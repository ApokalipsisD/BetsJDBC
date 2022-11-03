<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="jwdt" uri="jwdTags" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>

<%--<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'en'}"/>--%>
<%--<fmt:setBundle basename="locale" var="loc"/>--%>
<%--<fmt:message bundle="${loc}" key="catalog" var="catalog"/>--%>
<%--<fmt:message bundle="${loc}" key="createCourse" var="createCourse"/>--%>
<%--<fmt:message bundle="${loc}" key="title" var="title"/>--%>
<%--<fmt:message bundle="${loc}" key="inputTitle" var="inputTitle"/>--%>
<%--<fmt:message bundle="${loc}" key="beginning" var="beginning"/>--%>
<%--<fmt:message bundle="${loc}" key="inputStartDate" var="inputStartDate"/>--%>
<%--<fmt:message bundle="${loc}" key="ending" var="ending"/>--%>
<%--<fmt:message bundle="${loc}" key="inputEndDate" var="inputEndDate"/>--%>
<%--<fmt:message bundle="${loc}" key="teacher" var="teacherN"/>--%>
<%--<fmt:message bundle="${loc}" key="selectTeacher" var="selectTeacher"/>--%>
<%--<fmt:message bundle="${loc}" key="description" var="descriptionS"/>--%>
<%--<fmt:message bundle="${loc}" key="close" var="close"/>--%>
<%--<fmt:message bundle="${loc}" key="save" var="save"/>--%>
<%--<fmt:message bundle="${loc}" key="status" var="status"/>--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html>
<head>
    <title>Quick Servlet Demo</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/controller">Click here to send GET request</a>

<br/><br/>

<form action="${pageContext.request.contextPath}/controller" method="post">
    Width: <input type="text" size="5" name="width"/>
    Height <input type="text" size="5" name="height"/>
    <input type="submit" value="Calculate" />
</form>
</body>
</html>

<%--<%@ page import="com.bets.dao.model.Role" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" %>--%>

<%--<html>--%>
<%--<head>--%>
<%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">--%>
<%--    <title>${catalog}</title>--%>
<%--    <style>--%>

<%--&lt;%&ndash;        <%@include file="/WEB-INF/css/bootstrap.min.css" %>&ndash;%&gt;--%>
<%--&lt;%&ndash;        <%@include file="/WEB-INF/css/catalog.css" %>&ndash;%&gt;--%>

<%--        label {--%>
<%--            font-size: 120%;--%>
<%--        }--%>

<%--    </style>--%>
<%--</head>--%>
<%--<body>--%>
<%--&lt;%&ndash;<%@include file="header.jsp" %>&ndash;%&gt;--%>
<%--<main class="main">--%>
<%--    <div class="container justify-content-center mt-50 mb-50">--%>
<%--        <p style="font-size: 20px; color: red;">${requestScope.error}</p>--%>
<%--        <c:choose>--%>
<%--            <c:when test="${sessionScope.account.role eq Role.ADMIN}">--%>
<%--                <form action="${pageContext.request.contextPath}/controller?command=create_course"--%>
<%--                      method="post">--%>
<%--                    <button type="button" class="btn btn-primary" data-toggle="modal"--%>
<%--                            data-target="#staticBackdrop">--%>
<%--                            ${createCourse}--%>
<%--                    </button>--%>

<%--                    <div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false"--%>
<%--                         tabindex="-1"--%>
<%--                         aria-labelledby="staticBackdropLabel" aria-hidden="true">--%>
<%--                        <div class="modal-dialog">--%>
<%--                            <div class="modal-content">--%>

<%--                                <div class="modal-header">--%>
<%--                                    <h5 class="modal-title" id="staticBackdropLabel">${createCourse}</h5>--%>
<%--                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
<%--                                        <span aria-hidden="true">&times;</span>--%>
<%--                                    </button>--%>
<%--                                </div>--%>

<%--                                <div class="modal-body">--%>
<%--                                    <div class="form-group">--%>
<%--                                        <label for="title">${title}</label>--%>
<%--                                        <input type="text" name="title" class="form-control" id="title"--%>
<%--                                               placeholder="${inputTitle}"--%>
<%--                                               aria-label="Title" aria-describedby="basic-addon1" required>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group">--%>
<%--                                        <label for="startDate">${beginning}</label>--%>
<%--                                        <input type="date" name="startDate" class="form-control" id="startDate"--%>
<%--                                               placeholder="${inputStartDate}"--%>
<%--                                               aria-label="startDate" aria-describedby="basic-addon1" required>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group">--%>
<%--                                        <label for="endDate">${ending}</label>--%>
<%--                                        <input type="date" name="endDate" class="form-control" id="endDate"--%>
<%--                                               placeholder="${inputEndDate}"--%>
<%--                                               aria-label="endDate" aria-describedby="basic-addon1" required>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group">--%>
<%--                                        <label for="teacher">${teacherN}</label>--%>
<%--                                        <select class="form-control" name="teacher" id="teacher" required>--%>
<%--                                            <option selected disabled>${selectTeacher}</option>--%>
<%--                                            <c:forEach var="i" begin="0"--%>
<%--                                                       end="${sessionScope.teachers.size()-1}">--%>
<%--                                                <option value="${sessionScope.teachers.get(i).id}">${sessionScope.teachers.get(i).firstName} ${sessionScope.teachers.get(i).lastName}</option>--%>
<%--                                            </c:forEach>--%>

<%--                                        </select>--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group">--%>
<%--                                        <label for="description">${descriptionS}</label>--%>
<%--                                        <textarea class="form-control" name="description" id="description"--%>
<%--                                                  rows="3"></textarea>--%>
<%--                                    </div>--%>

<%--                                </div>--%>


<%--                                <div class="modal-footer">--%>
<%--                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">${close}--%>
<%--                                    </button>--%>
<%--                                    <button type="submit" class="btn btn-primary">${save}</button>--%>
<%--                                </div>--%>

<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </form>--%>
<%--            </c:when>--%>
<%--        </c:choose>--%>
<%--        <div class="row">--%>
<%--            <div class="col-md-10">--%>
<%--                <c:forEach var="i" begin="0" end="${sessionScope.catalog.size()-1}">--%>
<%--                    <div class="card card-body mt-3">--%>
<%--                        <div class="media align-items-center align-items-lg-start text-center text-lg-left flex-column flex-lg-row">--%>
<%--                            <div class="media-body">--%>
<%--                                <h6 class="media-title font-weight-semibold">--%>
<%--                                    <a href=controller?command=catalog&course=${sessionScope.catalog.get(i).id}--%>
<%--                                       data-abc="true">--%>
<%--                                            ${sessionScope.catalog.get(i).title}</a>--%>
<%--                                </h6>--%>

<%--                                <p class="mb-3">${sessionScope.catalog.get(i).description}</p>--%>
<%--                                <ul class="list-inline list-inline-dotted mb-0">--%>
<%--                                    <li class="list-inline-item">${sessionScope.catalog.get(i).startDate}</li>--%>
<%--                                    <li class="list-inline-item">${sessionScope.catalog.get(i).endDate}</li>--%>
<%--                                    <li class="list-inline-item">--%>
<%--                                            ${status}: ${sessionScope.catalog.get(i).courseStatus}</li>--%>
<%--                                </ul>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </c:forEach>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--&lt;%&ndash;    <c:if test="${not empty requestScope.message}">&ndash;%&gt;--%>
<%--&lt;%&ndash;        <script>&ndash;%&gt;--%>
<%--&lt;%&ndash;            alert("${requestScope.message}")&ndash;%&gt;--%>
<%--&lt;%&ndash;            window.location = '${pageContext.request.contextPath}/controller?command=show_courses';&ndash;%&gt;--%>
<%--&lt;%&ndash;        </script>&ndash;%&gt;--%>
<%--&lt;%&ndash;    </c:if>&ndash;%&gt;--%>

<%--</main>--%>
<%--&lt;%&ndash;<%@include file="footer.jsp" %>&ndash;%&gt;--%>
<%--</body>--%>
<%--</html>--%>