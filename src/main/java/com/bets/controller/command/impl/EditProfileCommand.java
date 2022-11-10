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

import static com.bets.controller.command.Attributes.AGE_ATTRIBUTE;
import static com.bets.controller.command.Attributes.CURRENT_USER;
import static com.bets.controller.command.Attributes.DELIMITER;
import static com.bets.controller.command.Attributes.EMAIL_ATTRIBUTE;
import static com.bets.controller.command.Attributes.ERROR_ATTRIBUTE;
import static com.bets.controller.command.Attributes.FIRST_NAME_ATTRIBUTE;
import static com.bets.controller.command.Attributes.LAST_NAME_ATTRIBUTE;
import static com.bets.controller.command.Attributes.MESSAGE;
import static com.bets.controller.command.Attributes.PROFILE_UPDATED_MESSAGE;
import static com.bets.controller.command.Attributes.USERNAME;


public class EditProfileCommand implements Command {
    private static final Logger logger = LogManager.getLogger(EditProfileCommand.class);
    private static final Command INSTANCE = new EditProfileCommand();
    private static final UserService<UserDto, Integer> userService = new UserServiceImpl();
    private static final String PAGE_PATH = "/controller?command=show_profile_page";
    private static final String FAIL_PAGE_PATH = "/WEB-INF/jsp/editProfile.jsp";
    private static final String ERROR_PAGE_PATH = "/WEB-INF/jsp/error.jsp";

    private static final ResponseContext SUCCESSFUL_PROFILE_CONTEXT = new ResponseContext() {
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

    private static final ResponseContext EDIT_PROFILE_FAILED_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return FAIL_PAGE_PATH;
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

        String login = context.getParameterByName(USERNAME);

        String name = context.getParameterByName(FIRST_NAME_ATTRIBUTE).isBlank()
                ? userDto.getName() : context.getParameterByName(FIRST_NAME_ATTRIBUTE);

        String surname = context.getParameterByName(LAST_NAME_ATTRIBUTE).isBlank()
                ? userDto.getSurname() : context.getParameterByName(LAST_NAME_ATTRIBUTE);

        String email = context.getParameterByName(EMAIL_ATTRIBUTE).isBlank()
                ? userDto.getEmail() : context.getParameterByName(EMAIL_ATTRIBUTE);

        Integer age = context.getParameterByName(AGE_ATTRIBUTE).isBlank()
                ? userDto.getAge() : Integer.valueOf(context.getParameterByName(AGE_ATTRIBUTE));


        try {
            UserDto currentUser = new UserDto(userDto.getId(), login, userDto.getPassword(), name, surname, age, email, userDto.getBalance(), userDto.getRole().getId());
            userService.update(currentUser);
            session.setAttribute(CURRENT_USER, currentUser);
            context.addAttributeToJsp(MESSAGE, PROFILE_UPDATED_MESSAGE);
        } catch (ServiceException e) {
            logger.error(ERROR_ATTRIBUTE + DELIMITER + e.getMessage());
            context.addAttributeToJsp(ERROR_ATTRIBUTE, e.getMessage());
            return EDIT_PROFILE_FAILED_CONTEXT;
        }
        return SUCCESSFUL_PROFILE_CONTEXT;
    }
}
