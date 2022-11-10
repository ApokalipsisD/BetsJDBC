package com.bets.controller.command.impl;

import com.bets.controller.command.api.Command;
import com.bets.controller.command.api.RequestContext;
import com.bets.controller.command.api.ResponseContext;
import jakarta.servlet.http.HttpSession;

//import javax.servlet.http.HttpSession;

public class DefaultCommand implements Command {
    private static final Command INSTANCE = new DefaultCommand();
    private static final String PAGE_PATH = "/WEB-INF/jsp/main.jsp";
    private static final ResponseContext SHOW_DEFAULT_PAGE_CONTEXT = new ResponseContext() {
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
        HttpSession session = context.getCurrentSession().get();
//        String language = (String) session.getAttribute("language");

        return SHOW_DEFAULT_PAGE_CONTEXT;
    }
}
