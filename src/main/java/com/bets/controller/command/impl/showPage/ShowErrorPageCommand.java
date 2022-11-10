package com.bets.controller.command.impl.showPage;

import com.bets.controller.command.api.Command;
import com.bets.controller.command.api.RequestContext;
import com.bets.controller.command.api.ResponseContext;

public class ShowErrorPageCommand implements Command {

    private static final Command INSTANCE = new ShowErrorPageCommand();
    private static final String PAGE_PATH = "/WEB-INF/jsp/error.jsp";

    private ShowErrorPageCommand() {
    }

    private static final ResponseContext SHOW_ERROR_PAGE_CONTEXT = new ResponseContext() {
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
    public ResponseContext execute(RequestContext    context) {
        return SHOW_ERROR_PAGE_CONTEXT;
    }
}
