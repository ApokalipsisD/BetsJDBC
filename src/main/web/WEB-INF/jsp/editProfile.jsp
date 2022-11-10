<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <title>Profile</title>
    <style>
        <%@include file="/WEB-INF/css/bootstrap.min.css" %>
        <%@include file="/WEB-INF/css/editProfile.css" %>
    </style>
</head>
<body>
<%@include file="header.jsp" %>
<main class="main">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-lg-10 col-xl-8 mx-auto">
                <h2 class="h3 mb-4 page-title">Settings</h2>
                <div class="my-4">
                    <ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="home"
                               href="${pageContext.request.contextPath}/controller?command=show_edit_profile">Profile</a>
                        </li>
                        <li>
                            <a class="nav-link active"
                               href="${pageContext.request.contextPath}/controller?command=show_password_page"
                            >Change password</a>
                        </li>
                    </ul>
                    <form action="${pageContext.request.contextPath}/controller?command=edit_profile" method="post">
                        <div class="row mt-5 align-items-center">
                            <div class="col-md-3 text-center mb-5">
                                <div class="avatar avatar-xl">
                                    <img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="..."
                                         class="avatar-img rounded-circle"/>
                                </div>
                            </div>
                            <div class="col">
                                <div class="row align-items-center">
                                    <div class="col-md-7">
                                        <c:choose>
                                            <c:when test="${not empty sessionScope.user.name && not empty sessionScope.user.surname}">
                                                <h2 class="font-weight-bold mb-0">${sessionScope.user.name} ${sessionScope.user.surname}</h2>
                                                <h4 class="text-muted font-weight-normal">
                                                    @${sessionScope.user.login}</h4>
                                            </c:when>
                                            <c:otherwise>
                                                <h4 class="font-weight-normal">@${sessionScope.user.login}</h4>
                                            </c:otherwise>
                                        </c:choose>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <p style="font-size: 20px; color: red;">${requestScope.error}</p>
                        <hr class="my-4"/>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="firstname">Firstname</label>
                                <input type="text" id="firstname" name="firstName" class="form-control"
                                <c:choose>
                                <c:when test="${not empty sessionScope.user.name}">
                                       value="${sessionScope.user.name}"
                                </c:when>
                                </c:choose>
                                       pattern="^([А-Я][а-яё]{2,20}|[A-Z][a-z]{2,20})$"
                                       title="First name must start with an uppercase letter and contain letters of only one language">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="lastname">Lastname</label>
                                <input type="text" id="lastname" name="lastName" class="form-control"
                                <c:choose>
                                <c:when test="${not empty sessionScope.user.surname}">
                                       value="${sessionScope.user.surname}"
                                </c:when>
                                </c:choose>
                                       pattern="^([А-Я][а-яё]{2,20}|[A-Z][a-z]{2,20})$"
                                       title="Last name must start with an uppercase letter and contain letters of only one language">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputEmail4">Login</label>
                            <input type="text" name="userName" class="form-control" id="inputUsername"
                                   aria-label="inputEmail4" value="${sessionScope.user.login}"
                                   pattern="^[\w.-]{3,20}[0-9a-zA-Z]$"
                                   title="Login must be greater than 3 and less than 20 and must not contain inaccessible characters">
                        </div>
                        <div class="form-group">
                            <label for="inputEmail4">Email</label>
                            <input type="email" name="email" class="form-control" id="inputEmail4"
                            <c:choose>
                            <c:when test="${not empty sessionScope.user.email}">
                                   value="${sessionScope.user.email}"
                            </c:when>
                            </c:choose>
                                   pattern="^([a-zA-Z0-9_-]+\.)*[a-zA-Z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$">
                        </div>
                        <div class="form-group">
                            <label for="inputAddress5">Age</label>
                            <input type="number" name="age" class="form-control" id="inputAddress5"
                            <c:choose>
                            <c:when test="${not empty sessionScope.user.age}">
                                   value="${sessionScope.user.age}"
                            </c:when>
                            </c:choose>

                        </div>
                        <hr class="my-4"/>

                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>


