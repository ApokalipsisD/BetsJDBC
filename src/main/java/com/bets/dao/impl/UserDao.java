package com.bets.dao.impl;

import com.bets.dao.api.ConnectionPool;
import com.bets.dao.api.Dao;
import com.bets.dao.exception.DaoException;
import com.bets.dao.exception.DaoMessageException;
import com.bets.dao.impl.connectionPool.ConnectionPoolImpl;
import com.bets.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User, Integer> {
    Logger logger = LogManager.getLogger(UserDao.class);
    private static final String SQL_SAVE_USER = "INSERT INTO user_account(login, password, name, surname, age, email, balance, role_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_USER = "UPDATE user_account SET login=?, password=?, name=?, surname=?, age=?, email=?, balance=?, role_id=? WHERE id=?";
    private static final String SQL_DELETE_USER = "DELETE FROM user_account WHERE id=?";
    private static final String SQL_FIND_USER_BY_ID = "SELECT id, login, password, name, surname, age, email, balance, role_id FROM user_account WHERE id=?";
    private static final String SQL_FIND_ALL_USERS = "SELECT id, login, password, name, surname, age, email, balance, role_id FROM user_account";


    private final ConnectionPool pool = ConnectionPoolImpl.getInstance();

    @Override
    public User save(User user) throws DaoException {
        Connection connection = pool.takeConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_USER, Statement.RETURN_GENERATED_KEYS)) {
            doAction(user, preparedStatement);
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            logger.error(DaoMessageException.SAVE_USER_EXCEPTION + e);
            throw new DaoException(DaoMessageException.SAVE_USER_EXCEPTION);
        } finally {
            closeResultSet(resultSet);
            pool.returnConnection(connection);
        }
        return user;
    }

    private void doAction(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getName());
        preparedStatement.setString(4, user.getSurname());
        preparedStatement.setInt(5, user.getAge());
        preparedStatement.setString(6, user.getEmail());
        preparedStatement.setBigDecimal(7, user.getBalance());
        preparedStatement.setInt(8, user.getRole().getId());
    }

    @Override
    public boolean update(User user) throws DaoException {
        Connection connection = pool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
            doAction(user, preparedStatement);
            preparedStatement.setInt(9, user.getId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(DaoMessageException.UPDATE_USER_EXCEPTION + e);
            throw new DaoException(DaoMessageException.UPDATE_USER_EXCEPTION);
        } finally {
            pool.returnConnection(connection);
        }
    }

    @Override
    public boolean delete(Integer userId) throws DaoException {
        Connection connection = pool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER)) {
            preparedStatement.setInt(1, userId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(DaoMessageException.DELETE_USER_EXCEPTION + e);
            throw new DaoException(DaoMessageException.DELETE_USER_EXCEPTION);
        } finally {
            pool.returnConnection(connection);
        }
    }

    @Override
    public User findById(Integer id) throws DaoException {
        Connection connection = pool.takeConnection();
        User user = null;
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getBigDecimal(8),
                        resultSet.getInt(9)
                );
            }
        } catch (SQLException e) {
            logger.error(DaoMessageException.FIND_USER_BY_ID_EXCEPTION + e);
            throw new DaoException(DaoMessageException.FIND_USER_BY_ID_EXCEPTION);
        } finally {
            closeResultSet(resultSet);
            pool.returnConnection(connection);
        }
        return user;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> userList = new ArrayList<>();
        Connection connection = pool.takeConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USERS)) {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getBigDecimal(8),
                        resultSet.getInt(9)));
            }
        } catch (SQLException e) {
            logger.error(DaoMessageException.FIND_ALL_USERS_EXCEPTION + e);
            throw new DaoException(DaoMessageException.FIND_ALL_USERS_EXCEPTION);
        } finally {
            closeResultSet(resultSet);
            pool.returnConnection(connection);
        }
        return userList;
    }
}
