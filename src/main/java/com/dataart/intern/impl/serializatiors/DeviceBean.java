package com.dataart.intern.impl.serializatiors;

import java.io.Serializable;

/**
 * JavaBean class for creating devices.
 */
public class DeviceBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int deviceId;
    private String deviceName;
    private long timestamp;
    private int temperature;

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}