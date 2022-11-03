package com.bets.service.api;

import com.bets.service.dto.AbstractDto;
import com.bets.service.dto.UserBetOnMatchDto;
import com.bets.service.exception.ServiceException;

import java.util.List;

public interface UserBetOnMatchService <T extends AbstractDto<K>, K> extends Service<T, K> {
    boolean delete(K userId, K matchId) throws ServiceException;

    List<UserBetOnMatchDto> getByUserId(K id) throws ServiceException;

    T getById(K userId, K matchId) throws ServiceException;
}
