package org.inofttech.butler.entity;


import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.Embeddable;
import java.io.Serializable;


@JsonRootName("deviceType")
public enum DeviceType implements Serializable {
    TEMPERATURE_SENSOR,
    PRESSURE_SENSOR,
    HUMIDITY_SENSOR
}
