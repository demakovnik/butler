package org.inofttech.butler.entity.to;

import org.inofttech.butler.entity.Building;
import org.inofttech.butler.entity.DeviceType;
import org.inofttech.butler.entity.TemporaryDeviceDetails;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeviceDto {
    @NotNull
    @NotEmpty
    private String linkAddress;

    private DeviceType deviceType;

    @NotNull
    @NotEmpty(message = "Field must not be empty")
    private String modelNumber;

    private String description;

    private List<TemporaryDeviceDetails> details;

    private Building building;

    public DeviceDto(String linkAddress, DeviceType deviceType, String modelNumber, String description) {
        this.linkAddress = linkAddress;
        this.deviceType = deviceType;
        this.modelNumber = modelNumber;
        this.description = description;
        details = new ArrayList<>();
    }

    public DeviceDto() {
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TemporaryDeviceDetails> getDetails() {
        return details;
    }

    public void setDetails(List<TemporaryDeviceDetails> details) {
        this.details = details;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceDto deviceDto = (DeviceDto) o;
        return Objects.equals(linkAddress, deviceDto.linkAddress) &&
                deviceType == deviceDto.deviceType &&
                Objects.equals(modelNumber, deviceDto.modelNumber) &&
                Objects.equals(description, deviceDto.description) &&
                Objects.equals(details, deviceDto.details) &&
                Objects.equals(building, deviceDto.building);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkAddress, deviceType, modelNumber, description, details, building);
    }

    @Override
    public String toString() {
        return "DeviceDto{" +
                "linkAddress='" + linkAddress + '\'' +
                ", deviceType=" + deviceType +
                ", modelNumber='" + modelNumber + '\'' +
                ", description='" + description + '\'' +
                ", details=" + details +
                ", building=" + building +
                '}';
    }
}
