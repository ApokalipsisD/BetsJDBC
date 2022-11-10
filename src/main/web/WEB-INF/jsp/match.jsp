<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="jwdt" uri="jwdTags" %>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'en'}"/>--%>
<%--<fmt:setBundle basename="locale" var="loc"/>--%>
<%--<fmt:message bundle="${loc}" key="start" var="start"/>--%>
<%--<fmt:message bundle="${loc}" key="end" var="end"/>--%>
<%--<fmt:message bundle="${loc}" key="drop" var="drop"/>--%>
<%--<fmt:message bundle="${loc}" key="join" var="join"/>--%>
<%--<fmt:message bundle="${loc}" key="change" var="change"/>--%>
<%--<fmt:message bundle="${loc}" key="users" var="users"/>--%>
<%--<fmt:message bundle="${loc}" key="review" var="review"/>--%>
<%--<fmt:message bundle="${loc}" key="grade" var="grade"/>--%>
<%--<fmt:message bundle="${loc}" key="enterGrade" var="enterGrade"/>--%>
<%--<fmt:message bundle="${loc}" key="attendance" var="attendance"/>--%>
<%--<fmt:message bundle="${loc}" key="enterAttendance" var="enterAttendance"/>--%>
<%--<fmt:message bundle="${loc}" key="changeReview" var="changeReview"/>--%>
<%--<fmt:message bundle="${loc}" key="deleteReview" var="deleteReview"/>--%>
<%--<fmt:message bundle="${loc}" key="deleteReviewMessage" var="deleteReviewMessage"/>--%>
<%--<fmt:message bundle="${loc}" key="changeCourse" var="changeCourse"/>--%>
<%--<fmt:message bundle="${loc}" key="deleteCourse" var="deleteCourse"/>--%>
<%--<fmt:message bundle="${loc}" key="deleteCourseMessage" var="deleteCourseMessage"/>--%>
<%--<fmt:message bundle="${loc}" key="status" var="statusS"/>--%>
<%--<fmt:message bundle="${loc}" key="delete" var="delete"/>--%>
<%--<fmt:message bundle="${loc}" key="close" var="close"/>--%>
<%--<fmt:message bundle="${loc}" key="save" var="save"/>--%>
<%--<fmt:message bundle="${loc}" key="title" var="titleS"/>--%>
<%--<fmt:message bundle="${loc}" key="beginning" var="beginning"/>--%>
<%--<fmt:message bundle="${loc}" key="inputStartDate" var="inputStartDate"/>--%>
<%--<fmt:message bundle="${loc}" key="ending" var="ending"/>--%>
<%--<fmt:message bundle="${loc}" key="inputEndDate" var="inputEndDate"/>--%>
<%--<fmt:message bundle="${loc}" key="teacher" var="teacherN"/>--%>
<%--<fmt:message bundle="${loc}" key="selectTeacher" var="selectTeacher"/>--%>
<%--<fmt:message bundle="${loc}" key="description" var="descriptionS"/>--%>

<%@ page import="com.bets.dao.model.Role" %>
<%@ page import="com.bets.dao.model.GameStatus" %>
<%--<%@ page import="com.epam.jwd.dao.entity.Status" %>--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <title>Match</title>
    <style>

        <%@include file="/WEB-INF/css/bootstrap.min.css" %>
        .main .drop{
            border-color: yellow;
            background-color: yellow;
            color: black;
        }
        .users{
            margin-top: 40px;
        }

    </style>

</head>
<body>
<%@include file="header.jsp"%>
<main class="main" style="background-color: #dee1e3">
    <div class="container" style="margin-top: 30px">
        <p style="color: red; text-align: center;">${error}</p>
<%--        <p style="font-size: 20px; color: red;">${requestScope.error}</p>--%>
        <div style="display: flex; justify-content: space-between; text-align: center;">

            <form action="${pageContext.request.contextPath}/controller?command=make_bet" method="post" style="margin-left: 30px">
                <input type="hidden" name="teamYouBet" value="${match.firstTeam}">
                <input type="hidden" name="coefficientYouBet" value="${match.firstCoefficient}">
                <input type="hidden" name="id" value="${match.id}">


                <h2>${sessionScope.teams.get(match.firstTeam)}</h2>
                <div>${match.firstCoefficient}</div>
                <c:if test="${match.status ne GameStatus.FINISHED}">
                    <label for="betFirstTeam" style="margin-top: 30px">Make yor bet:</label>
                    <input type="number" name="betTeam" class="form-control" id="betFirstTeam" min="0" required>

                    <button style="margin-top: 15px" class="btn btn-primary" type="submit">Confirm</button>

                </c:if>
            </form>

            <div style="display: flex; justify-content: space-between;">
                <h1>${match.firstTeamScore}</h1>
                <div style="margin-left: 20px; margin-right: 20px; display: grid; align-items: center;">
                    <div>${match.status}</div>
                    <div>
                        <fmt:formatDate value="${match.date}" pattern="HH:mm:ss dd-MM-yyyy" />
                    </div>
                </div>
                <h1>${match.secondTeamScore}</h1>
            </div>

            <form action="${pageContext.request.contextPath}/controller?command=make_bet" method="post" style="margin-left: 30px" style="margin-right: 30px">
                <input type="hidden" name="teamYouBet" value="${match.secondTeam}">
                <input type="hidden" name="coefficientYouBet" value="${match.secondCoefficient}">
                <input type="hidden" name="id" value="${match.id}">

                <h2>${sessionScope.teams.get(match.secondTeam)}</h2>
                <div>${match.secondCoefficient}</div>
                <c:if test="${match.status ne GameStatus.FINISHED}">
                    <label for="betSecondTeam" style="margin-top: 30px">Make yor bet:</label>
                    <input type="number" name="betTeam" class="form-control" id="betSecondTeam" min="0" required>

                    <button style="margin-top: 15px" class="btn btn-primary" type="submit">Confirm</button>

                </c:if>

            </form>


        </div>

    </div>
<%--    <div class="jumbotron" style="height: 100%">--%>

    <c:if test="${not empty requestScope.message}">
        <script>
            alert("${requestScope.message}")
            window.location = '${pageContext.request.contextPath}/controller?command=match&id=${match.id}';
        </script>
    </c:if>
</main>
<%@include file="footer.jsp"%>
</body>
</html>

<%--            <c:choose>--%>
<%--                <c:when test="${sessionScope.account.role eq Role.ADMIN or sessionScope.account.role eq Role.TEACHER}">--%>
<%--                    <a class="btn btn-primary btn-lg" href="#" data-toggle="modal" data-target="#changeCourse"--%>
<%--                       role="button">${change}Make bet</a>--%>
<%--                    <a class="btn btn-primary btn-lg" href="#" data-toggle="modal" data-target="#deleteCourse"--%>
<%--                       role="button">${delete}</a>--%>
<%--                    <c:if test="${not empty studentsOnCourse}">--%>
<%--                        <div class="users">--%>
<%--                            <h4>${users}:</h4>--%>
<%--                            <ul class="list-group">--%>
<%--                                <c:forEach items="${studentsOnCourse}" var="entry">--%>
<%--                                    <div>--%>
<%--                                        <form action="${pageContext.request.contextPath}/controller?command=review"--%>
<%--                                              method="post">--%>
<%--                                            <div class="modal fade" id="review${entry.key.id}"--%>
<%--                                                 data-backdrop="static" data-keyboard="false"--%>
<%--                                                 tabindex="-1" aria-labelledby="staticBackdropLabel1"--%>
<%--                                                 aria-hidden="true">--%>
<%--                                                <input type="hidden" name="studentIdOnCourse" value="${entry.key.id}">--%>

<%--                                                <div class="modal-dialog">--%>
<%--                                                    <div class="modal-content">--%>
<%--                                                        <div class="modal-header">--%>
<%--                                                            <h5 class="modal-title" id="review">${review}</h5>--%>
<%--                                                            <button type="button" class="close" data-dismiss="modal"--%>
<%--                                                                    aria-label="Close">--%>
<%--                                                                <span aria-hidden="true">&times;</span>--%>
<%--                                                            </button>--%>
<%--                                                        </div>--%>

<%--                                                        <div class="modal-body">--%>
<%--                                                            <div class="form-group">--%>
<%--                                                                <label for="grade">${grade}:</label>--%>
<%--                                                                <input type="number" name="grade" class="form-control"--%>
<%--                                                                       id="grade"--%>
<%--                                                                       aria-label="Grade"--%>
<%--                                                                       aria-describedby="basic-addon1" required pattern="^[0-9]+$"--%>
<%--                                                                       min="1" max="10"--%>
<%--                                                                       title="${enterGrade}">--%>
<%--                                                            </div>--%>
<%--                                                            <input type="hidden" name="id" value="${id}">--%>
<%--                                                            <div class="form-group">--%>
<%--                                                                <label for="attendance">${attendance} %:</label>--%>
<%--                                                                <input type="number" name="attendance"--%>
<%--                                                                       class="form-control"--%>
<%--                                                                       id="attendance"--%>
<%--                                                                       aria-label="attendance"--%>
<%--                                                                       aria-describedby="basic-addon1" required pattern="^[0-9]+$"--%>
<%--                                                                       min="1" max="100"--%>
<%--                                                                       title="${enterAttendance}">--%>
<%--                                                            </div>--%>

<%--                                                            <div class="form-group">--%>
<%--                                                                <label for="reviewText">${review}</label>--%>
<%--                                                                <textarea class="form-control" name="reviewText"--%>
<%--                                                                          id="reviewText"--%>
<%--                                                                          rows="3" required></textarea>--%>
<%--                                                            </div>--%>
<%--                                                        </div>--%>


<%--                                                        <div class="modal-footer">--%>
<%--                                                            <button type="button" class="btn btn-secondary"--%>
<%--                                                                    data-dismiss="modal">${close}--%>
<%--                                                            </button>--%>
<%--                                                            <button type="submit" class="btn btn-primary">${save}--%>
<%--                                                            </button>--%>
<%--                                                        </div>--%>

<%--                                                    </div>--%>
<%--                                                </div>--%>
<%--                                            </div>--%>
<%--                                        </form>--%>

<%--                                        <form action="${pageContext.request.contextPath}/controller?command=change_review"--%>
<%--                                              method="post">--%>
<%--                                            <div class="modal fade" id="changeReview${entry.key.id}"--%>
<%--                                                 data-backdrop="static" data-keyboard="false"--%>
<%--                                                 tabindex="-1" aria-labelledby="staticBackdropLabel1"--%>
<%--                                                 aria-hidden="true">--%>
<%--                                                <input type="hidden" name="studentIdOnCourse" value="${entry.key.id}">--%>
<%--                                                <input type="hidden" name="reviewId" value="${reviews.get(entry.key.id).id}">--%>

<%--                                                <div class="modal-dialog">--%>
<%--                                                    <div class="modal-content">--%>
<%--                                                        <div class="modal-header">--%>
<%--                                                            <h5 class="modal-title" id="review1">${changeReview}</h5>--%>
<%--                                                            <button type="button" class="close" data-dismiss="modal"--%>
<%--                                                                    aria-label="Close">--%>
<%--                                                                <span aria-hidden="true">&times;</span>--%>
<%--                                                            </button>--%>
<%--                                                        </div>--%>

<%--                                                        <div class="modal-body">--%>
<%--                                                            <div class="form-group">--%>
<%--                                                                <label for="grade">${grade}</label>--%>
<%--                                                                <input type="text" name="gradeChange" class="form-control"--%>
<%--                                                                       id="grade1"--%>
<%--                                                                       aria-label="Grade"--%>
<%--                                                                       aria-describedby="basic-addon1"--%>
<%--                                                                       value="${reviews.get(entry.key.id).grade}" required>--%>
<%--                                                            </div>--%>
<%--                                                            <input type="hidden" name="id" value="${id}">--%>
<%--                                                            <div class="form-group">--%>
<%--                                                                <label for="attendance">Attendance %:</label>--%>
<%--                                                                <input type="text" name="attendanceChange"--%>
<%--                                                                       class="form-control"--%>
<%--                                                                       id="attendance1"--%>
<%--                                                                       value="${reviews.get(entry.key.id).attendance}"--%>
<%--                                                                       aria-label="attendance"--%>
<%--                                                                       aria-describedby="basic-addon1" required>--%>
<%--                                                            </div>--%>

<%--                                                            <div class="form-group">--%>
<%--                                                                <label for="reviewText">${review}</label>--%>
<%--                                                                <textarea class="form-control" name="reviewTextChange"--%>
<%--                                                                          id="reviewText1"--%>
<%--                                                                          rows="3">${reviews.get(entry.key.id).review}</textarea>--%>
<%--                                                            </div>--%>
<%--                                                        </div>--%>


<%--                                                        <div class="modal-footer">--%>
<%--                                                            <button type="button" class="btn btn-secondary"--%>
<%--                                                                    data-dismiss="modal">${close}--%>
<%--                                                            </button>--%>
<%--                                                            <button type="submit" class="btn btn-primary">${save}</button>--%>
<%--                                                        </div>--%>

<%--                                                    </div>--%>
<%--                                                </div>--%>
<%--                                            </div>--%>
<%--                                        </form>--%>

<%--                                        <form action="${pageContext.request.contextPath}/controller?command=delete_review" method="post">--%>
<%--                                            <div class="modal fade" tabindex="-1" id="deleteReview${entry.key.id}">--%>
<%--                                                <div class="modal-dialog">--%>
<%--                                                    <div class="modal-content">--%>
<%--                                                        <div class="modal-header">--%>
<%--                                                            <h5 class="modal-title">${deleteReview}</h5>--%>
<%--                                                            <input type="hidden" name="id" value="${id}">--%>
<%--                                                            <input type="hidden" name="reviewId" value="${reviews.get(entry.key.id).id}">--%>

<%--                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
<%--                                                                <span aria-hidden="true">&times;</span>--%>
<%--                                                            </button>--%>
<%--                                                        </div>--%>
<%--                                                        <div class="modal-body">--%>
<%--                                                            <p>${deleteReviewMessage}</p>--%>
<%--                                                        </div>--%>
<%--                                                        <div class="modal-footer">--%>
<%--                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">${close}</button>--%>
<%--                                                            <button type="submit" class="btn btn-primary">${deleteReview}</button>--%>
<%--                                                        </div>--%>
<%--                                                    </div>--%>
<%--                                                </div>--%>
<%--                                            </div>--%>
<%--                                        </form>--%>
<%--                                    </div>--%>

<%--                                    <li class="list-group-item">${entry.key.login}--%>
<%--                                        <c:if test="${status ne Status.Coming}">--%>
<%--                                            <c:choose>--%>
<%--                                                <c:when test="${entry.value eq true}">--%>
<%--                                                    <div class="pull-right">--%>
<%--                                                        <a class="btn btn-primary" href="#"--%>
<%--                                                           data-toggle="modal"--%>
<%--                                                           data-target="#changeReview${entry.key.id}"--%>
<%--                                                           role="button">${changeReview}</a>--%>
<%--                                                        <a class="btn btn-primary" href="#"--%>
<%--                                                           data-toggle="modal"--%>
<%--                                                           data-target="#deleteReview${entry.key.id}"--%>
<%--                                                           role="button">${deleteReview}</a>--%>
<%--                                                    </div>--%>

<%--                                                </c:when>--%>
<%--                                                <c:otherwise>--%>
<%--                                                    <button class="btn btn-primary pull-right" role="button"--%>
<%--                                                            type="button"--%>
<%--                                                            data-toggle="modal"--%>
<%--                                                            data-target="#review${entry.key.id}"--%>
<%--                                                            name="studentIdOnCourse" value="${entry.key.id}">--%>
<%--                                                            ${review}--%>
<%--                                                    </button>--%>
<%--                                                </c:otherwise>--%>
<%--                                            </c:choose>--%>
<%--                                        </c:if>--%>

<%--                                    </li>--%>

<%--                                </c:forEach>--%>

<%--                            </ul>--%>
<%--                        </div>--%>
<%--                    </c:if>--%>
<%--                </c:when>--%>
<%--            </c:choose>--%>

<%--            <input type="hidden" name="id" value="${id}">--%>

<%--            <form action="${pageContext.request.contextPath}/controller?command=make_bet" method="post">--%>
<%--                <div class="modal fade" id="changeCourse" data-backdrop="static" data-keyboard="false"--%>
<%--                     tabindex="-1" aria-labelledby="staticBackdropLabel1" aria-hidden="true">--%>

<%--                    <div class="modal-dialog">--%>
<%--                        <div class="modal-content">--%>
<%--                            <div class="modal-header">--%>
<%--                                <h5 class="modal-title" id="staticBackdropLabel1">${changeCourse}</h5>--%>
<%--                                <button type="button" class="close" data-dismiss="modal"--%>
<%--                                        aria-label="Close">--%>
<%--                                    <span aria-hidden="true">&times;</span>--%>
<%--                                </button>--%>
<%--                            </div>--%>

<%--                            <div class="modal-body">--%>
<%--                                <div class="form-group">--%>
<%--                                    <label for="title1">${titleS}</label>--%>
<%--                                    <input type="text" name="title" class="form-control"--%>
<%--                                           id="title1"--%>
<%--                                           aria-label="${titleS}"--%>
<%--                                           aria-describedby="basic-addon1" value=${title} required>--%>
<%--                                </div>--%>
<%--                                <input type="hidden" name="id" value="${id}">--%>
<%--                                <div class="form-group">--%>
<%--                                    <label for="startDate1">${start}</label>--%>
<%--                                    <input type="date" name="startDate" class="form-control"--%>
<%--                                           id="startDate1" placeholder="${inputStartDate}"--%>
<%--                                           value=${startDate}--%>
<%--                                                   aria-label="startDate"--%>
<%--                                           aria-describedby="basic-addon1" required>--%>
<%--                                </div>--%>

<%--                                <div class="form-group">--%>
<%--                                    <label for="endDate1">${end}</label>--%>
<%--                                    <input type="date" name="endDate" class="form-control"--%>
<%--                                           id="endDate1" placeholder="${inputEndDate}"--%>
<%--                                           value=${endDate} aria-label="endDate"--%>
<%--                                           aria-describedby="basic-addon1" required>--%>
<%--                                </div>--%>

<%--                                <div class="form-group">--%>
<%--                                    <label for="teacher1">${teacherN}</label>--%>
<%--                                    <select class="form-control" name="teacher" id="teacher1">--%>
<%--                                        <c:forEach var="i" begin="0" end="${sessionScope.teachers.size()-1}">--%>
<%--                                            <c:choose>--%>
<%--                                                <c:when test="${sessionScope.teachers.get(i) eq teacher}">--%>
<%--                                                    <option selected value="${sessionScope.teachers.get(i).id}">${teacher.firstName} ${teacher.lastName}</option>--%>
<%--                                                </c:when>--%>
<%--                                                <c:otherwise>--%>
<%--                                                    <option value="${sessionScope.teachers.get(i).id}">${sessionScope.teachers.get(i).firstName} ${sessionScope.teachers.get(i).lastName}</option>--%>
<%--                                                </c:otherwise>--%>
<%--                                            </c:choose>--%>
<%--                                        </c:forEach>--%>
<%--                                    </select>--%>
<%--                                </div>--%>


<%--                                <div class="form-group">--%>
<%--                                    <label for="description1">${descriptionS}</label>--%>
<%--                                    <textarea class="form-control" name="description" id="description1"--%>
<%--                                              rows="3">${description}</textarea>--%>
<%--                                </div>--%>

<%--                            </div>--%>


<%--                            <div class="modal-footer">--%>
<%--                                <button type="button" class="btn btn-secondary" data-dismiss="modal">${close}</button>--%>
<%--                                <button type="submit" class="btn btn-primary">${save}</button>--%>
<%--                            </div>--%>

<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </form>--%>

<%--            <form action="${pageContext.request.contextPath}/controller?command=delete_course" method="post">--%>
<%--                <div class="modal fade" tabindex="-1" id="deleteCourse">--%>
<%--                    <div class="modal-dialog">--%>
<%--                        <div class="modal-content">--%>
<%--                            <div class="modal-header">--%>
<%--                                <h5 class="modal-title">${deleteCourse}</h5>--%>
<%--                                <input type="hidden" name="id" value="${id}">--%>
<%--                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
<%--                                    <span aria-hidden="true">&times;</span>--%>
<%--                                </button>--%>
<%--                            </div>--%>
<%--                            <div class="modal-body">--%>
<%--                                <p>${deleteCourseMessage}</p>--%>
<%--                            </div>--%>
<%--                            <div class="modal-footer">--%>
<%--                                <button type="button" class="btn btn-secondary" data-dismiss="modal">${close}</button>--%>
<%--                                <button type="submit" class="btn btn-primary">${deleteCourse}</button>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </form>--%>
<%--        </div>--%>