package com.bets.controller.command.impl;

import com.bets.controller.command.api.Command;
import com.bets.controller.command.api.RequestContext;
import com.bets.controller.command.api.ResponseContext;
import com.bets.dao.model.BetStatus;
import com.bets.dao.model.GameStatus;
import com.bets.service.api.MatchService;
import com.bets.service.api.UserBetOnMatchService;
import com.bets.service.api.UserService;
import com.bets.service.dto.MatchDto;
import com.bets.service.dto.UserBetOnMatchDto;
import com.bets.service.dto.UserDto;
import com.bets.service.exception.ServiceException;
import com.bets.service.impl.MatchServiceImpl;
import com.bets.service.impl.UserBetOnMatchServiceImpl;
import com.bets.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Objects;

import static com.bets.controller.command.Attributes.CURRENT_USER;
import static com.bets.controller.command.Attributes.DELIMITER;
import static com.bets.controller.command.Attributes.ERROR_ATTRIBUTE;
import static com.bets.controller.command.Attributes.MESSAGE;

public class MakeBetCommand implements Command {
    private static final Logger logger = LogManager.getLogger(MakeBetCommand.class);
    private static final Command INSTANCE = new MakeBetCommand();
    private static final UserService<UserDto, Integer> userService = new UserServiceImpl();
    private static final UserBetOnMatchService<UserBetOnMatchDto, Integer> betService = new UserBetOnMatchServiceImpl();
    private static final MatchService<MatchDto, Integer> matchService = new MatchServiceImpl();
    private static final String PAGE_PATH = "/controller?command=match&id=";
    //    private static final String FAIL_PAGE_PATH = "/controller?command=match&id=";
    private static final String ERROR_PAGE_PATH = "/WEB-INF/jsp/error.jsp";

    private static String pagePath = null;

    private static final ResponseContext SUCCESSFUL_MAKE_BET_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return pagePath;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

//    private static final ResponseContext MAKE_BET_FAILED_CONTEXT = new ResponseContext() {
//        @Override
//        public String getPage() {
//            return FAIL_PAGE_PATH;
//        }
//
//        @Override
//        public boolean isRedirect() {
//            return false;
//        }
//    };

    private final ResponseContext ERROR_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return ERROR_PAGE_PATH;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext context) {
        if (context.getHeader() == null) {
            return ERROR_CONTEXT;
        }
        HttpSession session = context.getCurrentSession().get();

        UserDto userDto = (UserDto) session.getAttribute(CURRENT_USER);

        BigDecimal bet = new BigDecimal(context.getParameterByName("betTeam"));

        Integer teamYouBet = Integer.valueOf(context.getParameterByName("teamYouBet"));

        BigDecimal coefficientYouBet = new BigDecimal(context.getParameterByName("coefficientYouBet"));

        Integer id = Integer.valueOf(context.getParameterByName("id"));

        MatchDto matchDto;
        UserBetOnMatchDto userBetOnMatchDto;


//        System.out.println(bet);
//        System.out.println(teamYouBet);
//        System.out.println(coefficientYouBet);
//        System.out.println(id);
        try {
            if (bet.compareTo(userDto.getBalance()) > 0) {
                throw new ServiceException("You don't have enough money");
            }
            if (Objects.nonNull(betService.getById(userDto.getId(), id))) {
                throw new ServiceException("You have already bet on this match");
            }
            if (matchService.getById(id).getStatus() != GameStatus.COMING) {
                throw new ServiceException("You can no longer bet on this match");
            }
            userDto.setBalance(userDto.getBalance().subtract(bet));

            userBetOnMatchDto = new UserBetOnMatchDto(userDto.getId(), id, bet, teamYouBet, coefficientYouBet, BetStatus.EXPECTING.getId());
            betService.save(userBetOnMatchDto, userDto);
            context.addAttributeToJsp(MESSAGE, "Bet set successfully");


        } catch (ServiceException e) {
            logger.error(ERROR_ATTRIBUTE + DELIMITER + e.getMessage());
            context.addAttributeToJsp(ERROR_ATTRIBUTE, e.getMessage());
        }

        pagePath = PAGE_PATH + id;

        return SUCCESSFUL_MAKE_BET_CONTEXT;
    }
}
