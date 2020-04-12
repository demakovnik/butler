package org.inofttech.butler.model;

@FunctionalInterface
public interface MessageSender {

    void send(Message message);
}
