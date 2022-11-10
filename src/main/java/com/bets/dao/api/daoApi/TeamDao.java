package com.bets.dao.api.daoApi;

import com.bets.dao.exception.DaoException;
import com.bets.dao.model.Entity;

public interface TeamDao<T extends Entity<K>, K> extends Dao<T, K> {
    T save(T entity) throws DaoException;

    boolean delete(K id) throws DaoException;

    T findById(K id) throws DaoException;
}
