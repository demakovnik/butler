package org.inofttech.butler.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "buildings")
public class Building extends AbstractEntity {

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "area_id")
    private Area area;

    @OneToMany(mappedBy = "building",cascade = CascadeType.ALL)
    private Set<Device> devices;

    public Building(String description) {

        this.description = description;
        devices = new HashSet<>();
    }

    public Building() {
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
}
