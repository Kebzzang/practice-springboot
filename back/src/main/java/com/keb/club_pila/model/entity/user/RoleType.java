package com.keb.club_pila.model.entity.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String toString() {
        return super.toString();
    }
}
