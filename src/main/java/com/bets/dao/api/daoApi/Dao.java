package com.bets.dao.api.daoApi;

import com.bets.dao.exception.DaoException;
import com.bets.dao.model.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public interface Dao <T extends Entity<K>, K> {
    Logger logger = LogManager.getLogger(Dao.class);

    boolean update(T entity) throws DaoException;

    List<T> findAll() throws DaoException;

    default void closeResultSet(ResultSet resultSet) {
        if (Objects.nonNull(resultSet)) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }
}
