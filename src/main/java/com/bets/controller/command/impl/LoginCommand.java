package com.bets.controller.command.impl;

import com.bets.controller.command.api.Command;
import com.bets.controller.command.api.RequestContext;
import com.bets.controller.command.api.ResponseContext;
import com.bets.service.api.UserService;
import com.bets.service.dto.UserDto;
import com.bets.service.exception.MessageException;
import com.bets.service.exception.ServiceException;
import com.bets.service.impl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.Objects;

import static com.bets.controller.command.Attributes.*;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    private static final Command INSTANCE = new LoginCommand();
    private static final UserService<UserDto, Integer> user = new UserServiceImpl();
    private static final String PAGE_PATH = "/controller?command=show_main";
    private static final String FAIL_PAGE_PATH = "/controller?command=show_login";
    private static final String ERROR_PAGE_PATH = "/WEB-INF/jsp/error.jsp";

    private static final ResponseContext SUCCESSFUL_LOG_IN_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return PAGE_PATH;
        }

        @Override
        public boolean isRedirect() {
            return true;
        }
    };

    private static final ResponseContext LOG_IN_FAILED_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return FAIL_PAGE_PATH;
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
        HttpSession session = context.getCurrentSession().orElse(context.createSession());

        String login = context.getParameterByName(LOGIN_ATTRIBUTE);
        String password = context.getParameterByName(PASSWORD_ATTRIBUTE);

        UserDto userDto;
        try {
            if (Objects.isNull(login) || Objects.isNull(password)) {
                throw new ServiceException(MessageException.ENTER_DATE_EXCEPTION);
            }
            userDto = user.getByLogin(login);
            if (userDto.getPassword().equals(password)) {
                session.setAttribute(CURRENT_USER, userDto);
            } else {
                throw new ServiceException(MessageException.USER_NOT_FOUND_EXCEPTION);
            }
        } catch (ServiceException e) {
            logger.error(ERROR_ATTRIBUTE + DELIMITER + e.getMessage());
            context.addAttributeToJsp(ERROR_ATTRIBUTE, e.getMessage());
            return LOG_IN_FAILED_CONTEXT;
        }

        return SUCCESSFUL_LOG_IN_CONTEXT;
    }
}

