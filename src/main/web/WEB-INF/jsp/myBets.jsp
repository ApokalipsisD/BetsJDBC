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

<%@ page import="com.bets.dao.model.Role" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <title>Matches</title>
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
        <c:choose>
            <c:when test="${empty sessionScope.myBets}">
                <p style="font-size: 20px;">You haven't bet on matches yet</p>
            </c:when>
            <c:otherwise>
                <div>
                    <c:forEach var="i" begin="0" end="${sessionScope.myBets.size()-1}">
                        <div class="card card-body mt-3">
                            <div style="display: flex; justify-content: space-between; text-align: center; color: inherit">
                                <div>Match: ${sessionScope.myBets.get(i).matchId}</div>
                                <div>Bet: ${sessionScope.myBets.get(i).bet}</div>
                                <div>Team: ${sessionScope.teams.get(sessionScope.myBets.get(i).team)}</div>
                                <div>Coefficient: ${sessionScope.myBets.get(i).coefficient}</div>
                                <div>Bet status: ${sessionScope.myBets.get(i).betStatus}</div>
                                <div>Earnings: ${sessionScope.myBets.get(i).earnings}</div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>

    </div>
    <%--    </div>--%>
    <%--    <c:if test="${not empty requestScope.message}">--%>
    <%--        <script>--%>
    <%--            alert("${requestScope.message}")--%>
    <%--            window.location = '${pageContext.request.contextPath}/controller?command=show_courses';--%>
    <%--        </script>--%>
    <%--    </c:if>--%>

</main>
<%@include file="footer.jsp" %>
</body>
</html>