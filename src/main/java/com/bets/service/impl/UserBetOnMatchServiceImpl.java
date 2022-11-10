package com.bets.service.impl;

import com.bets.dao.api.daoApi.UserBetOnMatchDao;
import com.bets.dao.exception.DaoException;
import com.bets.dao.impl.UserBetOnMatchDaoImpl;
import com.bets.dao.model.User;
import com.bets.dao.model.UserBetOnMatch;
import com.bets.service.api.UserBetOnMatchService;
import com.bets.service.converter.api.Converter;
import com.bets.service.converter.impl.UserBetOnMatchConverter;
import com.bets.service.converter.impl.UserConverter;
import com.bets.service.dto.UserBetOnMatchDto;
import com.bets.service.dto.UserDto;
import com.bets.service.exception.ServiceException;
import com.bets.service.validator.api.Validator;
import com.bets.service.validator.impl.UserBetOnMatchValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserBetOnMatchServiceImpl implements UserBetOnMatchService<UserBetOnMatchDto, Integer> {
    private static final Logger logger = LogManager.getLogger(UserBetOnMatchServiceImpl.class);
    private final UserBetOnMatchDao<UserBetOnMatch, Integer> betDao = new UserBetOnMatchDaoImpl();
    private final Validator<UserBetOnMatchDto, Integer> validator = new UserBetOnMatchValidator();
    private final Converter<UserBetOnMatch, UserBetOnMatchDto, Integer> converter = new UserBetOnMatchConverter();
    private final Converter<User, UserDto, Integer> userConverter = new UserConverter();

    @Override
    public UserBetOnMatchDto save(UserBetOnMatchDto betDto, UserDto userDto) throws ServiceException {
        validator.validate(betDto);
        try {
            return converter.convert(betDao.save(converter.convert(betDto), userConverter.convert(userDto)));
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean update(UserBetOnMatchDto betDto) throws ServiceException {
        validator.validate(betDto);
        try {
            return betDao.update(converter.convert(betDto));
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer userId, Integer matchId) throws ServiceException {
        validator.validateId(userId);
        validator.validateId(matchId);
        try {
            return betDao.delete(userId, matchId);
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<UserBetOnMatchDto> getByUserId(Integer id) throws ServiceException {
        validator.validateId(id);
        List<UserBetOnMatch> betList;
        List<UserBetOnMatchDto> betDtoList = new ArrayList<>();
        try {
            betList = betDao.findByUserId(id);
            betList.forEach(bet -> betDtoList.add(converter.convert(bet)));
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }
        return betDtoList;
    }

    @Override
    public UserBetOnMatchDto getById(Integer userId, Integer matchId) throws ServiceException {
        validator.validateId(userId);
        validator.validateId(matchId);
        UserBetOnMatch result;
        try {
            result = betDao.findById(userId, matchId);
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }
        return Objects.isNull(result) ? null : converter.convert(result);
    }

    @Override
    public boolean deleteBetByUserId(Integer userId) throws ServiceException {
        validator.validateId(userId);
        try {
            return betDao.deleteByUserId(userId);
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean updateEarningsAndUserBalance(UserBetOnMatchDto betDto, UserDto userDto) throws ServiceException {
        validator.validate(betDto);
        try {
            return betDao.updateEarningsAndUserBalance(converter.convert(betDto), userConverter.convert(userDto));
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<UserBetOnMatchDto> getAll() throws ServiceException {
        List<UserBetOnMatch> betList;
        List<UserBetOnMatchDto> betDtoList = new ArrayList<>();
        try {
            betList = betDao.findAll();
            betList.forEach(bet -> betDtoList.add(converter.convert(bet)));
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }
        return betDtoList;
    }
}
