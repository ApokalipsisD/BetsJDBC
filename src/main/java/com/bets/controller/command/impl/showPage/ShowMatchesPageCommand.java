package com.bets.controller.command.impl.showPage;

import com.bets.controller.command.api.Command;
import com.bets.controller.command.api.RequestContext;
import com.bets.controller.command.api.ResponseContext;
import com.bets.dao.model.GameStatus;
import com.bets.service.api.MatchService;
import com.bets.service.api.TeamService;
import com.bets.service.dto.MatchDto;
import com.bets.service.dto.TeamDto;
import com.bets.service.exception.ServiceException;
import com.bets.service.impl.MatchServiceImpl;
import com.bets.service.impl.TeamServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bets.controller.command.Attributes.DELIMITER;
import static com.bets.controller.command.Attributes.ERROR_ATTRIBUTE;
import static com.bets.controller.command.Attributes.MATCHES_ATTRIBUTE;
import static com.bets.controller.command.Attributes.TEAMS_ATTRIBUTE;

public class ShowMatchesPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowMatchesPageCommand.class);

    private static final Command INSTANCE = new ShowMatchesPageCommand();
    private static final MatchService<MatchDto, Integer> matchService = new MatchServiceImpl();
    private static final TeamService<TeamDto, Integer> teamService = new TeamServiceImpl();
//    private static final CourseServiceImpl catalog = new CourseServiceImpl();
//    private static final AccountServiceImpl accountService = new AccountServiceImpl();

    private static final String PAGE_PATH = "/WEB-INF/jsp/matches.jsp";
    private static final String ERROR_PAGE_PATH = "/WEB-INF/jsp/error.jsp";


    private static final ResponseContext SHOW_COURSES_PAGE_CONTEXT = new ResponseContext() {
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

    @Override
    public ResponseContext execute(RequestContext context) {
        HttpSession session = context.getCurrentSession().get();

        Map<Integer, String> teamMap = new HashMap<>();
        List<MatchDto> list;
        List<TeamDto> teamDtoList;
        try {
            list = matchService.getAll();
            teamDtoList = teamService.getAll();
            Timestamp currentTime = new Timestamp(new Date().getTime());
            for (MatchDto matchDto : list) {
                if (matchDto.getDate().after(currentTime) && matchDto.getStatus() != GameStatus.COMING) {
                    matchDto.setStatus(GameStatus.COMING);
                    matchService.updateMatchStatus(matchDto);
                }
                if (matchDto.getDate().before(currentTime) && matchDto.getStatus() != GameStatus.FINISHED) {
                    matchDto.setStatus(GameStatus.FINISHED);
                    if (((Math.random() <= 0.5) ? 1 : 2) == 1) {
                        matchDto.setFirstTeamScore(16);
                        matchDto.setSecondTeamScore((int) (Math.random() * 14));
                    } else {
                        matchDto.setFirstTeamScore((int) (Math.random() * 14));
                        matchDto.setSecondTeamScore(16);
                    }
                    matchService.update(matchDto);
                }
            }
            teamDtoList.forEach(teamDto -> teamMap.put(teamDto.getId(), teamDto.getName()));

            session.setAttribute(MATCHES_ATTRIBUTE, list);
            session.setAttribute(TEAMS_ATTRIBUTE, teamMap);
        } catch (ServiceException e) {
            logger.error(ERROR_ATTRIBUTE + DELIMITER + e.getMessage());
            context.addAttributeToJsp(ERROR_ATTRIBUTE, e.getMessage());
        }
        return SHOW_COURSES_PAGE_CONTEXT;
    }

}
