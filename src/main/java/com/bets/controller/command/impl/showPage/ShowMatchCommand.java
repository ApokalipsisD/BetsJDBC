package com.bets.controller.command.impl.showPage;

import com.bets.controller.command.api.Command;
import com.bets.controller.command.api.RequestContext;
import com.bets.controller.command.api.ResponseContext;
import com.bets.service.api.MatchService;
import com.bets.service.api.UserService;
import com.bets.service.dto.MatchDto;
import com.bets.service.dto.UserDto;
import com.bets.service.exception.ServiceException;
import com.bets.service.impl.MatchServiceImpl;
import com.bets.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.bets.controller.command.Attributes.CURRENT_USER;
import static com.bets.controller.command.Attributes.DELIMITER;
import static com.bets.controller.command.Attributes.ERROR_ATTRIBUTE;
import static com.bets.controller.command.Attributes.MATCH_ATTRIBUTE;

public class ShowMatchCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowMatchCommand.class);

    private static final Command INSTANCE = new ShowMatchCommand();
    private static final UserService<UserDto, Integer> user = new UserServiceImpl();
    private static final MatchService<MatchDto, Integer> matchService = new MatchServiceImpl();
//    private static final CourseServiceImpl catalog = new CourseServiceImpl();
//    private static final AccountServiceImpl account = new AccountServiceImpl();
//    private static final StudentHasCourseServiceImpl record = new StudentHasCourseServiceImpl();
//    private static final ReviewServiceImpl review = new ReviewServiceImpl();

    private static final String PAGE_PATH = "/WEB-INF/jsp/match.jsp";
    private static final String ERROR_PAGE_PATH = "/WEB-INF/jsp/error.jsp";

    private static final ResponseContext SUCCESSFUL_MATCH_CONTEXT = new ResponseContext() {
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
//        if (context.getHeader() == null) {
//            return ERROR_CONTEXT;
//        }
        HttpSession session = context.getCurrentSession().get();

        UserDto userDto = (UserDto) session.getAttribute(CURRENT_USER);
        Integer id = Integer.valueOf(context.getParameterByName("id"));
//        System.out.println(userDto);
//        System.out.println(id);
        MatchDto matchDto;


        try {
            matchDto = matchService.getById(id);
//            System.out.println(matchDto);
            context.addAttributeToJsp(MATCH_ATTRIBUTE, matchDto);
//            courseDto = catalog.getById(id);
//            context.addAttributeToJsp(TITLE_ATTRIBUTE, courseDto.getTitle());

//
//            if (!record.findRecordByCourseIdAndStudentId(courseDto.getId(), userDto.getId())) {
//                isRecordExists = true;
//            }
//            context.addAttributeToJsp(RECORD_ATTRIBUTE, isRecordExists);
//
//            studentsOnCourse = record.getRecordsByCourseId(courseDto.getId());
//
//            for (StudentHasCourseDto student : studentsOnCourse) {
//                UserDto userOnCourse = user.getById(student.getStudentId());
//                isReviewExists = !review.findReviewByCourseIdAndStudentId(courseDto.getId(), userOnCourse.getId());
//                mapUsers.put(userOnCourse, isReviewExists);
//            }
//            context.addAttributeToJsp(STUDENTS_ON_COURSE_ATTRIBUTE, mapUsers);
//
//            List<ReviewDto> reviewDtoList = review.getReviewsByCourseId(courseDto.getId());
//            reviewDtoList.forEach(review -> reviews.put(review.getStudentId(), review));
//            context.addAttributeToJsp(REVIEWS_ATTRIBUTE, reviews);
        } catch (ServiceException e) {
            logger.error(ERROR_ATTRIBUTE + DELIMITER + e.getMessage());
            context.addAttributeToJsp(ERROR_ATTRIBUTE, e.getMessage());
        }

        return SUCCESSFUL_MATCH_CONTEXT;
    }
}
