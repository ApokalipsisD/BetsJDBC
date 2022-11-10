<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="com.bets.dao.model.Role" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <title>Matches</title>
    <style>

        <%@include file="/WEB-INF/css/bootstrap.min.css" %>
        <%@include file="/WEB-INF/css/catalog.css" %>

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