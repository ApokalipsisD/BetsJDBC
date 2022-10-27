package com.bets.model;

import java.util.Arrays;

public enum Role {
    USER(0), ADMIN(1);

    private final Integer id;

    Role(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static Role getById(int id) {
        return Arrays.stream(Role.values())
                .filter(role -> role.getId() == id)
                .findFirst()
                .orElse(null);
    }
    // valuesAsList
}
