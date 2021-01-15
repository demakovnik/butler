package org.inofttech.butler.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "link_address")
    private String linkAddress;



    @Enumerated(EnumType.STRING)
    @Column(name = "device_type")
    private DeviceType deviceType;

    @Column(name = "model_number")
    private String modelNumber;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<TemporaryDeviceDetails> details;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "building_id")
    private Building building;

    public Device() {
    }

    public Device(String linkAddress, DeviceType deviceType, String modelNumber, String description) {
        this.linkAddress = linkAddress;
        this.deviceType = deviceType;
        this.modelNumber = modelNumber;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        Device device = (Device) o;
        return id == device.id &&
                Objects.equals(linkAddress, device.linkAddress) &&
                deviceType == device.deviceType &&
                Objects.equals(modelNumber, device.modelNumber) &&
                Objects.equals(description, device.description) &&
                Objects.equals(details, device.details) &&
                Objects.equals(building, device.building);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, linkAddress, deviceType, modelNumber, description, details, building);
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", linkAddress='" + linkAddress + '\'' +
                ", deviceType=" + deviceType +
                ", modelNumber='" + modelNumber + '\'' +
                ", description='" + description + '\'' +
                ", details=" + details +
                ", building=" + building +
                '}';
    }
}
