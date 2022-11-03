package com.bets.service.validator.impl;

import com.bets.service.dto.TeamDto;
import com.bets.service.exception.MessageException;
import com.bets.service.exception.ServiceException;
import com.bets.service.validator.api.Validator;

import java.util.Objects;

public class TeamValidator implements Validator<TeamDto, Integer> {
    @Override
    public void validate(TeamDto teamDto) throws ServiceException {
        validateTeamName(teamDto.getName());
    }

    private void validateTeamName(String team) throws ServiceException {
        if (Objects.isNull(team)) {
            throw new ServiceException(MessageException.TEAM_IS_NULL_EXCEPTION);
        }
    }


}
