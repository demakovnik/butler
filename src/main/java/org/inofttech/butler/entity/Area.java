package org.inofttech.butler.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "areas")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String address;

    public Area(String address) {
        this.address = address;
    }

    public Area() {
    }

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Building> buildings;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
