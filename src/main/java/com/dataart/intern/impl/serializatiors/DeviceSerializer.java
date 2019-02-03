package com.dataart.intern.impl.serializatiors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * Class that gets the Device object and converts it to array of bytes.
 */
public class DeviceSerializer implements Serializer<DeviceBean> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, DeviceBean data) {

        byte[] retVal = null;

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            retVal = objectMapper.writeValueAsString(data).getBytes();

        } catch (Exception exception) {

            System.out.println("Error in serializing object" + data);

        }

        return retVal;
    }

    @Override
    public void close() {
    }
}