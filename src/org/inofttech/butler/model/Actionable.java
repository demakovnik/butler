package org.inofttech.butler.model;

public interface Actionable {

    String getResponseFromDevice();

    void sendMessage(Message message, MessageSender messageSender);
}
