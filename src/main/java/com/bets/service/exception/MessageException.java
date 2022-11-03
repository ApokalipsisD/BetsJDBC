package com.bets.service.exception;

public interface MessageException {
    String INCORRECT_LOGIN_LENGTH_EXCEPTION = "Incorrect login length (login must be greater than 3 and less than 20)";
    String INCORRECT_LOGIN_EXCEPTION = "Incorrect login (login must be greater than 3 and less than 20 and must not contain inaccessible characters)";
    String LOGIN_IS_NULL_EXCEPTION = "Login is null";
    String PASSWORD_IS_NULL_EXCEPTION = "Password is null";
    String INCORRECT_PASSWORD_LENGTH_EXCEPTION = "Incorrect password length (password must be greater than 8 and less than 20)";
    String INCORRECT_PASSWORD_EXCEPTION = "Incorrect password (password must contain at least one number, one lowercase and one uppercase letter, minimum password length 8)";
    String ID_IS_NULL_EXCEPTION = "Id is null";
    String USER_NOT_FOUND_EXCEPTION = "User not found";
    String BET_NOT_FOUND_EXCEPTION = "Bet not found";
    String TEAM_NOT_FOUND_EXCEPTION = "Team not found";
    String MATCH_NOT_FOUND_EXCEPTION = "Match not found";
    String INCORRECT_ID_EXCEPTION = "Incorrect id";

    String INCORRECT_AGE_EXCEPTION = "Incorrect age";

    String INCORRECT_BALANCE_EXCEPTION = "Incorrect balance exception";
    String TEAM_IS_NULL_EXCEPTION = "Team name is null";
    String BALANCE_IS_NULL_EXCEPTION = "Balance is null";
    String COEFFICIENT_IS_NULL_EXCEPTION = "Coefficient is null";
    String BET_IS_NULL_EXCEPTION = "Bet is null";
    String SCORE_IS_NULL_EXCEPTION = "Score is null";

    String INCORRECT_FIRST_NAME_EXCEPTION = "First name must start with an uppercase letter and contain letters of only one language";
    String INCORRECT_COEFFICIENT_EXCEPTION = "Incorrect coefficient";
    String INCORRECT_BET_EXCEPTION = "Incorrect bet";
    String INCORRECT_EARNINGS_EXCEPTION = "Incorrect earnings";
    String INCORRECT_SCORE_EXCEPTION = "Incorrect score";
    String DATE_IS_NULL_EXCEPTION = "Date is null";
    String INCORRECT_DATE_EXCEPTION = "Incorrect date";
    String INCORRECT_FIRST_NAME_LENGTH_EXCEPTION = "Incorrect first name length (first name must be greater than 2 and less than 20)";
    String LAST_NAME_IS_NULL_EXCEPTION = "Last name is null";
    String INCORRECT_LAST_NAME_EXCEPTION = "Last name must start with an uppercase letter and contain letters of only one language";
    String INCORRECT_LAST_NAME_LENGTH_EXCEPTION = "Incorrect last name length (last name must be greater than 2 and less than 25)";
    String EMAIL_IS_NULL_EXCEPTION = "Email is null";
    String INCORRECT_EMAIL_EXCEPTION = "Incorrect email";
    String BIRTH_DATE_IS_NULL_EXCEPTION = "Birth date is null";
    String ROLE_ID_IS_NULL_EXCEPTION = "Role id is null";
    String USER_ID_IS_NULL_EXCEPTION = "User id is null";
    String ACCOUNT_NOT_FOUND_EXCEPTION = "Account not found";

    String TITLE_IS_NULL_EXCEPTION = "Title is null";
    String DESCRIPTION_IS_NULL_EXCEPTION = "Description is null";
    String START_DATE_IS_NULL_EXCEPTION = "Start date is null";
    String END_DATE_IS_NULL_EXCEPTION = "End date is null";
    String COURSE_STATUS_IS_NULL = "Course status is null";
    String TEACHER_ID_IS_NULL = "Teacher id is null";
    String COURSE_NOT_FOUND_EXCEPTION = "Course not found";
    String START_AND_END_DATE_EXCEPTION = "Incorrect start or end date";
    String INCORRECT_TEACHER_ID_EXCEPTION = "Incorrect teacher id";
    String INCORRECT_COURSE_STATUS_EXCEPTION = "Incorrect course status";

    String COURSE_ID_IS_NULL_EXCEPTION = "Course id is null";
    String STUDENT_ID_IS_NULL_EXCEPTION = "Student id is null";
    String GRADE_IS_NULL_EXCEPTION = "Grade is null";
    String INCORRECT_GRADE_EXCEPTION = "Incorrect grade";
    String ATTENDANCE_IS_NULL_EXCEPTION = "Attendance is null";
    String INCORRECT_ATTENDANCE_EXCEPTION = "Incorrect attendance";
    String REVIEW_IS_NULL_EXCEPTION = "Review is null";
    String REVIEW_NOT_FOUND_EXCEPTION = "Review not found";
    String INCORRECT_COURSE_ID_EXCEPTION = "Incorrect course id";
    String INCORRECT_STUDENT_ID_EXCEPTION = "Incorrect student id";

    String APPLICATION_DATE_IS_NULL_EXCEPTION = "Application date is null";
    String ENTER_DATE_EXCEPTION = "Enter data";
    String PASSWORD_MISMATCH_MESSAGE = "Password mismatch";
    String INCORRECT_PASSWORD_MESSAGE = "Incorrect password";
    String REPEATING_PASSWORD_MESSAGE = "The new password cannot be the same as the previous one";
    String LOGIN_IS_TAKEN_EXCEPTION = "Login is already taken";

}
