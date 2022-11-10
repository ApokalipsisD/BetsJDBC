<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="jwdt" uri="jwdTags" %>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<fmt:formatDate value="${bean.date}" pattern="yyyy-MM-dd HH:mm:ss" />--%>

<%--<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'en'}"/>--%>
<%--<fmt:setBundle basename="locale" var="loc"/>--%>
<%--<fmt:message bundle="${loc}" key="catalog" var="catalog"/>--%>
<%--<fmt:message bundle="${loc}" key="createCourse" var="createCourse"/>--%>
<%--<fmt:message bundle="${loc}" key="title" var="title"/>--%>
<%--<fmt:message bundle="${loc}" key="inputTitle" var="inputTitle"/>--%>
<%--<fmt:message bundle="${loc}" key="beginning" var="beginning"/>--%>
<%--<fmt:message bundle="${loc}" key="inputStartDate" var="inputStartDate"/>--%>
<%--<fmt:message bundle="${loc}" key="ending" var="ending"/>--%>
<%--<fmt:message bundle="${loc}" key="inputEndDate" var="inputEndDate"/>--%>
<%--<fmt:message bundle="${loc}" key="teacher" var="teacherN"/>--%>
<%--<fmt:message bundle="${loc}" key="selectTeacher" var="selectTeacher"/>--%>
<%--<fmt:message bundle="${loc}" key="description" var="descriptionS"/>--%>
<%--<fmt:message bundle="${loc}" key="close" var="close"/>--%>
<%--<fmt:message bundle="${loc}" key="save" var="save"/>--%>
<%--<fmt:message bundle="${loc}" key="status" var="status"/>--%>
<%--<%@ page contentType="text/html; charset=UTF-8" language="java" %>--%>

<%--<html>--%>
<%--<head>--%>
<%--    <title>Quick Servlet Demo</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<%@include file="header.jsp" %>--%>
<%--Hello from main page--%>


<%--<a href="${pageContext.request.contextPath}/controller">Click here to send GET request</a>--%>

<%--<br/><br/>--%>

<%--<form action="${pageContext.request.contextPath}/controller" method="post">--%>
<%--    Width: <input type="text" size="5" name="width"/>--%>
<%--    Height <input type="text" size="5" name="height"/>--%>
<%--    <input type="submit" value="Calculate" />--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>

<%@ page import="com.bets.dao.model.Role" %>
<%@ page import="com.bets.dao.model.Game" %>
<%@ page import="com.bets.dao.model.GameStatus" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <title>${catalog}Matches</title>
    <style>

        <%@include file="/WEB-INF/css/bootstrap.min.css" %>
        <%@include file="/WEB-INF/css/catalog.css" %>
        <%--            <%@include file="/WEB-INF/css/matches.css" %>--%>

        label {
            font-size: 120%;
        }

    </style>
</head>
<body>
<%@include file="header.jsp" %>
<main class="main">
    <div class="container justify-content-center mt-50 mb-50">
        <p style="font-size: 20px; color: red;">${error}</p>
        <c:choose>
            <c:when test="${sessionScope.user.role eq Role.ADMIN}">
                <form action="${pageContext.request.contextPath}/controller?command=create_match"
                      method="post">
                    <button type="button" class="btn btn-primary" data-toggle="modal"
                            data-target="#staticBackdrop">Create match
                    </button>


                    <div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false"
                         tabindex="-1"
                         aria-labelledby="staticBackdropLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">

                                <div class="modal-header">
                                    <h5 class="modal-title" id="staticBackdropLabel">Create match</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>

                                <div class="modal-body">
                                    <div class="form-group">
                                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                                data-target="#addTeamModal">Add team
                                        </button>
                                    </div>

                                    <div class="form-group">
                                        <label for="firstTeam">First team:</label>
                                        <select class="form-control" name="firstTeam" id="firstTeam" required>
                                            <option selected disabled>Select first team:</option>
                                            <c:forEach items="${sessionScope.teams}" var="entry">
                                                <option value="${entry.key}">${entry.value}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <label for="secondTeam">Second team:</label>
                                        <select class="form-control" name="secondTeam" id="secondTeam" required>
                                            <option selected="true" disabled="disabled">Select second team:</option>
                                            <c:forEach items="${sessionScope.teams}" var="entry">
                                                <option value="${entry.key}">${entry.value}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <label for="firstCoef">First coefficient:</label>
                                        <input type="number" step="0.01" name="firstCoef" class="form-control"
                                               id="firstCoef"
                                               placeholder="Choose first coefficient"
                                               aria-label="firstCoef" aria-describedby="basic-addon1" required>
                                    </div>

                                    <div class="form-group">
                                        <label for="secondCoef">Second coefficient:</label>
                                        <input type="number" step="0.01" name="secondCoef" class="form-control"
                                               id="secondCoef"
                                               placeholder="Choose second coefficient"
                                               aria-label="secondCoef" aria-describedby="basic-addon1" required>
                                    </div>

                                    <div class="form-group">
                                        <label for="date">Date</label>
                                        <input type="date" name="date" class="form-control" id="date"
                                               placeholder="Input date"
                                               aria-label="timeDate" aria-describedby="basic-addon1" required>
                                    </div>

                                    <div class="form-group">
                                        <label for="time">Time</label>
                                        <input type="time" step="1" name="time" class="form-control" id="time"
                                               placeholder="Input time"
                                               aria-label="timeDate" aria-describedby="basic-addon1" required>
                                    </div>

                                    <div class="form-group">
                                        <label for="gameId">Game</label>
                                        <select class="form-control" name="gameId" id="gameId" required>
                                            <option selected disabled>Select game:</option>
                                            <option value="${Game.CSGO.id}">${Game.CSGO}</option>
                                            <option value="${Game.Valorant.id}">${Game.Valorant}</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">${close}Close
                                    </button>
                                    <button type="submit" class="btn btn-primary">${save}Save</button>
                                </div>

                            </div>
                        </div>
                    </div>
                </form>

                <form action="${pageContext.request.contextPath}/controller?command=add_team" method="post">
                    <div class="modal fade" id="addTeamModal" data-backdrop="static" data-keyboard="false"
                         tabindex="-1"
                         aria-labelledby="staticBackdropLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">

                                <div class="modal-header">
                                    <h5 class="modal-title" id="addTeamModalLabel">Add team</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>

                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="addTeam">New team:</label>
                                        <input type="text" name="addTeam" class="form-control"
                                               id="addTeam"
                                               placeholder="Enter team name"
                                               aria-label="addTeam" aria-describedby="basic-addon1" required>
                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">${close}Close
                                    </button>
                                    <button type="submit" class="btn btn-primary">${save}Save</button>
                                </div>

                            </div>
                        </div>
                    </div>

                </form>

            </c:when>
        </c:choose>


        <%--        <div class="row">--%>
        <div class="">
            <c:forEach var="i" begin="0" end="${sessionScope.matches.size() - 1}">
                <div style="display: flex; text-align: center; align-items: center; justify-content: center;">
                    <div class="card card-body mt-3" style="margin-right: 20px">
                        <c:choose>
                            <c:when test="${matches.get(i).status ne GameStatus.FINISHED}">
                                <a href="${pageContext.request.contextPath}/controller?command=match&id=${sessionScope.matches.get(i).id}"
                                   style="display: flex; justify-content: space-between; text-align: center; color: inherit">
                                    <div>
                                        <h4>${sessionScope.teams.get(sessionScope.matches.get(i).firstTeam)}</h4>
                                        <div>${sessionScope.matches.get(i).firstCoefficient}</div>
                                    </div>
                                    <div style="display: flex; justify-content: space-between;">
                                        <h2>${sessionScope.matches.get(i).firstTeamScore}</h2>
                                        <div style="margin-left: 20px; margin-right: 20px; display: grid; align-items: end;">
                                            <div>${sessionScope.matches.get(i).game}</div>
                                            <div>${sessionScope.matches.get(i).status}</div>
                                            <div>
                                                <fmt:formatDate value="${sessionScope.matches.get(i).date}"
                                                                pattern="HH:mm:ss dd-MM-yyyy"/>
                                            </div>
                                        </div>
                                        <h2>${sessionScope.matches.get(i).secondTeamScore}</h2>
                                    </div>
                                    <div>
                                        <h4>${sessionScope.teams.get(sessionScope.matches.get(i).secondTeam)}</h4>
                                        <div>${sessionScope.matches.get(i).secondCoefficient}</div>
                                    </div>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <div style="display: flex; justify-content: space-between; text-align: center; color: inherit">
                                    <div>
<%--                                        <input type="hidden" name="id1" value="${sessionScope.matches.get(i).id}">--%>

                                        <h4>${sessionScope.teams.get(sessionScope.matches.get(i).firstTeam)}</h4>
                                        <div>${sessionScope.matches.get(i).firstCoefficient}</div>
                                    </div>
                                    <div style="display: flex; justify-content: space-between;">
                                        <h2>${sessionScope.matches.get(i).firstTeamScore}</h2>
                                        <div style="margin-left: 20px; margin-right: 20px; display: grid; align-items: end;">
                                            <div>${sessionScope.matches.get(i).game}</div>
                                            <div>${sessionScope.matches.get(i).status}</div>
                                            <div>
                                                <fmt:formatDate value="${sessionScope.matches.get(i).date}"
                                                                pattern="HH:mm:ss dd-MM-yyyy"/>
                                            </div>
                                        </div>
                                        <h2>${sessionScope.matches.get(i).secondTeamScore}</h2>
                                    </div>
                                    <div>
                                        <h4>${sessionScope.teams.get(sessionScope.matches.get(i).secondTeam)}</h4>
                                        <div>${sessionScope.matches.get(i).secondCoefficient}</div>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
<%--                    <input type="hidden" name="id2" value="${sessionScope.matches.get(i).id}">--%>

                    <c:if test="${sessionScope.user.role eq Role.ADMIN}">
                        <div>
<%--                            <input type="hidden" name="id3" value="${sessionScope.matches.get(i).id}">--%>

                            <a style="align-self: flex-start;" href="#" class="btn btn-primary" data-toggle="modal"
                               data-target="#deleteMatch${sessionScope.matches.get(i).id}"
                               role="button">Delete</a>
                        </div>
<%--                        <input type="hidden" name="id4" value="${sessionScope.matches.get(i).id}">--%>

                        <form action="${pageContext.request.contextPath}/controller?command=delete_match"
                              method="post">

                            <div class="modal fade" tabindex="-1" id="deleteMatch${sessionScope.matches.get(i).id}">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title">Delete match</h5>
                                            <input type="hidden" name="id" value="${sessionScope.matches.get(i).id}">

                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <p>${deleteAccountConfirm}Are you sure you want to delete this match?</p>
                                        </div>
                                        <div class="modal-footer">
                                            <input type="hidden" name="id" value="${matches.get(i).id}">

                                            <button type="button" class="btn btn-secondary"
                                                    data-dismiss="modal">${close}Close
                                            </button>
                                            <button type="submit" class="btn btn-primary">${deleteAccount}Delete match
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </c:if>


                </div>

            </c:forEach>
        </div>
    </div>
    <%--    </div>--%>


    <c:if test="${not empty requestScope.message}">
        <script>
            alert("${requestScope.message}")
            window.location = '${pageContext.request.contextPath}/controller?command=show_matches';
        </script>
    </c:if>
    <script>
        const destination = document.getElementById('secondTeam');
        const originalOptions = Array.from(destination.options).slice(0);
        document.getElementById('firstTeam').addEventListener('change', e => {
            const currentOptions = Array.from(originalOptions).filter(option => option.value !== e.currentTarget.value);
            destination.length = 0;
            currentOptions.forEach(option => destination.appendChild(option));
        });
    </script>

</main>
<%@include file="footer.jsp" %>
</body>
</html>