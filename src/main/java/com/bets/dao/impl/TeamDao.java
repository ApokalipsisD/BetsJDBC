package com.bets.dao.impl;

import com.bets.dao.api.ConnectionPool;
import com.bets.dao.api.Dao;
import com.bets.dao.exception.DaoException;
import com.bets.dao.exception.DaoMessageException;
import com.bets.dao.impl.connectionPool.ConnectionPoolImpl;
import com.bets.model.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeamDao implements Dao<Team, Integer> {

    Logger logger = LogManager.getLogger(TeamDao.class);
    private static final String SQL_SAVE_TEAM = "INSERT INTO team(name) VALUES (?)";
    private static final String SQL_UPDATE_TEAM = "UPDATE team SET name=? WHERE id=?";
    private static final String SQL_DELETE_TEAM = "DELETE FROM team WHERE id=?";
    private static final String SQL_FIND_TEAM_BY_ID = "SELECT id, name FROM team WHERE id=?";
    private static final String SQL_FIND_ALL_TEAMS = "SELECT id, name FROM team";

    private final ConnectionPool pool = ConnectionPoolImpl.getInstance();

    @Override
    public Team save(Team team) throws DaoException {
        Connection connection = pool.takeConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_TEAM, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, team.getName());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                team.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            logger.error(DaoMessageException.SAVE_TEAM_EXCEPTION + e);
            throw new DaoException(DaoMessageException.SAVE_TEAM_EXCEPTION);
        } finally {
            closeResultSet(resultSet);
            pool.returnConnection(connection);
        }
        return team;
    }

    @Override
    public boolean update(Team team) throws DaoException {
        Connection connection = pool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_TEAM)) {
            preparedStatement.setString(1, team.getName());
            preparedStatement.setInt(2, team.getId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(DaoMessageException.UPDATE_TEAM_EXCEPTION + e);
            throw new DaoException(DaoMessageException.UPDATE_TEAM_EXCEPTION);
        } finally {
            pool.returnConnection(connection);
        }
    }

    @Override
    public boolean delete(Integer teamId) throws DaoException {
        Connection connection = pool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_TEAM)) {
            preparedStatement.setInt(1, teamId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(DaoMessageException.DELETE_TEAM_EXCEPTION + e);
            throw new DaoException(DaoMessageException.DELETE_TEAM_EXCEPTION);
        } finally {
            pool.returnConnection(connection);
        }
    }

    @Override
    public Team findById(Integer id) throws DaoException {
        Connection connection = pool.takeConnection();
        Team team = null;
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_TEAM_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                team = new Team(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                );
            }
        } catch (SQLException e) {
            logger.error(DaoMessageException.FIND_TEAM_BY_ID_EXCEPTION + e);
            throw new DaoException(DaoMessageException.FIND_TEAM_BY_ID_EXCEPTION);
        } finally {
            closeResultSet(resultSet);
            pool.returnConnection(connection);
        }
        return team;
    }

    @Override
    public List<Team> findAll() throws DaoException {
        List<Team> teamList = new ArrayList<>();
        Connection connection = pool.takeConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_TEAMS)) {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teamList.add(new Team(
                        resultSet.getInt(1),
                        resultSet.getString(2)));
            }
        } catch (SQLException e) {
            logger.error(DaoMessageException.FIND_ALL_TEAMS_EXCEPTION + e);
            throw new DaoException(DaoMessageException.FIND_ALL_TEAMS_EXCEPTION);
        } finally {
            closeResultSet(resultSet);
            pool.returnConnection(connection);
        }
        return teamList;
    }
}
