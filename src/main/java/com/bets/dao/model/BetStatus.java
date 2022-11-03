package com.bets.dao.model;

import java.util.Arrays;
import java.util.Objects;

public enum BetStatus {
    WIN(1), LOSE(2), EXPECTING(3);

    private final Integer id;

    BetStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static BetStatus getById(Integer id) {
        return Arrays.stream(BetStatus.values())
                .filter(role -> Objects.equals(role.getId(), id))
                .findFirst()
                .orElse(null);
    }
}
