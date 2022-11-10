<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'en'}"/>--%>
<%--<fmt:setBundle basename="locale" var="loc"/>--%>
<%--<fmt:message bundle="${loc}" key="userName" var="userName"/>--%>
<%--<fmt:message bundle="${loc}" key="firstName" var="firstName"/>--%>
<%--<fmt:message bundle="${loc}" key="lastName" var="lastName"/>--%>
<%--<fmt:message bundle="${loc}" key="birthDate" var="birthDate"/>--%>
<%--<fmt:message bundle="${loc}" key="settings" var="settings"/>--%>
<%--<fmt:message bundle="${loc}" key="profile" var="profile"/>--%>
<%--<fmt:message bundle="${loc}" key="saveChanges" var="saveChanges"/>--%>
<%--<fmt:message bundle="${loc}" key="oldPassword" var="oldPassword"/>--%>
<%--<fmt:message bundle="${loc}" key="newPassword" var="newPassword"/>--%>
<%--<fmt:message bundle="${loc}" key="confirmPassword" var="confirmPassword"/>--%>
<%--<fmt:message bundle="${loc}" key="passwordReq" var="passwordReq"/>--%>
<%--<fmt:message bundle="${loc}" key="passReq" var="passReq"/>--%>
<%--<fmt:message bundle="${loc}" key="passToCreate" var="passToCreate"/>--%>
<%--<fmt:message bundle="${loc}" key="minChar" var="minChar"/>--%>
<%--<fmt:message bundle="${loc}" key="oneLUl" var="oneLUl"/>--%>
<%--<fmt:message bundle="${loc}" key="oneNumber" var="oneNumber"/>--%>
<%--<fmt:message bundle="${loc}" key="samePass" var="samePass"/>--%>
<%--<fmt:message bundle="${loc}" key="changePassword" var="changePassword"/>--%>

<html>
<head>
    <title>${profile}Profile</title>
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
                <h2 class="h3 mb-4 page-title">${settings}Settings</h2>
                <div class="my-4">
                    <ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="home" href="${pageContext.request.contextPath}/controller?command=show_edit_profile"
                            >${profile}Profile</a>
                        </li>
                        <li>
                            <a class="nav-link active" href="${pageContext.request.contextPath}/controller?command=show_password_page"
                            >${changePassword}Change password</a>
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
                                                <h4 class="text-muted font-weight-normal">@${sessionScope.user.login}</h4>
                                            </c:when>
                                            <c:otherwise>
                                                <h4 class="font-weight-normal">@${sessionScope.user.login}</h4>
                                            </c:otherwise>
                                        </c:choose>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <p style="font-size: 20px; color: red;">${requestScope.error}</p>
                    <hr class="my-4"/>
                    <form action="${pageContext.request.contextPath}/controller?command=change_password" method="post">
                        <div class="row mb-4">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="inputOldPassword">${oldPassword}Old password</label>
                                    <input name="oldPass" type="password" class="form-control" id="inputOldPassword" required/>
                                </div>
                                <div class="form-group">
                                    <label for="inputNewPassword">${newPassword}New password</label>
                                    <input name="newPass" type="password" class="form-control" id="inputNewPassword"
                                           required pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,20}"
                                           title="${passwordReq}"/>
                                </div>
                                <div class="form-group">
                                    <label for="confirmNewPassword">${confirmPassword}Confirm password</label>
                                    <input name="confirmNewPass" type="password" class="form-control" id="confirmNewPassword"
                                           required pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,20}"
                                           title="${passwordReq}"/>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <p class="mb-2">${passReq}Password requirements</p>
                                <p class="small text-muted mb-2">${passToCreate}Here should be requirements:</p>
                                <ul class="small text-muted pl-4 mb-0">
                                    <li>${minChar}1</li>
                                    <li>${oneLUl}2</li>
                                    <li>${oneNumber}3</li>
                                    <li>${samePass}4</li>
                                </ul>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">${saveChanges}Save changes</button>
                    </form>

                    <hr class="my-4"/>
                </div>
            </div>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>