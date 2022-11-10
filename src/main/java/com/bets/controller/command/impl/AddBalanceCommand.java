package com.bets.controller.command.impl;

import com.bets.controller.command.api.Command;
import com.bets.controller.command.api.RequestContext;
import com.bets.controller.command.api.ResponseContext;
import com.bets.service.api.UserService;
import com.bets.service.dto.UserDto;
import com.bets.service.exception.ServiceException;
import com.bets.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.bets.controller.command.Attributes.CURRENT_USER;
import static com.bets.controller.command.Attributes.DELIMITER;
import static com.bets.controller.command.Attributes.ERROR_ATTRIBUTE;
import static com.bets.controller.command.Attributes.MESSAGE;

public class AddBalanceCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddBalanceCommand.class);

    private static final Command INSTANCE = new AddBalanceCommand();
    private static final UserService<UserDto, Integer> userService = new UserServiceImpl();
    private static final String PAGE_PATH = "/controller?command=show_profile_page";
    private static final String ERROR_PAGE_PATH = "/WEB-INF/jsp/error.jsp";

    private static final ResponseContext SUCCESSFUL_ADD_TEAM_CONTEXT = new ResponseContext() {
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

        BigDecimal deposit = new BigDecimal(context.getParameterByName("deposit"));

        try {
            userDto.setBalance((userDto.getBalance().add(deposit)).setScale(2, RoundingMode.HALF_UP));
            userService.update(userDto);
            session.setAttribute(CURRENT_USER, userDto);

            context.addAttributeToJsp(MESSAGE, "Balance added successfully");
        } catch (ServiceException e) {
            logger.error(ERROR_ATTRIBUTE + DELIMITER + e.getMessage());
            context.addAttributeToJsp(ERROR_ATTRIBUTE, e.getMessage());
        }
//        pagePath = context.getContextPath() + context.getHeader();
        return SUCCESSFUL_ADD_TEAM_CONTEXT;
    }
}
