package com.bets.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class Match extends Entity<Integer>{
    private String firstTeam;
    private String secondTeam;
    private BigDecimal firstCoefficient;
    private BigDecimal secondCoefficient;
    private Integer firstTeamScore;
    private Integer secondTeamScore;
    private Timestamp date;
    private GameStatus status;
    private Game game;

    public Match() {
    }

    public Match(String firstTeam, String secondTeam, BigDecimal firstCoefficient, BigDecimal secondCoefficient, Integer firstTeamScore, Integer secondTeamScore, Timestamp date, Integer statusId, Integer gameId) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.firstCoefficient = firstCoefficient;
        this.secondCoefficient = secondCoefficient;
        this.firstTeamScore = firstTeamScore;
        this.secondTeamScore = secondTeamScore;
        this.date = date;
        this.status = GameStatus.getById(statusId);
        this.game = Game.getById(gameId);
    }

    public Match(Integer id, String firstTeam, String secondTeam, BigDecimal firstCoefficient, BigDecimal secondCoefficient, Integer firstTeamScore, Integer secondTeamScore, Timestamp date, Integer statusId, Integer gameId) {
        this.id = id;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.firstCoefficient = firstCoefficient;
        this.secondCoefficient = secondCoefficient;
        this.firstTeamScore = firstTeamScore;
        this.secondTeamScore = secondTeamScore;
        this.date = date;
        this.status = GameStatus.getById(statusId);
        this.game = Game.getById(gameId);
    }

    public String getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(String firstTeam) {
        this.firstTeam = firstTeam;
    }

    public String getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(String secondTeam) {
        this.secondTeam = secondTeam;
    }

    public BigDecimal getFirstCoefficient() {
        return firstCoefficient;
    }

    public void setFirstCoefficient(BigDecimal firstCoefficient) {
        this.firstCoefficient = firstCoefficient;
    }

    public BigDecimal getSecondCoefficient() {
        return secondCoefficient;
    }

    public void setSecondCoefficient(BigDecimal secondCoefficient) {
        this.secondCoefficient = secondCoefficient;
    }

    public Integer getFirstTeamScore() {
        return firstTeamScore;
    }

    public void setFirstTeamScore(Integer firstTeamScore) {
        this.firstTeamScore = firstTeamScore;
    }

    public Integer getSecondTeamScore() {
        return secondTeamScore;
    }

    public void setSecondTeamScore(Integer secondTeamScore) {
        this.secondTeamScore = secondTeamScore;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return firstTeam.equals(match.firstTeam) && secondTeam.equals(match.secondTeam) && firstCoefficient.equals(match.firstCoefficient) && secondCoefficient.equals(match.secondCoefficient) && firstTeamScore.equals(match.firstTeamScore) && secondTeamScore.equals(match.secondTeamScore) && date.equals(match.date) && status == match.status && game == match.game;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstTeam, secondTeam, firstCoefficient, secondCoefficient, firstTeamScore, secondTeamScore, date, status, game);
    }

    @Override
    public String toString() {
        return "Match{" +
                "firstTeam='" + firstTeam + '\'' +
                ", secondTeam='" + secondTeam + '\'' +
                ", firstCoefficient=" + firstCoefficient +
                ", secondCoefficient=" + secondCoefficient +
                ", firstTeamScore=" + firstTeamScore +
                ", secondTeamScore=" + secondTeamScore +
                ", date=" + date +
                ", status=" + status +
                ", game=" + game +
                ", id=" + id +
                '}';
    }
}
