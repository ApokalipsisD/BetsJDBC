<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'en'}"/>--%>
<%--<fmt:setBundle basename="locale" var="loc"/>--%>
<%--<fmt:message bundle="${loc}" key="userName" var="userName"/>--%>
<%--<fmt:message bundle="${loc}" key="signUp" var="signUp"/>--%>
<%--<fmt:message bundle="${loc}" key="confirmPassword" var="confirmPassword"/>--%>
<%--<fmt:message bundle="${loc}" key="passwordReq" var="passwordReq"/>--%>
<%--<fmt:message bundle="${loc}" key="loginReq" var="loginReq"/>--%>
<%--<fmt:message bundle="${loc}" key="password" var="password"/>--%>
<%--<fmt:message bundle="${loc}" key="haveAccount" var="haveAccount"/>--%>

<html>
<head>
    <title>${signUp}SignUp</title>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <style>
        <%@include file="/WEB-INF/css/bootstrap.min.css" %>
        <%@include file="/WEB-INF/css/signUp.css" %>

    </style>

</head>
<body class="text-center">
<%@include file="header.jsp" %>
<main class="main">
    <div class="container">
        <div class="card card-container">
            <img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"/>
            <form action="${pageContext.request.contextPath}/controller?command=sign_up_command" method="post"
                  class="form-signin">
                <h4 style="margin-bottom: 20px;">${signUp}Sign Up</h4>
                <p style="color: red;">${error}</p>
                <div class="input-container">

                    <input name="login" id="login" type="text" class="form-control" placeholder="${userName}Username"
                           aria-label=Login" aria-describedby="basic-addon1" required
                           pattern="^[\w.-]{3,20}[0-9a-zA-Z]$"
                           title="${loginReq}LoginReq">
                </div>


                <div class="input-container">
                    <input type="password" name="password" id="inputPassword" class="form-control"
                           placeholder="${password}Password"
                           required pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,20}"
                           title="${passwordReq}PasswordReq">
                </div>

                <div class="input-container">
                    <input type="password" name="repeat_password" id="inputRepeatPassword" class="form-control"
                           placeholder="${confirmPassword}Confirm password" required
                           pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,20}" title="${confirmPassword}Confirm password">
                </div>

                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">${signUp}Sign Up</button>
            </form>

            <a href="${pageContext.request.contextPath}/controller?command=show_login" class="forgot-password">
                ${haveAccount}Already have an account?
            </a>
        </div>
    </div>

</main>

<%@include file="footer.jsp" %>
</body>
</html>