package com.bets.service.connectionPoolService;

import com.bets.dao.api.ConnectionPool;
import com.bets.dao.exception.DaoException;
import com.bets.dao.impl.connectionPool.ConnectionPoolImpl;
import com.bets.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class ConnectionPoolService {
    private static final Logger logger = LogManager.getLogger(ConnectionPoolService.class);

    private static final String CAN_NOT_INITIALIZE_CONNECTION_POOL_EXCEPTION = "Can not initialize connection pool";
    private static final String CAN_NOT_SHUTDOWN_CONNECTION_POOL_EXCEPTION = "Can not shutdown connection pool";
    private static ConnectionPoolService instance = new ConnectionPoolService();
    private final ConnectionPool pool = ConnectionPoolImpl.getInstance();

    private ConnectionPoolService() {

    }

    public static ConnectionPoolService getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ConnectionPoolService();
        }
        return instance;
    }

    public void initialize() throws ServiceException {
        try {
            pool.initialize();
        } catch (DaoException e) {
            logger.error(CAN_NOT_INITIALIZE_CONNECTION_POOL_EXCEPTION + e);
            throw new ServiceException(CAN_NOT_INITIALIZE_CONNECTION_POOL_EXCEPTION);
        }
    }

    public void shutDown() throws ServiceException {
        try {
            pool.shutdown();
        } catch (DaoException e) {
            logger.error(CAN_NOT_SHUTDOWN_CONNECTION_POOL_EXCEPTION + e);
            throw new ServiceException(CAN_NOT_SHUTDOWN_CONNECTION_POOL_EXCEPTION);
        }
    }
}
