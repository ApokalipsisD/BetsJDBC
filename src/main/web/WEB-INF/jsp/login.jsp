<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Login</title>

    <style>
        <%@include file="/WEB-INF/css/bootstrap.min.css" %>
        <%@include file="/WEB-INF/css/signUp.css" %>
    </style>

</head>
<body>
<%@include file="header.jsp"%>
<main class="main">
    <div class="container">
        <div class="card card-container">
            <img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"/>
            <form action="${pageContext.request.contextPath}/controller?command=login" method="post"
                  class="form-signin">
                <h4 style="margin-bottom: 20px; text-align: center;">Login</h4>
                <p style="color: red; text-align: center;">${error}</p>
                <div class="input-container">
                    <input name="login" id="login" type="text" class="form-control" placeholder="Username"
                           aria-label="Login" aria-describedby="basic-addon1" required>
                </div>

                <div class="input-container">
                    <input type="password" name="password" id="inputPassword" class="form-control" aria-label="Password" placeholder="Password" required>
                </div>

                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Login</button>
            </form>
            <a href="${pageContext.request.contextPath}/controller?command=show_sign_up" class="forgot-password"
               style="text-align: center">Create account</a>
        </div>
    </div>
    <c:if test="${not empty requestScope.message}">
        <script>
            alert("${requestScope.message}")
            window.location = '${pageContext.request.contextPath}/controller?command=show_login';
        </script>
    </c:if>
</main>
<%@include file="footer.jsp" %>
</body>
</html>
