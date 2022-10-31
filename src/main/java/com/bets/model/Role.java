package com.bets.model;

import java.util.Arrays;
import java.util.Objects;

public enum Role {
    USER(1), ADMIN(2);

    private final Integer id;

    Role(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static Role getById(Integer id) {
        return Arrays.stream(Role.values())
                .filter(role -> Objects.equals(role.getId(), id))
                .findFirst()
                .orElse(null);
    }
}
