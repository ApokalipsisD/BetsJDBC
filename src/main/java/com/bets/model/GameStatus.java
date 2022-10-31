package com.bets.model;

import java.util.Arrays;
import java.util.Objects;

public enum GameStatus {
    COMING(1), LIVE(2), FINISHED(3);

    private final Integer id;

    GameStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static GameStatus getById(Integer id) {
        return Arrays.stream(GameStatus.values())
                .filter(role -> Objects.equals(role.getId(), id))
                .findFirst()
                .orElse(null);
    }
    // valuesAsList
}
