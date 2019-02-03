package com.dataart.intern.impl;

import com.dataart.intern.DeviceInfoProcessor;
import com.dataart.intern.impl.conversation.ConversationCreator;

public class DeviceInfoProcessorImpl implements DeviceInfoProcessor {
    @Override
    public void startProcessing() {
        ConversationCreator conversationCreator = new ConversationCreator();
        conversationCreator.startMassaging();
    }

    public static void main(String[] args) {
        DeviceInfoProcessor deviceInfoProcessor = new DeviceInfoProcessorImpl();
        deviceInfoProcessor.startProcessing();
    }

}
