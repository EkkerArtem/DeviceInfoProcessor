package com.dataart.intern.impl;

import com.dataart.intern.impl.creators.ConsumerCreator;
import com.dataart.intern.impl.creators.ProducerCreator;

/**
 * Interface that stores some variables for ConsumerCreator and ProducerCreator classes.
 *
 * @see ConsumerCreator
 * @see ProducerCreator
 */
public interface IKafkaConstants {

    String KAFKA_BROKERS = "localhost:9092";

    Integer MESSAGE_COUNT = 10;

    String CLIENT_ID = "client1";

    String TOPIC_NAME = "demo4";

    String GROUP_ID_CONFIG = "consumerGroup1";

    Integer MAX_NO_MESSAGE_FOUND_COUNT = 10;

    String OFFSET_RESET_EARLIER = "earliest";

    Integer MAX_POLL_RECORDS = 1;

}