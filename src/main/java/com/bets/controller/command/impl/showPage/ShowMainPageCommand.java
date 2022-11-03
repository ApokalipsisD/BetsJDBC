package com.bets.controller.command.impl.showPage;

import com.bets.controller.command.api.Command;
import com.bets.controller.command.api.RequestContext;
import com.bets.controller.command.api.ResponseContext;
import com.bets.controller.command.impl.DefaultCommand;

public class ShowMainPageCommand implements Command {
    private static final Command INSTANCE = new DefaultCommand();
    private static final String PAGE_PATH = "/WEB-INF/matches.jsp";
    private static final ResponseContext SHOW_MAIN_PAGE_CONTEXT = new ResponseContext() {
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
        System.out.println("hello from show main");
        return SHOW_MAIN_PAGE_CONTEXT;
    }
}