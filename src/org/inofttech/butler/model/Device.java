package org.inofttech.butler.model;

import java.util.Objects;

public abstract class Device extends Item implements Actionable {
    private final String description;
    private final String address;

    public Device(String uuid, String name, String description, String address) {
        super(uuid, name);
        this.description = description;
        Objects.requireNonNull(address,"address must not be null");
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(description, device.description) &&
                address.equals(device.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, address);
    }

    @Override
    public String toString() {
        return "Device{" + super.toString() +
                " description='" + description + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
