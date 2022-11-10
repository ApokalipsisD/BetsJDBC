package com.bets.controller.command.impl;

import com.bets.controller.command.api.Command;
import com.bets.controller.command.api.RequestContext;
import com.bets.controller.command.api.ResponseContext;
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

import java.util.Objects;

import static com.bets.controller.command.Attributes.CURRENT_USER;
import static com.bets.controller.command.Attributes.DELIMITER;
import static com.bets.controller.command.Attributes.ERROR_ATTRIBUTE;
import static com.bets.controller.command.Attributes.MESSAGE;

public class DeleteMatchCommand implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteMatchCommand.class);

    private static final Command INSTANCE = new DeleteMatchCommand();
    private static final UserService<UserDto, Integer> userService = new UserServiceImpl();
    private static final UserBetOnMatchService<UserBetOnMatchDto, Integer> betService = new UserBetOnMatchServiceImpl();
    private static final MatchService<MatchDto, Integer> matchService = new MatchServiceImpl();
    private static final String PAGE_PATH = "/controller?command=show_matches";
    private static final String ERROR_PAGE_PATH = "/WEB-INF/jsp/error.jsp";

    private static final ResponseContext SUCCESSFUL_DELETE_MATCH_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return PAGE_PATH;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

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
        Integer id = Objects.nonNull(context.getParameterByName("id"))
                ? Integer.valueOf(context.getParameterByName("id")) : null;

        UserBetOnMatchDto userBetOnMatchDto;

        try {
            userBetOnMatchDto = betService.getById(userDto.getId(), id);
            matchService.delete(id, userBetOnMatchDto);
            session.setAttribute(CURRENT_USER, userService.getById(userDto.getId()));
            context.addAttributeToJsp(MESSAGE, "Match was deleted successfully");
        } catch (ServiceException e) {
            logger.error(ERROR_ATTRIBUTE + DELIMITER + e.getMessage());
            context.addAttributeToJsp(ERROR_ATTRIBUTE, e.getMessage());
        }

        return SUCCESSFUL_DELETE_MATCH_CONTEXT;
    }
}
