<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Sign Up</title>
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
                <h4 style="margin-bottom: 20px;">Sign Up</h4>
                <p style="color: red;">${error}</p>
                <div class="input-container">

                    <input name="login" id="login" type="text" class="form-control" placeholder="Username"
                           aria-label=Login" aria-describedby="basic-addon1" required
                           pattern="^[\w.-]{3,20}[0-9a-zA-Z]$"
                           title="Login must be greater than 3 and less than 20 and must not contain inaccessible characters">
                </div>


                <div class="input-container">
                    <input type="password" name="password" id="inputPassword" class="form-control"
                           placeholder="Password"
                           required pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,20}"
                           title="Password must contain at least one number, one lowercase and one uppercase letter, min password length 8">
                </div>

                <div class="input-container">
                    <input type="password" name="repeat_password" id="inputRepeatPassword" class="form-control"
                           placeholder="Confirm password" required
                           pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,20}" title="Confirm password">
                </div>

                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Sign Up</button>
            </form>

            <a href="${pageContext.request.contextPath}/controller?command=show_login" class="forgot-password">
                Already have an account?
            </a>
        </div>
    </div>

</main>

<%@include file="footer.jsp" %>
</body>
</html>