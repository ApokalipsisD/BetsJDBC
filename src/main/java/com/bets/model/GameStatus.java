package com.bets.model;

import java.util.Arrays;

public enum GameStatus {
    COMING(0), LIVE(1), FINISHED(2);

    private final Integer id;

    GameStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static GameStatus getById(int id) {
        return Arrays.stream(GameStatus.values())
                .filter(role -> role.getId() == id)
                .findFirst()
                .orElse(null);
    }
    // valuesAsList
}
