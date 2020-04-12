package org.inofttech.butler.model;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Message {

    private final LocalDate currentTimeAndDate;
    private final String title;
    private final String payload;
    private final String destination;

    public Message(String title, String payload, String destination) {
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(payload, "payload must not be null");
        Objects.requireNonNull(destination, "destination must not be null");
        this.title = title;
        this.payload = payload;
        this.destination = destination;
        currentTimeAndDate = LocalDate.now();
    }

    public LocalDate getCurrentTimeAndDate() {
        return currentTimeAndDate;
    }

    public String getPayload() {
        return payload;
    }

    public String getTitle() {
        return title;
    }

    public String getDestination() {
        return destination;
    }
}
