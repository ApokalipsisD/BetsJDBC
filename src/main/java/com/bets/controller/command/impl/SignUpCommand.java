package com.bets.controller.command.impl;

import com.bets.controller.command.api.Command;
import com.bets.controller.command.api.RequestContext;
import com.bets.controller.command.api.ResponseContext;
import com.bets.dao.model.Role;
import com.bets.service.api.UserService;
import com.bets.service.dto.UserDto;
import com.bets.service.exception.MessageException;
import com.bets.service.exception.ServiceException;
import com.bets.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import static com.bets.controller.command.Attributes.*;

public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SignUpCommand.class);
    private static final UserService<UserDto, Integer> userService = new UserServiceImpl();
    private static final Command INSTANCE = new SignUpCommand();
    private static final String PAGE_PATH = "/controller?command=show_login";
    private static final String FAIL_PAGE_PATH = "/controller?command=show_sign_up";
    private static final String ERROR_PAGE_PATH = "/WEB-INF/jsp/error.jsp";

    private static final ResponseContext SUCCESSFUL_SIGN_UP_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return PAGE_PATH;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final ResponseContext SIGN_UP_FAILED_CONTEXT = new ResponseContext() {
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

//        if(context.getCurrentSession().get().getAttribute())
        if (context.getHeader() == null) {
            return ERROR_CONTEXT;
        }
        String login = context.getParameterByName(LOGIN_ATTRIBUTE);
        String password = context.getParameterByName(PASSWORD_ATTRIBUTE);
        String repeatPassword = context.getParameterByName(REPEAT_PASSWORD_ATTRIBUTE);

        try {
            if (Objects.isNull(login) || Objects.isNull(password) || Objects.isNull(repeatPassword)) {
                throw new ServiceException(MessageException.ENTER_DATE_EXCEPTION);
            }
            if(!password.equals(repeatPassword)){
                throw new ServiceException(MessageException.PASSWORD_MISMATCH_MESSAGE);
            }
            if (!userService.checkIfLoginFree(login)) {
                throw new ServiceException(MessageException.LOGIN_IS_TAKEN_EXCEPTION);
            }
            userService.save(new UserDto(login, password, BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP), Role.USER.getId()));
            context.addAttributeToJsp(MESSAGE, USER_CREATED);
        } catch (ServiceException e) {
            logger.error(ERROR_ATTRIBUTE + DELIMITER + e.getMessage());
            context.addAttributeToJsp(ERROR_ATTRIBUTE, e.getMessage());
            return SIGN_UP_FAILED_CONTEXT;
        }

        return SUCCESSFUL_SIGN_UP_CONTEXT;
    }
}
