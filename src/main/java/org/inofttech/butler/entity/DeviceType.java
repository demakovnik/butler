package org.inofttech.butler.entity;


import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;


@JsonRootName("deviceType")
public enum DeviceType implements Serializable {
    TEMPERATURE_SENSOR,
    PRESSURE_SENSOR,
    HUMIDITY_SENSOR;

    public String getText() {
        String text = "";
        if (this.name().equals("TEMPERATURE_SENSOR")) {
            text = "Temperature Sensor";
        }
        if (this.name().equals("PRESSURE_SENSOR")) {
            text = "Pressure Sensor";
        }
        if (this.name().equals("HUMIDITY_SENSOR")) {
            text = "Humidity Sensor";
        }
        return text;
    }
}
