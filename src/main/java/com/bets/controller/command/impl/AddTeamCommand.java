package com.bets.controller.command.impl;

import com.bets.controller.command.api.Command;
import com.bets.controller.command.api.RequestContext;
import com.bets.controller.command.api.ResponseContext;
import com.bets.service.api.TeamService;
import com.bets.service.dto.TeamDto;
import com.bets.service.exception.ServiceException;
import com.bets.service.impl.TeamServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

import static com.bets.controller.command.Attributes.DELIMITER;
import static com.bets.controller.command.Attributes.ERROR_ATTRIBUTE;
import static com.bets.controller.command.Attributes.MESSAGE;
import static com.bets.controller.command.Attributes.TEAMS_ATTRIBUTE;

public class AddTeamCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddTeamCommand.class);

    private static final Command INSTANCE = new AddTeamCommand();
    //    private static final MatchService<MatchDto, Integer> matchService = new MatchServiceImpl();
    private static final TeamService<TeamDto, Integer> teamService = new TeamServiceImpl();

    private static final String PAGE_PATH = "/controller?command=show_matches";
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
        Map<Integer, String> map = (Map<Integer, String>) session.getAttribute(TEAMS_ATTRIBUTE);
//        System.out.println(map);
        String teamName = context.getParameterByName("addTeam");
//        System.out.println(teamName);

        TeamDto teamDto;
        List<TeamDto> list;
        try {
            list = teamService.getAll();
            for (TeamDto teamDtoElement : list) {
                if (teamName.equalsIgnoreCase(teamDtoElement.getName())) {
                    throw new ServiceException("That team already exists");
                }
            }

            teamDto = teamService.save(new TeamDto(teamName));
            map.put(teamDto.getId(), teamDto.getName());
            session.setAttribute(TEAMS_ATTRIBUTE, map);


            context.addAttributeToJsp(MESSAGE, "Team added successfully");
        } catch (ServiceException e) {
            logger.error(ERROR_ATTRIBUTE + DELIMITER + e.getMessage());
            context.addAttributeToJsp(ERROR_ATTRIBUTE, e.getMessage());
        }
//        pagePath = context.getContextPath() + context.getHeader();
        return SUCCESSFUL_ADD_TEAM_CONTEXT;
    }
}
