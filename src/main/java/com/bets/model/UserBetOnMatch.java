package com.bets.model;

import java.util.Objects;

public class UserBetOnMatch {
    private User userOnMatch;
    private Match match;
    private Double bet;
    private Integer team;
    private Double coefficient;
    private BetStatus betStatus;
    private Double earnings;

    public UserBetOnMatch() {
    }

    public UserBetOnMatch(User userOnMatch, Match match, Double bet, Integer team, Double coefficient, Integer betStatusId, Double earnings) {
        this.userOnMatch = userOnMatch;
        this.match = match;
        this.bet = bet;
        this.team = team;
        this.coefficient = coefficient;
        this.betStatus = BetStatus.getById(betStatusId);
        this.earnings = earnings;
    }

    public User getUserOnMatch() {
        return userOnMatch;
    }

    public void setUserOnMatch(User userOnMatch) {
        this.userOnMatch = userOnMatch;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Double getBet() {
        return bet;
    }

    public void setBet(Double bet) {
        this.bet = bet;
    }

    public Integer getTeam() {
        return team;
    }

    public void setTeam(Integer team) {
        this.team = team;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public BetStatus getBetStatus() {
        return betStatus;
    }

    public void setBetStatus(BetStatus betStatus) {
        this.betStatus = betStatus;
    }

    public Double getEarnings() {
        return earnings;
    }

    public void setEarnings(Double earnings) {
        this.earnings = earnings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBetOnMatch that = (UserBetOnMatch) o;
        return userOnMatch.equals(that.userOnMatch) && match.equals(that.match) && bet.equals(that.bet) && team.equals(that.team) && coefficient.equals(that.coefficient) && betStatus == that.betStatus && earnings.equals(that.earnings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userOnMatch, match, bet, team, coefficient, betStatus, earnings);
    }

    @Override
    public String toString() {
        return "UserBetOnMatch{" +
                "userOnMatch=" + userOnMatch +
                ", match=" + match +
                ", bet=" + bet +
                ", team=" + team +
                ", coefficient=" + coefficient +
                ", betStatus=" + betStatus +
                ", earnings=" + earnings +
                '}';
    }
}
