package com.dataart.intern;

import com.dataart.intern.impl.conversation.ConversationCreator;
import com.dataart.intern.impl.serializatiors.DeviceBean;

/**
 * DeviceInfoProcessor class that is creating producers and consumer to share the information about devices and share it through apache kafka.
 * Producer sends info to kafka and then Consumer is reading it from kafka, and if device temperature more 15 - logging it to file.
 *
 * @see DeviceBean
 * @see ConversationCreator
 */
public interface DeviceInfoProcessor {

    void startProcessing();

}
