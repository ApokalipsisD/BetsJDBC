package com.bets.model;

import java.util.Arrays;
import java.util.Objects;

public enum Game {
    CSGO(1), Valorant(2);

    private final Integer id;

    Game(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static Game getById(Integer id) {
        return Arrays.stream(Game.values())
                .filter(role -> Objects.equals(role.getId(), id))
                .findFirst()
                .orElse(null);
    }
    // valuesAsList
}
