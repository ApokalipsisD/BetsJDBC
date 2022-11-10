package com.bets.service.dto;

import com.bets.dao.model.BetStatus;

import java.math.BigDecimal;
import java.util.Objects;

public class UserBetOnMatchDto extends AbstractDto<Integer>{
    private Integer userId;
    private Integer matchId;
    private BigDecimal bet;
    private Integer team;
    private BigDecimal coefficient;
    private BetStatus betStatus;
    private BigDecimal earnings;

    public UserBetOnMatchDto() {
    }
    public UserBetOnMatchDto(Integer userId, Integer matchId, BigDecimal bet, Integer team, BigDecimal coefficient, Integer betStatusId) {
        this.userId = userId;
        this.matchId = matchId;
        this.bet = bet;
        this.team = team;
        this.coefficient = coefficient;
        this.betStatus = BetStatus.getById(betStatusId);
    }

    public UserBetOnMatchDto(Integer userId, Integer matchId, BigDecimal bet, Integer team, BigDecimal coefficient, Integer betStatusId, BigDecimal earnings) {
        this.userId = userId;
        this.matchId = matchId;
        this.bet = bet;
        this.team = team;
        this.coefficient = coefficient;
        this.betStatus = BetStatus.getById(betStatusId);
        this.earnings = earnings;
    }

    public UserBetOnMatchDto(Integer id, Integer userId, Integer matchId, BigDecimal bet, Integer team, BigDecimal coefficient, Integer betStatusId, BigDecimal earnings) {
        this.id = id;
        this.userId = userId;
        this.matchId = matchId;
        this.bet = bet;
        this.team = team;
        this.coefficient = coefficient;
        this.betStatus = BetStatus.getById(betStatusId);
        this.earnings = earnings;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public BigDecimal getBet() {
        return bet;
    }

    public void setBet(BigDecimal bet) {
        this.bet = bet;
    }

    public Integer getTeam() {
        return team;
    }

    public void setTeam(Integer team) {
        this.team = team;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    public BetStatus getBetStatus() {
        return betStatus;
    }

    public void setBetStatus(BetStatus betStatus) {
        this.betStatus = betStatus;
    }

    public BigDecimal getEarnings() {
        return earnings;
    }

    public void setEarnings(BigDecimal earnings) {
        this.earnings = earnings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBetOnMatchDto that = (UserBetOnMatchDto) o;
        return userId.equals(that.userId) && matchId.equals(that.matchId) && bet.equals(that.bet) && team.equals(that.team) && coefficient.equals(that.coefficient) && betStatus == that.betStatus && Objects.equals(earnings, that.earnings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, matchId, bet, team, coefficient, betStatus, earnings);
    }

    @Override
    public String toString() {
        return "UserBetOnMatchDto{" +
                "userId=" + userId +
                ", matchId=" + matchId +
                ", bet=" + bet +
                ", team=" + team +
                ", coefficient=" + coefficient +
                ", betStatus=" + betStatus +
                ", earnings=" + earnings +
                ", id=" + id +
                '}';
    }
}
