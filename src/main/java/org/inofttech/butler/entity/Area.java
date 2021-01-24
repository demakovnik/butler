package org.inofttech.butler.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "areas")
public class Area extends AbstractEntity{


    private String address;

    private String description;

    public Area(String address) {
        this.address = address;
    }

    public Area() {
    }

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Building> buildings;



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }
}
