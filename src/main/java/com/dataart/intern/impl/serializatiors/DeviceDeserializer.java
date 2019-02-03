package com.dataart.intern.impl.serializatiors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * Class that gets the byte array of serialized devices and converts them back to Device object.
 */
public class DeviceDeserializer implements Deserializer<DeviceBean> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public DeviceBean deserialize(String topic, byte[] data) {

        ObjectMapper mapper = new ObjectMapper();

        DeviceBean device = null;

        try {

            device = mapper.readValue(data, DeviceBean.class);

        } catch (Exception exception) {

            System.out.println("Error in deserializing bytes " + exception);

        }

        return device;
    }

    @Override
    public void close() {
    }
}