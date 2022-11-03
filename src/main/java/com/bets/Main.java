package com.bets;

import com.bets.dao.api.ConnectionPool;
import com.bets.dao.exception.DaoException;
import com.bets.dao.impl.connectionPool.ConnectionPoolImpl;
import com.bets.service.api.MatchService;
import com.bets.service.api.TeamService;
import com.bets.service.api.UserBetOnMatchService;
import com.bets.service.api.UserService;
import com.bets.service.dto.MatchDto;
import com.bets.service.dto.TeamDto;
import com.bets.service.dto.UserBetOnMatchDto;
import com.bets.service.dto.UserDto;
import com.bets.service.exception.ServiceException;
import com.bets.service.impl.MatchServiceImpl;
import com.bets.service.impl.TeamServiceImpl;
import com.bets.service.impl.UserBetOnMatchServiceImpl;
import com.bets.service.impl.UserServiceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) throws DaoException, ServiceException {
        ConnectionPool pool = ConnectionPoolImpl.getInstance();
        TeamService<TeamDto, Integer> teamService = new TeamServiceImpl();
        UserService<UserDto, Integer> userService = new UserServiceImpl();
        MatchService<MatchDto, Integer> matchService = new MatchServiceImpl();
        UserBetOnMatchService<UserBetOnMatchDto, Integer> betService = new UserBetOnMatchServiceImpl();

        pool.initialize();

        UserDto user = new UserDto(20,"login", "123Sergey321", "Namea", null, 21, "email@mail.ru", BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP), 2);
        MatchDto match = new MatchDto(9, 4, 3, BigDecimal.valueOf(1.5).setScale(2, RoundingMode.HALF_UP), BigDecimal.valueOf(2).setScale(2, RoundingMode.HALF_UP), 0, 0, Timestamp.valueOf("2022-11-29 15:00:00"), 2, 1);
        UserBetOnMatchDto betOnMatch = new UserBetOnMatchDto(20, 5, BigDecimal.valueOf(5).setScale(2, RoundingMode.HALF_UP), 2, BigDecimal.valueOf(2).setScale(2, RoundingMode.HALF_UP), 1, BigDecimal.valueOf(25).setScale(2, RoundingMode.HALF_UP));
        TeamDto team = new TeamDto("Liquid");

        System.out.println(betService.save(betOnMatch));
//        System.out.println(userBetOnMatchDao.save(betOnMatch));
//        System.out.println(new Timestamp(System.currentTimeMillis()));
        pool.shutdown();
    }
}
