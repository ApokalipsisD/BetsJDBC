package com.bets.service.impl;

import com.bets.dao.api.daoApi.TeamDao;
import com.bets.dao.exception.DaoException;
import com.bets.dao.impl.TeamDaoImpl;
import com.bets.dao.model.Team;
import com.bets.service.api.TeamService;
import com.bets.service.converter.api.Converter;
import com.bets.service.converter.impl.TeamConverter;
import com.bets.service.dto.TeamDto;
import com.bets.service.exception.MessageException;
import com.bets.service.exception.ServiceException;
import com.bets.service.validator.api.Validator;
import com.bets.service.validator.impl.TeamValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TeamServiceImpl implements TeamService<TeamDto, Integer> {
    private static final Logger logger = LogManager.getLogger(TeamServiceImpl.class);
    private final TeamDao<Team, Integer> teamDaoImpl = new TeamDaoImpl();
    private final Validator<TeamDto, Integer> validator = new TeamValidator();
    private final Converter<Team, TeamDto, Integer> converter = new TeamConverter();

    @Override
    public TeamDto save(TeamDto teamDto) throws ServiceException {
        validator.validate(teamDto);
        try {
            return converter.convert(teamDaoImpl.save(converter.convert(teamDto)));
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean update(TeamDto teamDto) throws ServiceException {
        validator.validate(teamDto);
        try {
            return teamDaoImpl.update(converter.convert(teamDto));
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer id) throws ServiceException {
        validator.validateId(id);
        try {
            return teamDaoImpl.delete(id);
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public TeamDto getById(Integer id) throws ServiceException {
        validator.validateId(id);
        Team result;
        try {
            result = teamDaoImpl.findById(id);
            if (Objects.isNull(result)) {
                throw new DaoException(MessageException.TEAM_NOT_FOUND_EXCEPTION);
            }
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }
        return converter.convert(result);
    }

    @Override
    public List<TeamDto> getAll() throws ServiceException {
        List<Team> teamList;
        List<TeamDto> teamDtoList = new ArrayList<>();
        try {
            teamList = teamDaoImpl.findAll();
            teamList.forEach(user -> teamDtoList.add(converter.convert(user)));
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }
        return teamDtoList;
    }
}
