package com.bets.dao.api.daoApi;

import com.bets.dao.exception.DaoException;
import com.bets.dao.model.Entity;
import com.bets.dao.model.UserBetOnMatch;

public interface MatchDao<T extends Entity<K>, K> extends Dao<T, K> {
    T save(T entity) throws DaoException;

    boolean delete(K id, UserBetOnMatch bet) throws DaoException;

    T findById(K id) throws DaoException;

    boolean updateMatchStatus(T entity) throws DaoException;
}
