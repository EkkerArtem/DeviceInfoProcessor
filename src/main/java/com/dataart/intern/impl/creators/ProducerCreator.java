package com.dataart.intern.impl.creators;

import com.dataart.intern.impl.IKafkaConstants;
import com.dataart.intern.impl.serializatiors.DeviceBean;
import com.dataart.intern.impl.serializatiors.DeviceSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;

import java.util.Properties;

/**
 * Class that creates a Kafka producer with properties.
 * <p>
 * BOOTSTRAP_SERVERS_CONFIG: The Kafka broker's address.
 * CLIENT_ID_CONFIG: Id of the producer so that the broker can determine the source of the request.
 * KEY_SERIALIZER_CLASS_CONFIG: The class that will be used to serialize the key object.
 * VALUE_SERIALIZER_CLASS_CONFIG: The class that will be used to serialize the value object.
 */
public class ProducerCreator {

    public static Producer<Long, DeviceBean> createProducer() {

        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);

        props.put(ProducerConfig.CLIENT_ID_CONFIG, IKafkaConstants.CLIENT_ID);

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());

        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, DeviceSerializer.class.getName());

        return new KafkaProducer<>(props);

    }

}