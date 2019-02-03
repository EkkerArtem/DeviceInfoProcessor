package com.dataart.intern.impl.serializatiors;

import org.junit.Assert;
import org.junit.Test;

public class DeviceSerializationTest {

    @Test
    public void serializationTest(){
        DeviceBean device = new DeviceBean();
        device.setDeviceId(4);
        device.setDeviceName("Device4");
        device.setTemperature(21);
        device.setTimestamp(333444);

        DeviceSerializer deviceSerializer = new DeviceSerializer();
        byte[] serializedDevice = deviceSerializer.serialize("",device);

        DeviceDeserializer deviceDeserializer = new DeviceDeserializer();
        DeviceBean deserializedDevice = deviceDeserializer.deserialize("", serializedDevice);

        Assert.assertEquals(device.getDeviceId(), deserializedDevice.getDeviceId());
        Assert.assertEquals(device.getTemperature(), deserializedDevice.getTemperature());
        Assert.assertEquals(device.getDeviceName(), deserializedDevice.getDeviceName());
        Assert.assertEquals(device.getTimestamp(), deserializedDevice.getTimestamp());
    }

}