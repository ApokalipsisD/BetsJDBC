package com.bets.service.dto;

import com.bets.dao.model.Game;
import com.bets.dao.model.GameStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class MatchDto extends AbstractDto<Integer>{
    private Integer firstTeam;
    private Integer secondTeam;
    private BigDecimal firstCoefficient;
    private BigDecimal secondCoefficient;
    private Integer firstTeamScore;
    private Integer secondTeamScore;
    private Timestamp date;
    private GameStatus status;
    private Game game;

    public MatchDto() {
    }

    public MatchDto(Integer firstTeam, Integer secondTeam, BigDecimal firstCoefficient, BigDecimal secondCoefficient, Timestamp date, Integer statusId, Integer gameId) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.firstCoefficient = firstCoefficient;
        this.secondCoefficient = secondCoefficient;
        this.date = date;
        this.status = GameStatus.getById(statusId);
        this.game = Game.getById(gameId);
    }
    public MatchDto(Integer firstTeam, Integer secondTeam, BigDecimal firstCoefficient, BigDecimal secondCoefficient, Integer firstTeamScore, Integer secondTeamScore, Timestamp date, Integer statusId, Integer gameId) {
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

    public MatchDto(Integer id, Integer firstTeam, Integer secondTeam, BigDecimal firstCoefficient, BigDecimal secondCoefficient, Integer firstTeamScore, Integer secondTeamScore, Timestamp date, Integer statusId, Integer gameId) {
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

    public Integer getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(Integer firstTeam) {
        this.firstTeam = firstTeam;
    }

    public Integer getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(Integer secondTeam) {
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
        MatchDto matchDto = (MatchDto) o;
        return firstTeam.equals(matchDto.firstTeam) && secondTeam.equals(matchDto.secondTeam) && firstCoefficient.equals(matchDto.firstCoefficient) && secondCoefficient.equals(matchDto.secondCoefficient) && firstTeamScore.equals(matchDto.firstTeamScore) && secondTeamScore.equals(matchDto.secondTeamScore) && date.equals(matchDto.date) && status == matchDto.status && game == matchDto.game;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstTeam, secondTeam, firstCoefficient, secondCoefficient, firstTeamScore, secondTeamScore, date, status, game);
    }

    @Override
    public String toString() {
        return "MatchDto{" +
                "firstTeam=" + firstTeam +
                ", secondTeam=" + secondTeam +
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
