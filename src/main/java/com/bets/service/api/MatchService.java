package com.bets.service.api;

import com.bets.service.dto.AbstractDto;
import com.bets.service.dto.UserBetOnMatchDto;
import com.bets.service.exception.ServiceException;

public interface MatchService <T extends AbstractDto<K>, K> extends Service<T, K> {
    T save(T entity) throws ServiceException;

    boolean delete(K id, UserBetOnMatchDto bet) throws ServiceException;

    T getById(K id) throws ServiceException;

    boolean updateMatchStatus(T entity) throws ServiceException;
}
