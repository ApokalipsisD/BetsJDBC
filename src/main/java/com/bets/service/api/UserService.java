package com.bets.service.api;

import com.bets.service.dto.AbstractDto;
import com.bets.service.exception.ServiceException;

public interface UserService <T extends AbstractDto<K>, K> extends Service<T, K> {
    boolean delete(K id) throws ServiceException;

    T getById(K id) throws ServiceException;
}
