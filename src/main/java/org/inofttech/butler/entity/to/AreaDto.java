package org.inofttech.butler.entity.to;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AreaDto {


    @NotNull
    @NotEmpty
    private String address;

    @NotNull
    @NotEmpty
    private String description;

    public AreaDto(String address) {
        this.address = address;
    }

    public AreaDto() {
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
