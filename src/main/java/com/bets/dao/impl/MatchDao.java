package com.bets.dao.impl;

import com.bets.dao.api.ConnectionPool;
import com.bets.dao.api.Dao;
import com.bets.dao.exception.DaoException;
import com.bets.dao.exception.DaoMessageException;
import com.bets.dao.impl.connectionPool.ConnectionPoolImpl;
import com.bets.model.Match;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MatchDao implements Dao<Match, Integer> {
    Logger logger = LogManager.getLogger(MatchDao.class);

    private static final String SQL_SAVE_MATCH = "insert into match(team_1, team_2, coefficient_1, coefficient_2, score_1, score_2, date, status_id, game_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_MATCH = "UPDATE match SET team_1=?, team_2=?, coefficient_1=?, coefficient_2=?, score_1=?, score_2=?, date=?, status_id=?, game_id=? WHERE id=?";
    private static final String SQL_DELETE_MATCH = "DELETE FROM match WHERE id=?";
    private static final String SQL_FIND_MATCH_BY_ID = "SELECT id, team_1, team_2, coefficient_1, coefficient_2, score_1, score_2, date, status_id, game_id FROM match WHERE id=?";
    private static final String SQL_FIND_ALL_MATCHES = "SELECT id, team_1, team_2, coefficient_1, coefficient_2, score_1, score_2, date, status_id, game_id FROM match";

    private final ConnectionPool pool = ConnectionPoolImpl.getInstance();

    @Override
    public Match save(Match match) throws DaoException {
        Connection connection = pool.takeConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_MATCH, Statement.RETURN_GENERATED_KEYS)) {
            doAction(match, preparedStatement);

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                match.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            logger.error(DaoMessageException.SAVE_MATCH_EXCEPTION + e);
            throw new DaoException(DaoMessageException.SAVE_MATCH_EXCEPTION);
        } finally {
            closeResultSet(resultSet);
            pool.returnConnection(connection);
        }
        return match;
    }

    private void doAction(Match match, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, match.getFirstTeam());
        preparedStatement.setInt(2, match.getSecondTeam());
        preparedStatement.setBigDecimal(3, match.getFirstCoefficient());
        preparedStatement.setBigDecimal(4, match.getSecondCoefficient());
        preparedStatement.setInt(5, match.getFirstTeamScore());
        preparedStatement.setInt(6, match.getSecondTeamScore());
        preparedStatement.setTimestamp(7, match.getDate());
        preparedStatement.setInt(8, match.getStatus().getId());
        preparedStatement.setInt(9, match.getGame().getId());
    }


    @Override
    public boolean update(Match match) throws DaoException {
        Connection connection = pool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_MATCH)) {
            doAction(match, preparedStatement);
            preparedStatement.setInt(10, match.getId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(DaoMessageException.UPDATE_MATCH_EXCEPTION + e);
            throw new DaoException(DaoMessageException.UPDATE_MATCH_EXCEPTION);
        } finally {
            pool.returnConnection(connection);
        }
    }

    @Override
    public boolean delete(Integer matchId) throws DaoException {
        Connection connection = pool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_MATCH)) {
            preparedStatement.setInt(1, matchId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(DaoMessageException.DELETE_MATCH_EXCEPTION + e);
            throw new DaoException(DaoMessageException.DELETE_MATCH_EXCEPTION);
        } finally {
            pool.returnConnection(connection);
        }
    }

    @Override
    public Match findById(Integer id) throws DaoException {
        Connection connection = pool.takeConnection();
        Match match = null;
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_MATCH_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                match = new Match(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getBigDecimal(4),
                        resultSet.getBigDecimal(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7),
                        resultSet.getTimestamp(8),
                        resultSet.getInt(9),
                        resultSet.getInt(10)
                );
            }
        } catch (SQLException e) {
            logger.error(DaoMessageException.FIND_MATCH_BY_ID_EXCEPTION + e);
            throw new DaoException(DaoMessageException.FIND_MATCH_BY_ID_EXCEPTION);
        } finally {
            closeResultSet(resultSet);
            pool.returnConnection(connection);
        }
        return match;
    }

    @Override
    public List<Match> findAll() throws DaoException {
        List<Match> matchList = new ArrayList<>();
        Connection connection = pool.takeConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_MATCHES)) {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                matchList.add(new Match(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getBigDecimal(4),
                        resultSet.getBigDecimal(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7),
                        resultSet.getTimestamp(8),
                        resultSet.getInt(9),
                        resultSet.getInt(10)
                ));
            }
        } catch (SQLException e) {
            logger.error(DaoMessageException.FIND_ALL_MATCHES_EXCEPTION + e);
            throw new DaoException(DaoMessageException.FIND_ALL_MATCHES_EXCEPTION);
        } finally {
            closeResultSet(resultSet);
            pool.returnConnection(connection);
        }
        return matchList;
    }

}
