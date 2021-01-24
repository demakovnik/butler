package org.inofttech.butler.entity.to;

import org.inofttech.butler.entity.Area;
import org.inofttech.butler.entity.Device;

import java.util.HashSet;
import java.util.Set;

public class BuildingDto {

    private String description;

    private Area area;

    private Set<Device> devices;

    public BuildingDto(String description, Area area) {
        this.description = description;
        this.area = area;
        devices = new HashSet<>();
    }

    public BuildingDto() {
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }
}
