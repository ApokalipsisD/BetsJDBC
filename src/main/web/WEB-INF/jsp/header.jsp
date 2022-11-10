<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="jwdt" uri="jwdTags" %>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'en'}"/>--%>
<%--<fmt:setBundle basename="locale" var="loc"/>--%>
<%--<fmt:message bundle="${loc}" key="login" var="login"/>--%>
<%--<fmt:message bundle="${loc}" key="signUp" var="signUp"/>--%>
<%--<fmt:message bundle="${loc}" key="myCourses" var="myCourses"/>--%>
<%--<fmt:message bundle="${loc}" key="teacherCourses" var="teacherCourses"/>--%>
<%--<fmt:message bundle="${loc}" key="logOut" var="logOut"/>--%>
<%--<fmt:message bundle="${loc}" key="catalog" var="catalog"/>--%>
<%--<fmt:message bundle="${loc}" key="profile" var="profile"/>--%>
<%--<fmt:message bundle="${loc}" key="mainPage" var="mainPage"/>--%>


<%@ page import="com.bets.dao.model.Role" %>
<html>
<head>
    <title>${mainPage}Main Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bitter:400,700">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <style>
        <%@include file="/WEB-INF/css/header.css" %>
    </style>

</head>
<body>
<div class="header-dark ">
    <nav class="navbar navbar-dark navbar-expand-md special-color-dark">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/controller?command=show_main">Bets</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent2"
                    aria-controls="navbarSupportedContent2" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse align-items-center justify-content-between"
                 id="navbarSupportedContent2">
                <c:choose>
                    <c:when test="${empty sessionScope.user}">
                        <div class="navbar-collapse align-items-center justify-content-md-end">
                            <ul class="nav navbar-nav align-items-center ">
                                    <%--                                <li class="dropdown">--%>
                                    <%--                                    <a class="dropdown-toggle nav-link dropdown-toggle"--%>
                                    <%--                                       data-toggle="dropdown"--%>
                                    <%--                                       aria-expanded="false"--%>
                                    <%--                                       href="#">${not empty sessionScope.language ? sessionScope.language : 'en'}</a>--%>
                                    <%--                                <div class="dropdown-menu" role="menu">--%>
                                    <%--                                    <a class="dropdown-item" role="presentation"--%>
                                    <%--                                       href="${pageContext.request.contextPath}/controller?command=change_language&lang=en">en</a>--%>
                                    <%--                                    <a class="dropdown-item" role="presentation"--%>
                                    <%--                                       href="${pageContext.request.contextPath}/controller?command=change_language&lang=ru">ru</a>--%>
                                    <%--                                </div>--%>
                                    <%--                                </li>--%>

                                <li>
                                    <a href="${pageContext.request.contextPath}/controller?command=show_login"
                                       class="login nav-link px-2 link-dark">${login}Login</a>
                                </li>
                                <li>
                                    <a class="btn btn-light action-button" role="button"
                                       href="${pageContext.request.contextPath}/controller?command=show_sign_up">${signUp}SignUp</a>
                                </li>
                            </ul>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <%--    <span>--%>
                        <ul class="nav navbar-nav col-12 col-md-auto mb-2 justify-content-center mb-md-0 ">
                            <li><a href="${pageContext.request.contextPath}/controller?command=show_matches"
                                   class="nav-link px-2 link-dark">Matches</a></li>
                            <li><a href="${pageContext.request.contextPath}/controller?command=show_my_bets"
                                   class="nav-link px-2 link-dark">My Bets</a></li>
<%--                            <c:if test="${sessionScope.user.role eq Role.ADMIN}">--%>
<%--                                <li>--%>
<%--                                    <a href="${pageContext.request.contextPath}/controller?command=show_users"--%>
<%--                                       class="nav-link px-2 link-dark">Users</a></li>--%>
<%--                            </c:if>--%>
                        </ul>
                        <ul class="nav navbar-nav align-items-center">
                                <%--                            <span>--%>
                                <%--                                <li class="dropdown">--%>
                                <%--                                    <a class="dropdown-toggle nav-link dropdown-toggle"--%>
                                <%--                                       data-toggle="dropdown"--%>
                                <%--                                       aria-expanded="false"--%>
                                <%--                                       href="#">${not empty sessionScope.language ? sessionScope.language : 'en'}</a>--%>
                                <%--                                <div class="dropdown-menu" role="menu">--%>
                                <%--                                    <a class="dropdown-item" role="presentation"--%>
                                <%--                                       href="${pageContext.request.contextPath}/controller?command=change_language&lang=en">en</a>--%>
                                <%--                                    <a class="dropdown-item" role="presentation"--%>
                                <%--                                       href="${pageContext.request.contextPath}/controller?command=change_language&lang=ru">ru</a>--%>
                                <%--                                </div>--%>
                                <%--                                </li>--%>
                                <%--                            </span>--%>
                                <%--                            <span>--%>
                            <li><a href="${pageContext.request.contextPath}/controller?command=show_profile_page"
                                   class="nav-link px-2 link-dark">${profile}Profile</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/controller?command=logout"
                                   class="login nav-link px-2 link-dark">${logOut}Logout</a>
                            </li>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </nav>
</div>

</body>
</html>
