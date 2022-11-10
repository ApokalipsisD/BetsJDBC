package com.bets.controller.command.impl.showPage;

import com.bets.controller.command.api.Command;
import com.bets.controller.command.api.RequestContext;
import com.bets.controller.command.api.ResponseContext;
import com.bets.dao.model.BetStatus;
import com.bets.dao.model.GameStatus;
import com.bets.service.api.MatchService;
import com.bets.service.api.TeamService;
import com.bets.service.api.UserBetOnMatchService;
import com.bets.service.dto.MatchDto;
import com.bets.service.dto.TeamDto;
import com.bets.service.dto.UserBetOnMatchDto;
import com.bets.service.dto.UserDto;
import com.bets.service.exception.ServiceException;
import com.bets.service.impl.MatchServiceImpl;
import com.bets.service.impl.TeamServiceImpl;
import com.bets.service.impl.UserBetOnMatchServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

import static com.bets.controller.command.Attributes.CURRENT_USER;
import static com.bets.controller.command.Attributes.DELIMITER;
import static com.bets.controller.command.Attributes.ERROR_ATTRIBUTE;

public class ShowMyBetsPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowMyBetsPageCommand.class);

    private static final Command INSTANCE = new ShowMyBetsPageCommand();
    private static final MatchService<MatchDto, Integer> matchService = new MatchServiceImpl();
    private static final TeamService<TeamDto, Integer> teamService = new TeamServiceImpl();
    private static final UserBetOnMatchService<UserBetOnMatchDto, Integer> betService = new UserBetOnMatchServiceImpl();
    private static final String PAGE_PATH = "/WEB-INF/jsp/myBets.jsp";
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
        UserDto userDto = (UserDto) session.getAttribute(CURRENT_USER);

        List<UserBetOnMatchDto> list;
        try {

            list = betService.getByUserId(userDto.getId());
            for (UserBetOnMatchDto userBetOnMatchDto : list) {
                MatchDto matchDto = matchService.getById(userBetOnMatchDto.getMatchId());
                Integer winner;

                if (matchDto.getStatus().equals(GameStatus.FINISHED)
                        && Objects.isNull(userBetOnMatchDto.getEarnings())) {
                    winner = matchDto.getFirstTeamScore().equals(16)
                            ? matchDto.getFirstTeam()
                            : matchDto.getSecondTeam();

//                    userBetOnMatchDto.setEarnings(
//                            winner.equals(userBetOnMatchDto.getTeam())
//                                    ? userBetOnMatchDto.getBet().multiply(userBetOnMatchDto.getCoefficient()).setScale(2, RoundingMode.HALF_UP)
//                                    : BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP)
//                    );

                    if (winner.equals(userBetOnMatchDto.getTeam())) {
                        userBetOnMatchDto.setEarnings(userBetOnMatchDto.getBet().multiply(userBetOnMatchDto.getCoefficient()).setScale(2, RoundingMode.HALF_UP));
                        userBetOnMatchDto.setBetStatus(BetStatus.WIN);
                    } else {
                        userBetOnMatchDto.setEarnings(BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP));
                        userBetOnMatchDto.setBetStatus(BetStatus.LOSE);
                    }
                    if (userBetOnMatchDto.getBetStatus().equals(BetStatus.WIN)
//                            && Objects.isNull(userBetOnMatchDto.getEarnings())
                    ) {
                        userDto.setBalance(userDto.getBalance().add(userBetOnMatchDto.getEarnings()).setScale(2, RoundingMode.HALF_UP));
                    }
                    betService.updateEarningsAndUserBalance(userBetOnMatchDto, userDto);
                }
            }
            session.setAttribute("myBets", list);
            session.setAttribute(CURRENT_USER, userDto);
        } catch (ServiceException e) {
            logger.error(ERROR_ATTRIBUTE + DELIMITER + e.getMessage());
            context.addAttributeToJsp(ERROR_ATTRIBUTE, e.getMessage());
        }
        return SHOW_COURSES_PAGE_CONTEXT;
    }
}
