package com.bets.controller.servlet;

import com.bets.controller.command.RequestContextImpl;
import com.bets.controller.command.api.Command;
import com.bets.controller.command.api.ResponseContext;
import com.bets.service.connectionPoolService.ConnectionPoolService;
import com.bets.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="controller",urlPatterns={"/controller"})
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);
    private final ConnectionPoolService pool = ConnectionPoolService.getInstance();
    public static final String COMMAND_PARAMETER_NAME = "command";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(1);
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(2);
        Command command = Command.of(request.getParameter(COMMAND_PARAMETER_NAME));
        ResponseContext commandResult = command.execute(new RequestContextImpl(request));
        if (commandResult.isRedirect()) {
            response.sendRedirect(commandResult.getPage());
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(commandResult.getPage());
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    public void init() {
        try {
            System.out.println(3);
            pool.initialize();
        } catch (ServiceException e) {
            logger.error(e.getMessage() + e);
        }
    }
    
    @Override
    public void destroy() {
        super.destroy();
        try {
            pool.shutDown();
        } catch (ServiceException e) {
            logger.error(e.getMessage() + e);
        }
    }
}