package org.inofttech.butler.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class Item implements Serializable {
    private final String uuid;
    private final String name;



    public Item(String uuid, String name) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(name, "name must not be null");
        this.uuid = uuid;
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
