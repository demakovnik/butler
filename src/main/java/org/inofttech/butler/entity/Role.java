package org.inofttech.butler.entity;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public enum Role implements GrantedAuthority, Serializable {
    USER, ADMIN, DEVICE;

    @Override
    public String getAuthority() {
        return name();
    }
}
