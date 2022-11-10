package com.bets.controller.command.impl.showPage;

import com.bets.controller.command.api.Command;
import com.bets.controller.command.api.RequestContext;
import com.bets.controller.command.api.ResponseContext;

public class ShowSignUpPageCommand implements Command {
    private static final Command INSTANCE = new ShowSignUpPageCommand();
    private static final String PAGE_PATH = "/WEB-INF/jsp/signup.jsp";
    private static final ResponseContext SHOW_SIGN_UP_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return PAGE_PATH;
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
        return SHOW_SIGN_UP_PAGE_CONTEXT;
    }
}