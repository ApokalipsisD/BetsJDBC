package com.bets.service.api;

import com.bets.service.dto.AbstractDto;
import com.bets.service.exception.ServiceException;

import java.util.List;

public interface Service<T extends AbstractDto<K>, K> {

    T save(T entity) throws ServiceException;

    boolean update(T entity) throws ServiceException;

    List<T> getAll() throws ServiceException;
}
