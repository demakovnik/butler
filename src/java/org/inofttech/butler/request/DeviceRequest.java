package org.inofttech.butler.request;

import java.util.Map;

public class DeviceRequest {

    private String linkAddress;

    private String modelNumber;

    private Map<String, String> deviceData;

    public DeviceRequest() {
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public Map<String, String> getDeviceData() {
        return deviceData;
    }

    public void setDeviceData(Map<String, String> deviceData) {
        this.deviceData = deviceData;
    }

    @Override
    public String toString() {
        return "DeviceRequest{" +
                "linkAddress='" + linkAddress + '\'' +
                ", modelNumber='" + modelNumber + '\'' +
                ", deviceData=" + deviceData +
                '}';
    }
}
