package org.inofttech.butler.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Message extends AbstractBaseEntity {

    private String payload;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private ActiveDevice activeDevice;

    public Message() {
    }

    public Message(Long id, String payload, ActiveDevice activeDevice) {
        super(id);
        this.payload = payload;
        this.activeDevice = activeDevice;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public ActiveDevice getActiveDevice() {
        return activeDevice;
    }

    public void setActiveDevice(ActiveDevice activeDevice) {
        this.activeDevice = activeDevice;
    }
}
