package org.inofttech.butler.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "device_details")
public class TemporaryDeviceDetails extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "time")
    private LocalDateTime dateTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="device_id")
    @JsonIgnore
    private Device device;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @OneToMany(mappedBy = "details", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Measurement> measurements;

    public TemporaryDeviceDetails() {
    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    @Override
    public String toString() {
        return "TemporaryDeviceDetails{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", device=" + device +
                '}';
    }
}
