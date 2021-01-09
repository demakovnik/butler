package org.inofttech.butler.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, DEVICE;

    @Override
    public String getAuthority() {
        return name();
    }
}
