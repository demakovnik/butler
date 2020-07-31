package org.inofttech.butler.model;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "activedevices")
public class ActiveDevice extends AbstractNamedEntity {

    protected String description;
    protected String networkAddress;

    public ActiveDevice() {
    }

    public ActiveDevice(Long id, String name, String description, String networkAddress) {
        super(id, name);
        this.description = description;
        this.networkAddress = networkAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(String address) {
        this.networkAddress = address;
    }
}
