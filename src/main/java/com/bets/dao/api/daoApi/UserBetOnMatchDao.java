package com.bets.dao.api.daoApi;

import com.bets.dao.exception.DaoException;
import com.bets.dao.model.Entity;
import com.bets.dao.model.User;
import com.bets.dao.model.UserBetOnMatch;

import java.sql.Connection;
import java.util.List;

public interface UserBetOnMatchDao<T extends Entity<K>, K> extends Dao<T, K> {
    T save(T entity, User user) throws DaoException;

    boolean delete(K userId, K matchId) throws DaoException;

    List<UserBetOnMatch> findByUserId(K id) throws DaoException;

    T findById(K userId, K matchId) throws DaoException;

    boolean deleteByUserId(K userId) throws DaoException;

    boolean deleteBeforeMatch(Connection connection, T entity) throws DaoException;

    boolean updateEarningsAndUserBalance(T entity, User user) throws DaoException;
}
