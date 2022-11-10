<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@ page import="com.bets.dao.model.Role" %>
<%@ page import="com.bets.dao.model.GameStatus" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <title>Match</title>
    <style>

        <%@include file="/WEB-INF/css/bootstrap.min.css" %>

    </style>

</head>
<body>
<%@include file="header.jsp"%>
<main class="main" style="background-color: #dee1e3">
    <div class="container" style="margin-top: 30px">
        <p style="color: red; text-align: center;">${error}</p>
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
                <div style="margin-left: 20px; margin-right: 20px; display: grid; align-items: center;  align-content: center;">
                    <h4>${match.game}</h4>
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

