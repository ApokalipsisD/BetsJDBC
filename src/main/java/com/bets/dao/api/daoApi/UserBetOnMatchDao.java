package com.bets.dao.api.daoApi;

import com.bets.dao.exception.DaoException;
import com.bets.dao.model.Entity;
import com.bets.dao.model.UserBetOnMatch;

import java.util.List;

public interface UserBetOnMatchDao<T extends Entity<K>, K> extends Dao<T, K> {
    boolean delete(K userId, K matchId) throws DaoException;

    List<UserBetOnMatch> findByUserId(K id) throws DaoException;

    T findById(K userId, K matchId) throws DaoException;
}
