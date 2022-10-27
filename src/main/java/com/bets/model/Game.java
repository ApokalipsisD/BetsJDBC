package com.bets.model;

import java.util.Arrays;

public enum Game {
    CSGO(0), Valorant(1);

    private final Integer id;

    Game(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static Game getById(int id) {
        return Arrays.stream(Game.values())
                .filter(role -> role.getId() == id)
                .findFirst()
                .orElse(null);
    }
    // valuesAsList
}
