package com.bets.service.api;

import com.bets.service.dto.AbstractDto;
import com.bets.service.exception.ServiceException;

public interface TeamService <T extends AbstractDto<K>, K> extends Service<T, K> {
    T save(T entity) throws ServiceException;

    boolean delete(K id) throws ServiceException;

    T getById(K id) throws ServiceException;
}
