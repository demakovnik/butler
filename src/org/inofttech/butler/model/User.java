package org.inofttech.butler.model;

import java.util.UUID;

public class User extends Item {

    public User(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public User(String uuid, String name) {
        super(uuid, name);
    }
}
