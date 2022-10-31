package com.bets;

import com.bets.dao.api.ConnectionPool;
import com.bets.dao.exception.DaoException;
import com.bets.dao.impl.MatchDao;
import com.bets.dao.impl.UserBetOnMatchDao;
import com.bets.dao.impl.UserDao;
import com.bets.dao.impl.connectionPool.ConnectionPoolImpl;
import com.bets.model.Match;
import com.bets.model.User;
import com.bets.model.UserBetOnMatch;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) throws DaoException {
        ConnectionPool pool = ConnectionPoolImpl.getInstance();
        UserDao userDao = new UserDao();
        MatchDao matchDao = new MatchDao();
        UserBetOnMatchDao userBetOnMatchDao = new UserBetOnMatchDao();
        pool.initialize();

        User user = new User("aarne", "aa", "name", null, 21, "email@mail.ru", BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP), 2);
        Match match = new Match("heroic", "Big", BigDecimal.valueOf(1.5).setScale(2, RoundingMode.HALF_UP), BigDecimal.valueOf(2).setScale(2, RoundingMode.HALF_UP), 0, 0, Timestamp.valueOf("2022-10-29 15:00:00"), 2, 1);
        UserBetOnMatch betOnMatch = new UserBetOnMatch(17, 2, BigDecimal.valueOf(5).setScale(2, RoundingMode.HALF_UP), 1, BigDecimal.valueOf(2).setScale(2, RoundingMode.HALF_UP), 1, BigDecimal.valueOf(25).setScale(2, RoundingMode.HALF_UP));

        System.out.println(userDao.save(user));
        pool.shutdown();
    }
}
