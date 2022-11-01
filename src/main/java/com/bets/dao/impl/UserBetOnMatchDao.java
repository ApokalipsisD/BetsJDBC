package com.bets.dao.impl;

import com.bets.dao.api.ConnectionPool;
import com.bets.dao.exception.DaoException;
import com.bets.dao.exception.DaoMessageException;
import com.bets.dao.impl.connectionPool.ConnectionPoolImpl;
import com.bets.model.UserBetOnMatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class UserBetOnMatchDao {
    Logger logger = LogManager.getLogger(UserBetOnMatchDao.class);

    private static final String SQL_SAVE_BET = "INSERT INTO user_bet_on_match(user_id, match_id, bet, team, coefficient, bet_status_id, earnings) values (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_BET = "UPDATE user_bet_on_match SET bet=?, team=?, coefficient=?, bet_status_id=?, earnings=? where user_id=? and match_id=?";
    private static final String SQL_DELETE_BET = "DELETE FROM user_bet_on_match WHERE user_id=? and match_id=?";
    private static final String SQL_FIND_BET_BY_ID = "SELECT id, user_id, match_id, bet, team, coefficient, bet_status_id, earnings FROM user_bet_on_match WHERE user_id=? and match_id=?";
    private static final String SQL_FIND_BET_BY_USER_ID = "SELECT id, user_id, match_id, bet, team, coefficient, bet_status_id, earnings FROM user_bet_on_match WHERE user_id=?";
    private static final String SQL_FIND_ALL_BETS = "SELECT id, user_id, match_id, bet, team, coefficient, bet_status_id, earnings FROM user_bet_on_match";
    private final ConnectionPool pool = ConnectionPoolImpl.getInstance();

    public UserBetOnMatch save(UserBetOnMatch bet) throws DaoException {
        Connection connection = pool.takeConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_BET, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, bet.getUserId());
            preparedStatement.setInt(2, bet.getMatchId());
            preparedStatement.setBigDecimal(3, bet.getBet());
            preparedStatement.setInt(4, bet.getTeam());
            preparedStatement.setBigDecimal(5, bet.getCoefficient());
            preparedStatement.setInt(6, bet.getBetStatus().getId());
            preparedStatement.setBigDecimal(7, bet.getEarnings());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                bet.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            logger.error(DaoMessageException.SAVE_BET_EXCEPTION + e);
            throw new DaoException(DaoMessageException.SAVE_BET_EXCEPTION);
        } finally {
            if (Objects.nonNull(resultSet)) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
            pool.returnConnection(connection);
        }
        return bet;
    }

    public boolean update(UserBetOnMatch bet) throws DaoException {
        Connection connection = pool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BET)) {
            preparedStatement.setBigDecimal(1, bet.getBet());
            preparedStatement.setInt(2, bet.getTeam());
            preparedStatement.setBigDecimal(3, bet.getCoefficient());
            preparedStatement.setInt(4, bet.getBetStatus().getId());
            preparedStatement.setBigDecimal(5, bet.getEarnings());
            preparedStatement.setInt(6, bet.getUserId());
            preparedStatement.setInt(7, bet.getMatchId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(DaoMessageException.UPDATE_BET_EXCEPTION + e);
            throw new DaoException(DaoMessageException.UPDATE_BET_EXCEPTION);
        } finally {
            pool.returnConnection(connection);
        }
    }

    public boolean delete(Integer integerId, Integer matchId) throws DaoException {
        Connection connection = pool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BET)) {
            preparedStatement.setInt(1, integerId);
            preparedStatement.setInt(2, matchId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(DaoMessageException.DELETE_BET_EXCEPTION + e);
            throw new DaoException(DaoMessageException.DELETE_BET_EXCEPTION);
        } finally {
            pool.returnConnection(connection);
        }
    }

    public List<UserBetOnMatch> findByUserId(Integer userId) throws DaoException {
        Connection connection = pool.takeConnection();
        List<UserBetOnMatch> betListForUser = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BET_BY_USER_ID)) {
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                betListForUser.add(new UserBetOnMatch(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getBigDecimal(4),
                        resultSet.getInt(5),
                        resultSet.getBigDecimal(6),
                        resultSet.getInt(7),
                        resultSet.getBigDecimal(8)
                ));
            }
        } catch (SQLException e) {
            logger.error(DaoMessageException.FIND_BET_BY_ID_EXCEPTION + e);
            throw new DaoException(DaoMessageException.FIND_BET_BY_ID_EXCEPTION);
        } finally {
            if (Objects.nonNull(resultSet)) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
            pool.returnConnection(connection);
        }
        return betListForUser;
    }

    public UserBetOnMatch findById(Integer userId, Integer matchId) throws DaoException {
        Connection connection = pool.takeConnection();
        UserBetOnMatch bet = null;
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BET_BY_ID)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, matchId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bet = new UserBetOnMatch(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getBigDecimal(4),
                        resultSet.getInt(5),
                        resultSet.getBigDecimal(6),
                        resultSet.getInt(7),
                        resultSet.getBigDecimal(8)
                );
            }
        } catch (SQLException e) {
            logger.error(DaoMessageException.FIND_BET_BY_ID_EXCEPTION + e);
            throw new DaoException(DaoMessageException.FIND_BET_BY_ID_EXCEPTION);
        } finally {
            if (Objects.nonNull(resultSet)) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
            pool.returnConnection(connection);
        }
        return bet;
    }

    public List<UserBetOnMatch> findAll() throws DaoException {
        List<UserBetOnMatch> betList = new ArrayList<>();
        Connection connection = pool.takeConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_BETS)) {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                betList.add(new UserBetOnMatch(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getBigDecimal(4),
                        resultSet.getInt(5),
                        resultSet.getBigDecimal(6),
                        resultSet.getInt(7),
                        resultSet.getBigDecimal(8)
                ));
            }
        } catch (SQLException e) {
            logger.error(DaoMessageException.FIND_ALL_BETS_EXCEPTION + e);
            throw new DaoException(DaoMessageException.FIND_ALL_BETS_EXCEPTION);
        } finally {
            if (Objects.nonNull(resultSet)) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
            pool.returnConnection(connection);
        }
        return betList;
    }
}
