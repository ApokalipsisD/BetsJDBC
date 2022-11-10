<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'en'}"/>--%>
<%--<fmt:setBundle basename="locale" var="loc"/>--%>
<%--<fmt:message bundle="${loc}" key="page" var="page"/>--%>
<%--<fmt:message bundle="${loc}" key="pageNotFound" var="pageNotFound"/>--%>
<%--<fmt:message bundle="${loc}" key="pageYouLooking" var="pageYouLooking"/>--%>
<%--<fmt:message bundle="${loc}" key="backToMain" var="backToMain"/>--%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Responsive free template 404 for you website">
    <title>Page 404 </title>
    <style>
        <%@include file="/WEB-INF/css/bootstrap.min.css" %>
        <%@include file="/WEB-INF/css/error.css" %>
    </style>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
<main class="main">
    <section id="wrapper" class="container-fluid">
        <div class="error-box">
            <div class="error-body text-center">
                <h1 class="text-danger">404</h1>
                <h3>Page not found!</h3>
                <p class="text-muted m-t-30 m-b-30">MOST LIKELY THE PAGE YOU ARE LOOKING FOR THERE</p>
                <a href="${pageContext.request.contextPath}/controller?command=show_main" class="btn btn-danger btn-rounded m-b-40">Back to main</a>
            </div>
        </div>
    </section>
</main>
<%@include file="footer.jsp" %>
</body>
</html>