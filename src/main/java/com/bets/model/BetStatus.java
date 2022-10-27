package com.bets.model;

import java.util.Arrays;

public enum BetStatus {
    WIN(0), LOSE(1), EXPECTING(3);

    private final Integer id;

    BetStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static BetStatus getById(int id) {
        return Arrays.stream(BetStatus.values())
                .filter(role -> role.getId() == id)
                .findFirst()
                .orElse(null);
    }
    // valuesAsList
}
