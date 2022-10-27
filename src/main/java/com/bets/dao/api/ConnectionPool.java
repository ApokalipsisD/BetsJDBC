package com.bets.dao.api;

import com.bets.dao.exception.DaoException;

import java.sql.Connection;

public interface ConnectionPool {

    void initialize() throws DaoException;

    void shutdown() throws DaoException;

    Connection takeConnection();

    void returnConnection(Connection connection);

}
