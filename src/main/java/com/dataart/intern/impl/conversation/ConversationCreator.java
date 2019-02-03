package com.dataart.intern.impl.conversation;

import com.dataart.intern.impl.creators.ConsumerCreator;
import com.dataart.intern.impl.IKafkaConstants;
import com.dataart.intern.impl.creators.ProducerCreator;
import com.dataart.intern.impl.serializatiors.DeviceBean;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.log4j.Logger;

import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class that is running producer and consumer threads, so they can start massaging through Kafka.
 */
public class ConversationCreator {

    private static final Logger log = Logger.getLogger(ConversationCreator.class);

    private Runnable consumer = this::runConsumer;

    private static int numberOfDevices = 0;

    private static AtomicInteger deviceId = new AtomicInteger(0);

    /**
     * Method that is creating as much devices as user wants and running each other in its own thread
     * using CountDownLatch to minimize the time between all thread's start.
     *
     * @see CountDownLatch
     * @see ConversationCreator#runProducer()
     * @see ConversationCreator#runConsumer()
     */
    public void startMassaging() {

        System.out.println("Enter the number of devices you want to run");
        Scanner scanner = new Scanner(System.in);
        numberOfDevices = scanner.nextInt();

        CountDownLatch latch = new CountDownLatch(1);

        for (int i = 0; i < numberOfDevices; i++) {

            ProducerThread producerThread = new ProducerThread(latch);
            new Thread(producerThread).start();
        }

        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        latch.countDown();
    }

    /**
     * Method that is getting information about devices, processing it to log in a file and commits the offset
     * of record to broker.
     * If there is no message in a kafka, it will wait 1 second and check it again. If there is no massage
     * at the broker for 10 checks - it breaks the loop.
     *
     * @see IKafkaConstants#MAX_NO_MESSAGE_FOUND_COUNT to change the number of checks before exiting the loop.2
     */
    private void runConsumer() {

        Consumer<Long, DeviceBean> consumer = ConsumerCreator.createConsumer();

        int noMessageFound = 0;

        while (true) {

            ConsumerRecords<Long, DeviceBean> consumerRecords = consumer.poll(Duration.ofMillis(1000));

            if (consumerRecords.count() == 0) {

                noMessageFound++;

                if (noMessageFound > IKafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT)

                    break;
                else
                    continue;
            }

            consumerRecords.forEach(record -> {

                if (record.value().getTemperature() > 15) {

                    log.info("\"deviceId\":\"" + record.value().getDeviceId() + "\", " + "\"deviceName\":\"" + record.value().getDeviceName() + "\", "
                            + "\"timestamp\":\"" + record.value().getTimestamp() + "\", " + "\"temperature\":\"" + record.value().getTemperature() + "\"\n");
                }
            });
            consumer.commitAsync();
        }
        consumer.close();
    }

    /**
     * Method that is creating information about device and sending it to Kafka.
     * Each producer sends 10 massages.
     *
     * @see IKafkaConstants#MESSAGE_COUNT to change the number massages that each producer sends.
     */
    static void runProducer() {

        Producer<Long, DeviceBean> producer = ProducerCreator.createProducer();

        for (int index = 0; index < IKafkaConstants.MESSAGE_COUNT; index++) {

            int deviceNumber = deviceId.getAndIncrement();

            if (deviceId.get() == numberOfDevices) {
                deviceId.set(0);
            }

            DeviceBean device = new DeviceBean();
            device.setDeviceId(deviceNumber);
            device.setDeviceName("Device" + deviceNumber);
            device.setTemperature((int) (Math.round(10 + Math.random() * 10)));
            device.setTimestamp(System.currentTimeMillis());

            ProducerRecord<Long, DeviceBean> record = new ProducerRecord<>(IKafkaConstants.TOPIC_NAME,
                    device);

            int partition = -1;
            long offset = -1L;

            try {

                RecordMetadata metadata = producer.send(record).get();

                partition = metadata.partition();
                offset = metadata.offset();

            } catch (ExecutionException | InterruptedException e) {

                System.out.println("Error in sending record with key: " + index + " to partition " + partition
                        + " with offset " + offset);

                System.out.println(e);

            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("Thread died");
                e.printStackTrace();
            }
        }
    }
}