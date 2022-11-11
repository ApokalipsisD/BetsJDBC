<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@ page import="com.bets.dao.model.Role" %>
<html>
<head>
    <title>Main Page</title>
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
            <a class="navbar-brand" href="${pageContext.request.contextPath}/controller?command=show_main">FireBets</a>
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
                                <li>
                                    <a href="${pageContext.request.contextPath}/controller?command=show_login"
                                       class="login nav-link px-2 link-dark">Login</a>
                                </li>
                                <li>
                                    <a class="btn btn-light action-button" role="button"
                                       href="${pageContext.request.contextPath}/controller?command=show_sign_up">SignUp</a>
                                </li>
                            </ul>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <ul class="nav navbar-nav col-12 col-md-auto mb-2 justify-content-center mb-md-0 ">
                            <li><a href="${pageContext.request.contextPath}/controller?command=show_matches"
                                   class="nav-link px-2 link-dark">Matches</a></li>
                            <li><a href="${pageContext.request.contextPath}/controller?command=show_my_bets"
                                   class="nav-link px-2 link-dark">My Bets</a></li>
                        </ul>
                        <ul class="nav navbar-nav align-items-center">
                            <li><a href="${pageContext.request.contextPath}/controller?command=show_profile_page"
                                   class="nav-link px-2 link-dark">Profile</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/controller?command=logout"
                                   class="login nav-link px-2 link-dark">Logout</a>
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
