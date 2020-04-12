package org.inofttech.butler.model;

import java.util.UUID;

public class TempSensor extends Device {

    public TempSensor(String name, String description, String address) {
        this(UUID.randomUUID().toString(), name, description, address);
    }

    public TempSensor(String uuid, String name, String description, String address) {
        super(uuid, name, description, address);
    }

    @Override
    public String getResponseFromDevice() {

        return null;
    }

    @Override
    public void sendMessage(Message message, MessageSender messageSender) {
        messageSender.send(message);
    }

}
