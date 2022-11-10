package com.bets.controller.command.impl.showPage;

import com.bets.controller.command.api.Command;
import com.bets.controller.command.api.RequestContext;
import com.bets.controller.command.api.ResponseContext;
import com.bets.service.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.bets.controller.command.Attributes.CURRENT_USER;

public class ShowProfilePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowProfilePageCommand.class);

    private static final Command INSTANCE = new ShowProfilePageCommand();
//    private static final AccountServiceImpl accountService = new AccountServiceImpl();

    private static final String PAGE_PATH = "/WEB-INF/jsp/profile.jsp";
    private static final String ERROR_PAGE_PATH = "/WEB-INF/jsp/error.jsp";

    private static final ResponseContext SHOW_PROFILE_PAGE_CONTEXT = new ResponseContext() {
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
        HttpSession session = context.getCurrentSession().get();
        UserDto userDto = (UserDto) session.getAttribute(CURRENT_USER);

//        AccountDto accountDto;
//        try {
//            accountDto = accountService.getAccountByUserId(userDto.getId());
//            session.setAttribute(CURRENT_ACCOUNT, accountDto);
//        } catch (ServiceException e) {
//            logger.error(ERROR_ATTRIBUTE + DELIMITER + e.getMessage());
//            context.addAttributeToJsp(ERROR_ATTRIBUTE, e.getMessage());
//        }

        return SHOW_PROFILE_PAGE_CONTEXT;
    }
}

