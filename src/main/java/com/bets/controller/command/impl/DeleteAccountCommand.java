package com.bets.controller.command.impl;

import com.bets.controller.command.api.Command;
import com.bets.controller.command.api.RequestContext;
import com.bets.controller.command.api.ResponseContext;
import com.bets.service.api.UserBetOnMatchService;
import com.bets.service.api.UserService;
import com.bets.service.dto.UserBetOnMatchDto;
import com.bets.service.dto.UserDto;
import com.bets.service.exception.ServiceException;
import com.bets.service.impl.UserBetOnMatchServiceImpl;
import com.bets.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import static com.bets.controller.command.Attributes.*;

public class DeleteAccountCommand implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteAccountCommand.class);

    private static final Command INSTANCE = new DeleteAccountCommand();
    private static final UserService<UserDto, Integer> userService = new UserServiceImpl();
    private static final UserBetOnMatchService<UserBetOnMatchDto, Integer> betService = new UserBetOnMatchServiceImpl();
    private static final String PAGE_PATH = "/controller?command=logout";
    private static final String ERROR_PAGE_PATH = "/WEB-INF/jsp/error.jsp";

    private static final ResponseContext SUCCESSFUL_DELETE_ACCOUNT_CONTEXT = new ResponseContext() {
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

        try {
            betService.deleteBetByUserId(userDto.getId());
            userService.delete(userDto.getId());
            session.removeAttribute(CURRENT_USER);
            context.addAttributeToJsp(MESSAGE, ACCOUNT_DELETED_MESSAGE);
        } catch (ServiceException e) {
            logger.error(ERROR_ATTRIBUTE + DELIMITER + e.getMessage());
            context.addAttributeToJsp(ERROR_ATTRIBUTE, e.getMessage());
        }

        return SUCCESSFUL_DELETE_ACCOUNT_CONTEXT;
    }
}
