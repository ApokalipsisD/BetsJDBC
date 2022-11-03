package com.bets.service.converter.impl;

import com.bets.dao.model.UserBetOnMatch;
import com.bets.service.converter.api.Converter;
import com.bets.service.dto.UserBetOnMatchDto;

public class UserBetOnMatchConverter implements Converter<UserBetOnMatch, UserBetOnMatchDto, Integer> {
    @Override
    public UserBetOnMatch convert(UserBetOnMatchDto betDto) {
        return new UserBetOnMatch(
                betDto.getId(),
                betDto.getUserId(),
                betDto.getMatchId(),
                betDto.getBet(),
                betDto.getTeam(),
                betDto.getCoefficient(),
                betDto.getBetStatus().getId(),
                betDto.getEarnings()
        );
    }

    @Override
    public UserBetOnMatchDto convert(UserBetOnMatch bet) {
        return new UserBetOnMatchDto(
                bet.getId(),
                bet.getUserId(),
                bet.getMatchId(),
                bet.getBet(),
                bet.getTeam(),
                bet.getCoefficient(),
                bet.getBetStatus().getId(),
                bet.getEarnings()
        );
    }
}
