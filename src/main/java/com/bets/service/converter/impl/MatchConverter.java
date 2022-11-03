package com.bets.service.converter.impl;

import com.bets.dao.model.Match;
import com.bets.service.converter.api.Converter;
import com.bets.service.dto.MatchDto;

public class MatchConverter implements Converter<Match, MatchDto, Integer> {
    @Override
    public Match convert(MatchDto matchDto) {
        return new Match(
                matchDto.getId(),
                matchDto.getFirstTeam(),
                matchDto.getSecondTeam(),
                matchDto.getFirstCoefficient(),
                matchDto.getSecondCoefficient(),
                matchDto.getFirstTeamScore(),
                matchDto.getSecondTeamScore(),
                matchDto.getDate(),
                matchDto.getStatus().getId(),
                matchDto.getGame().getId()
        );
    }

    @Override
    public MatchDto convert(Match match) {
        return new MatchDto(
                match.getId(),
                match.getFirstTeam(),
                match.getSecondTeam(),
                match.getFirstCoefficient(),
                match.getSecondCoefficient(),
                match.getFirstTeamScore(),
                match.getSecondTeamScore(),
                match.getDate(),
                match.getStatus().getId(),
                match.getGame().getId()
        );
    }
}
