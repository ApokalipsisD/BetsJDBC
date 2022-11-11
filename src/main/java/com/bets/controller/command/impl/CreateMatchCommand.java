package com.bets.controller.command.impl;

import com.bets.controller.command.api.Command;
import com.bets.controller.command.api.RequestContext;
import com.bets.controller.command.api.ResponseContext;
import com.bets.dao.model.GameStatus;
import com.bets.service.api.MatchService;
import com.bets.service.dto.MatchDto;
import com.bets.service.exception.ServiceException;
import com.bets.service.impl.MatchServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

import static com.bets.controller.command.Attributes.DELIMITER;
import static com.bets.controller.command.Attributes.ERROR_ATTRIBUTE;
import static com.bets.controller.command.Attributes.MESSAGE;


public class CreateMatchCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CreateMatchCommand.class);

    private static final Command INSTANCE = new CreateMatchCommand();
    private static final MatchService<MatchDto, Integer> matchService = new MatchServiceImpl();
    private static final String PAGE_PATH = "/controller?command=show_matches";
    private static final String FAIL_PAGE_PATH = "/controller?command=show_matches";
    private static final String ERROR_PAGE_PATH = "/WEB-INF/jsp/error.jsp";

    private static final Integer startScore = 0;

    private static final ResponseContext SUCCESSFUL_CREATE_COURSE_CONTEXT = new ResponseContext() {
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

    private final ResponseContext FAIL_PAGE_CONTEXT = new ResponseContext() {
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
//        System.out.println(session.getAttribute("teams"));

        Integer firstTeam = Objects.nonNull(context.getParameterByName("firstTeam"))
                ? Integer.valueOf(context.getParameterByName("firstTeam")) : null;

        Integer secondTeam = Objects.nonNull(context.getParameterByName("secondTeam"))
                ? Integer.valueOf(context.getParameterByName("secondTeam")) : null;

        //check for null
        BigDecimal firstCoefficient = new BigDecimal(context.getParameterByName("firstCoef"));
        BigDecimal secondCoefficient = new BigDecimal(context.getParameterByName("secondCoef"));

        //todo separate method
        LocalDate date = LocalDate.parse(context.getParameterByName("date"));
        LocalTime time = LocalTime.parse(context.getParameterByName("time"));
        LocalDateTime localDateTime = LocalDateTime.of(date, time);
        Timestamp dateTime = Timestamp.valueOf(localDateTime);
        Timestamp currentTime = new Timestamp(new Date().getTime());

        Integer game = Objects.nonNull(context.getParameterByName("gameId"))
                ? Integer.valueOf(context.getParameterByName("gameId")) : null;


        GameStatus status;

        MatchDto matchDto;
        try {
            status = dateTime.before(currentTime)
                    ? GameStatus.FINISHED : dateTime.after(currentTime)
                    ? GameStatus.COMING : GameStatus.LIVE;
            matchService.getAll();

            matchDto = new MatchDto(firstTeam, secondTeam, firstCoefficient, secondCoefficient, startScore, startScore, dateTime, status.getId(), game);
            matchService.save(matchDto);

            context.addAttributeToJsp(MESSAGE, "Match created successfully");
        } catch (ServiceException e) {
            logger.error(ERROR_ATTRIBUTE + DELIMITER + e.getMessage());
            context.addAttributeToJsp(ERROR_ATTRIBUTE, e.getMessage());
            return FAIL_PAGE_CONTEXT;
        }

        return SUCCESSFUL_CREATE_COURSE_CONTEXT;
    }

}
