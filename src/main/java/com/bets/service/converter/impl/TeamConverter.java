package com.bets.service.converter.impl;

import com.bets.dao.model.Team;
import com.bets.service.converter.api.Converter;
import com.bets.service.dto.TeamDto;

public class TeamConverter implements Converter<Team, TeamDto, Integer> {
    @Override
    public Team convert(TeamDto teamDto) {
        return new Team(
                teamDto.getId(),
                teamDto.getName()
        );
    }

    @Override
    public TeamDto convert(Team team) {
        return new TeamDto(
                team.getId(),
                team.getName()
        );
    }
}
