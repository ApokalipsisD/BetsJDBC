<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="jwdt" uri="jwdTags" %>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'en'}"/>--%>
<%--<fmt:setBundle basename="locale" var="loc"/>--%>
<%--<fmt:message bundle="${loc}" key="catalog" var="catalog"/>--%>
<%--<fmt:message bundle="${loc}" key="profile" var="profile"/>--%>
<%--<fmt:message bundle="${loc}" key="login" var="login"/>--%>
<%--<fmt:message bundle="${loc}" key="signUp" var="signUp"/>--%>


<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Main Page</title>
</head>
<style>
    .header-h1 {
        text-align: center;
        margin-bottom: .5rem;
    }
    .header-h1 h1 {
        display: inline-block;
        position: relative;
        background: #0097a7;
        color: #fff;
        margin-bottom: 0;
        padding: .5rem 3rem;
        font-size: 1.5rem;
        text-transform: uppercase;
    }
    .header-h1 h1::before {
        content: "";
        position: absolute;
        left: 0;
        top: 50%;
        transform: translateY(-50%);
        border-left: 1.5rem solid #fff;
        border-top: 3rem solid transparent;
        border-bottom: 3rem solid transparent;
    }
    .header-h1 h1::after {
        content: "";
        position: absolute;
        right: 0;
        top: 50%;
        transform: translateY(-50%);
        border-right: 1.5rem solid #fff;
        border-top: 3rem solid transparent;
        border-bottom: 3rem solid transparent;
    }
</style>
<body>
<%@include file="header.jsp"%>

<main class="main">

        <div class="container col-md-5">
            <div class="header-h1" style="padding-bottom: 20px; padding-top: 20px">
                <h1 style="font-size: 45px">Bets</h1>
            </div>
<%--            <h3 style="text-align: center;  color: #0097a7; font-family: ‘Lobster’, cursive; font-size: 36px; font-weight: normal; line-height: 48px; margin: 0 0 18px; text-shadow: 1px 0 0 #fff;"><jwdt:welcomeText/></h3>--%>
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <div class="row justify-content-between" style="margin-top: 50px">
                        <div class="card card-body col-sm-5 col-md-6 col-lg-5">
                            <div class="card-body"
                                 style="height: 150px; justify-content: center; text-align: center">
                                <p style="text-align: center; font-size: 30px">${catalog}Matches</p>
                                <a class="btn btn-primary btn-lg"
                                   href="${pageContext.request.contextPath}/controller?command=show_matches"
                                   role="button" style="align-content: center; align-items: center" type="submit">${catalog}Matches</a>
                            </div>
                        </div>
                        <div class="card card-body col-sm-5 col-md-6 col-lg-5">
                            <div class="card-body"
                                 style="height: 150px; justify-content: center; text-align: center">
                                <p style="text-align: center; font-size: 30px">${profile}Profile</p>
                                <a class="btn btn-primary btn-lg"
                                   href="${pageContext.request.contextPath}/controller?command=show_profile_page"
                                   role="button" style="align-content: center; align-items: center" type="submit">${profile}Profile</a>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="row justify-content-between" style="margin-top: 50px">
                        <div class="card card-body col-md-5 col-sm-5 col-lg-5">
                            <div class="card-body align-items-center justify-content-center"
                                 style="height: 150px; justify-content: center; text-align: center">
                                <p style="text-align: center; font-size: 30px">${login}Login</p>
                                <a class="btn btn-primary btn-lg"
                                   href="${pageContext.request.contextPath}/controller?command=show_login"
                                   role="button" style="align-content: center; align-items: center" type="submit">${login}Login</a>
                            </div>
                        </div>
                        <div class="card card-body col-md-5 col-sm-5 col-lg-5">
                            <div class="card-body align-items-center justify-content-center"
                                 style="height: 150px; justify-content: center; text-align: center">
                                <p style="text-align: center; font-size: 30px">${signUp}Sign Up</p>
                                <a class="btn btn-primary btn-lg"
                                   href="${pageContext.request.contextPath}/controller?command=show_sign_up"
                                   role="button" style="align-content: center; align-items: center" type="submit">${signUp}Sign Up</a>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>

        </div>

</main>

<%@include file="footer.jsp"%>
</body>
</html>