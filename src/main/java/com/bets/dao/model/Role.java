package com.bets.dao.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum Role {
    USER(1), ADMIN(2), UNAUTHORISED(3);

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
    public static List<Role> valuesAsList() {
        return Arrays.asList(values());
    }
}
