package com.bets.service.api;

import com.bets.service.dto.AbstractDto;
import com.bets.service.dto.UserDto;
import com.bets.service.exception.ServiceException;

import java.sql.Connection;

public interface UserService <T extends AbstractDto<K>, K> extends Service<T, K> {
    T save(T entity) throws ServiceException;

    boolean delete(K id) throws ServiceException;

    T getById(K id) throws ServiceException;

    boolean checkIfLoginFree(String login) throws ServiceException;

    T getByLogin(String login) throws ServiceException;

    boolean updateUserBalance(Connection connection, UserDto userDto) throws ServiceException;
}
