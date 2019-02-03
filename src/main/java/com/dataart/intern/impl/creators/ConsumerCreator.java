package com.dataart.intern.impl.creators;

import com.dataart.intern.impl.IKafkaConstants;
import com.dataart.intern.impl.serializatiors.DeviceBean;
import com.dataart.intern.impl.serializatiors.DeviceDeserializer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;

import java.util.Collections;
import java.util.Properties;

/**
 * Class that creates a Kafka consumer with properties.
 * <p>
 * BOOTSTRAP_SERVERS_CONFIG: The Kafka broker's address.
 * GROUP_ID_CONFIG: The consumer group id used to identify to which group this consumer belongs.
 * KEY_DESERIALIZER_CLASS_CONFIG: The class name to deserialize the key object.
 * VALUE_DESERIALIZER_CLASS_CONFIG: The class name to deserialize the value object.
 * MAX_POLL_RECORDS_CONFIG: The max count of records that the consumer will fetch in one iteration.
 * ENABLE_AUTO_COMMIT_CONFIG: When the consumer from a group receives a message it must commit the offset of that record.
 * AUTO_OFFSET_RESET_CONFIG: For each consumer group, the last committed offset value is stored.
 */
public class ConsumerCreator {

    public static Consumer<Long, DeviceBean> createConsumer() {

        Properties props = new Properties();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);

        props.put(ConsumerConfig.GROUP_ID_CONFIG, IKafkaConstants.GROUP_ID_CONFIG);

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());

        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, DeviceDeserializer.class.getName());

        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, IKafkaConstants.MAX_POLL_RECORDS);

        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, IKafkaConstants.OFFSET_RESET_EARLIER);

        Consumer<Long, DeviceBean> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Collections.singletonList(IKafkaConstants.TOPIC_NAME));

        return consumer;

    }

}