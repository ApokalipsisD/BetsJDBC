package com.bets.service.impl;

import com.bets.dao.api.daoApi.UserDao;
import com.bets.dao.exception.DaoException;
import com.bets.dao.impl.UserDaoImpl;
import com.bets.dao.model.User;
import com.bets.service.api.UserService;
import com.bets.service.converter.api.Converter;
import com.bets.service.converter.impl.UserConverter;
import com.bets.service.dto.UserDto;
import com.bets.service.exception.MessageException;
import com.bets.service.exception.ServiceException;
import com.bets.service.validator.api.Validator;
import com.bets.service.validator.impl.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService<UserDto, Integer> {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final UserDao<User, Integer> userDaoImpl = new UserDaoImpl();
    private final Validator<UserDto, Integer> validator = new UserValidator();
    private final Converter<User, UserDto, Integer> converter = new UserConverter();

    @Override
    public UserDto save(UserDto userDto) throws ServiceException {
        validator.validate(userDto);
        try {
            return converter.convert(userDaoImpl.save(converter.convert(userDto)));
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean update(UserDto userDto) throws ServiceException {
        validator.validate(userDto);
        try {
            return userDaoImpl.update(converter.convert(userDto));
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer id) throws ServiceException {
        validator.validateId(id);
        try {
            return userDaoImpl.delete(id);
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public UserDto getById(Integer id) throws ServiceException {
        validator.validateId(id);
        User result;
        try {
            result = userDaoImpl.findById(id);
            if (Objects.isNull(result)) {
                throw new DaoException(MessageException.USER_NOT_FOUND_EXCEPTION);
            }
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }
        return converter.convert(result);
    }

    @Override
    public List<UserDto> getAll() throws ServiceException {
        List<User> userList;
        List<UserDto> userDtoList = new ArrayList<>();
        try {
            userList = userDaoImpl.findAll();
            userList.forEach(user -> userDtoList.add(converter.convert(user)));
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }

        return userDtoList;
    }
}
