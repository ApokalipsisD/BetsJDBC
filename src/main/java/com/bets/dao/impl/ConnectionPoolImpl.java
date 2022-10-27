package com.bets.dao.impl;

import com.bets.dao.api.ConnectionPool;
import com.bets.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPoolImpl implements ConnectionPool {

    private static final int POOL_SIZE = 5;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/bets";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1235";
//    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION_FAILED = "Connection has been failed";

    private final BlockingQueue<Connection> availableConnections;
    private final BlockingQueue<Connection> usedConnections;

    private static ConnectionPool instance;

    private boolean initialized;

    private ConnectionPoolImpl() {
        availableConnections = new ArrayBlockingQueue<>(POOL_SIZE);
        usedConnections = new ArrayBlockingQueue<>(POOL_SIZE);
    }

    public static ConnectionPool getInstance() {
        synchronized (ConnectionPool.class) {
            if (Objects.isNull(instance)) {
                instance = new ConnectionPoolImpl();
            }
        }
        return instance;
    }

    @Override
    public synchronized void initialize() throws DaoException {
        if (!initialized) {
            createConnection();
            initialized = true;
        }
    }

    private void createConnection() throws DaoException{
        try {
            for (int i = 0; i < POOL_SIZE; i++) {
//                Class.forName(DRIVER);
                Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
//                ProxyConnection proxyConnection = new ProxyConnection(connection, this);
                availableConnections.put(connection);
            }
        } catch (SQLException e) {
//            logger.error(CONNECTION_FAILED);
            throw new DaoException(CONNECTION_FAILED);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
//            logger.error(e.getMessage());
        }
    }

    @Override
    public void shutdown() throws DaoException {
        if (initialized) {
            closeConnections(availableConnections);
            closeConnections(usedConnections);
            availableConnections.clear();
            usedConnections.clear();
            initialized = false;
        }
    }

    private void closeConnections(BlockingQueue<Connection> connections) throws DaoException{
        for (Connection connection : connections) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
        }
    }

    @Override
    public synchronized Connection takeConnection() {
        Connection connection = null;
        try {
            connection = availableConnections.take();
            usedConnections.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
//            logger.error(e.getMessage());
        }
        return connection;
    }

    @Override
    public synchronized void returnConnection(Connection connection) {
        try {
            availableConnections.put(usedConnections.take());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
//            logger.error(e.getMessage());
        }
    }
}
