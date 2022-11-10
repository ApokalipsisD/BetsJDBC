package com.bets.service.api;

import com.bets.service.dto.AbstractDto;
import com.bets.service.dto.UserBetOnMatchDto;
import com.bets.service.dto.UserDto;
import com.bets.service.exception.ServiceException;

import java.util.List;

public interface UserBetOnMatchService <T extends AbstractDto<K>, K> extends Service<T, K> {
    T save(T entity, UserDto userDto) throws ServiceException;

    boolean delete(K userId, K matchId) throws ServiceException;

    List<UserBetOnMatchDto> getByUserId(K id) throws ServiceException;

    T getById(K userId, K matchId) throws ServiceException;

    boolean deleteBetByUserId(K userId) throws ServiceException;

    boolean updateEarningsAndUserBalance(T entity, UserDto userDto) throws ServiceException;
}
