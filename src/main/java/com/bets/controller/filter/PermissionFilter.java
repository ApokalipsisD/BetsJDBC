package com.bets.controller.filter;

import com.bets.controller.command.ApplicationCommand;
import com.bets.dao.model.Role;
import com.bets.service.dto.UserDto;
import com.bets.service.exception.ServiceException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

@WebFilter(urlPatterns = "/controller")
public class PermissionFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(PermissionFilter.class);

    private static final String ERROR_PAGE = "/controller?command=show_error_page";
    private static final String SESSION_ERROR_PAGE = "/controller?command=show_login";
    private static final String USER_ATTRIBUTE = "user";
    private static final String COMMAND_ATTRIBUTE = "command";

    private final Map<Role, Set<ApplicationCommand>> commandsByRole;

    public PermissionFilter() {
        this.commandsByRole = new EnumMap<>(Role.class);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final ApplicationCommand command = ApplicationCommand.getCommands(req.getParameter(COMMAND_ATTRIBUTE));
        final HttpSession session = req.getSession(false);
        Role currentRole = null;
        try {
            currentRole = extractRoleFromSession(session);
        } catch (ServiceException e) {
            logger.error(e.getMessage() + e);
        }
        final Set<ApplicationCommand> allowedCommands;
        allowedCommands = commandsByRole.get(currentRole);
        if (allowedCommands.contains(command)) {
            filterChain.doFilter(request, response);
        } else {
            if (session != null && !session.isNew()) {
                ((HttpServletResponse) response).sendRedirect(ERROR_PAGE);
            } else {
                ((HttpServletResponse) response).sendRedirect(SESSION_ERROR_PAGE);
            }
        }
    }


    @Override
    public void init(FilterConfig filterConfig) {
        for (ApplicationCommand command : ApplicationCommand.values()) {
            for (Role allowedRole : command.getAllowRoles()) {
                Set<ApplicationCommand> commands = commandsByRole.computeIfAbsent(allowedRole, k -> EnumSet.noneOf(ApplicationCommand.class));
                commands.add(command);
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private Role extractRoleFromSession(HttpSession session) throws ServiceException {
        return session != null &&
                (session.getAttribute(USER_ATTRIBUTE) != null)
                ? ((UserDto) session.getAttribute(USER_ATTRIBUTE)).getRole()
                : Role.UNAUTHORISED;
    }
}
