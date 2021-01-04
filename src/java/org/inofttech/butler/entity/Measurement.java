package org.inofttech.butler.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "measurements")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="device_details_id")
    @JsonIgnore

    private TemporaryDeviceDetails details;

    public Measurement() {
    }

    public Measurement(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String measure) {
        this.value = measure;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public TemporaryDeviceDetails getDetails() {
        return details;
    }

    public void setDetails(TemporaryDeviceDetails details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", measure='" + value + '\'' +
                '}';
    }
}
