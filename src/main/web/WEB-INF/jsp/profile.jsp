<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Profile</title>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <style>
        <%@include file="/WEB-INF/css/bootstrap.min.css" %>

        body {
            margin-top: 20px;
            background: #f5f5f5;
        }

        .ui-w-100 {
            width: 100px !important;
            height: auto;
        }

        .card {
            background-clip: padding-box;
            box-shadow: 0 1px 4px rgba(24, 28, 33, 0.012);
        }

        .user-view-table td:first-child {
            width: 9rem;
        }

        .user-view-table td {
            padding-right: 0;
            padding-left: 0;
            border: 0;
        }

        .card .row-bordered > [class*=" col-"]::after {
            border-color: rgba(24, 28, 33, 0.075);
        }
    </style>

</head>
<body>
<%@include file="header.jsp" %>
<main class="main">
    <div class="container bootdey flex-grow-1 container-p-y">
        <div class="media align-items-center py-3 mb-3 ">
            <img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt=""
                 class="d-block ui-w-100 rounded-circle">
            <div class="media-body ml-4" style="display: flex; flex-direction: row; justify-content: space-between;">
                <div style="">
                    <c:choose>
                        <c:when test="${not empty sessionScope.user.name && not empty sessionScope.user.surname}">
                            <h4 class="font-weight-bold mb-0">${sessionScope.user.name} ${sessionScope.user.surname}
                                <span class="text-muted font-weight-normal">@${sessionScope.user.login}</span></h4>
                        </c:when>
                        <c:otherwise>
                            <h4 class="font-weight-normal">@${sessionScope.user.login}</h4>
                        </c:otherwise>
                    </c:choose>
                    <div style="padding-top: 15px; justify-content: space-between">
                        <a href="controller?command=show_edit_profile" class="btn btn-primary btn-sm">Edit</a>
                        <a href="#" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#deleteAccount"
                           role="button">Delete</a>
                    </div>
                </div>

                <h4>
                    <a href="#" style="color: inherit" data-toggle="modal"
                       data-target="#addBalance">Balance: ${sessionScope.user.balance}$</a>
                </h4>

            </div>
        </div>

        <form action="${pageContext.request.contextPath}/controller?command=add_balance" method="post">
            <div class="modal fade" tabindex="-1" id="addBalance">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Delete match</h5>

                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>How much do you want to deposit?</p>
                            <input type="number" step="0.01" name="deposit" class="form-control"
                                   id="deposit"
                                   placeholder="Deposit"
                                   aria-label="deposit" aria-describedby="basic-addon1" required>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary"
                                    data-dismiss="modal">Close
                            </button>
                            <button type="submit" class="btn btn-primary">Deposit</button>
                        </div>
                    </div>
                </div>
            </div>

        </form>
        <form action="${pageContext.request.contextPath}/controller?command=delete_account" method="post">
            <div class="modal fade" tabindex="-1" id="deleteAccount">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Delete account</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>Are you sure you want to delete this account?</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Delete account</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>

        <div class="card mb-4">
            <div class="card-body">
                <table class="table user-view-table m-0">
                    <tbody>
                    <tr>
                        <td>Login:</td>
                        <c:choose>
                            <c:when test="${not empty sessionScope.user.login}">
                                <td>${sessionScope.user.login}</td>
                            </c:when>
                            <c:otherwise>
                                <td>-</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
                        <td>Firstname:</td>
                        <c:choose>
                            <c:when test="${not empty sessionScope.user.name}">
                                <td>${sessionScope.user.name}</td>
                            </c:when>
                            <c:otherwise>
                                <td>-</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
                        <td>Lastname:</td>
                        <c:choose>
                            <c:when test="${not empty sessionScope.user.surname}">
                                <td>${sessionScope.user.surname}</td>
                            </c:when>
                            <c:otherwise>
                                <td>-</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <c:choose>
                            <c:when test="${not empty sessionScope.user.email}">
                                <td>${sessionScope.user.email}</td>
                            </c:when>
                            <c:otherwise>
                                <td>-</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
                        <td>Role:</td>
                        <td>${sessionScope.user.role}</td>
                    </tr>
                    <tr>
                        <td>Age:</td>
                        <c:choose>
                            <c:when test="${not empty sessionScope.user.age}">
                                <td>${sessionScope.user.age}</td>
                            </c:when>
                            <c:otherwise>
                                <td>-</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <c:if test="${not empty requestScope.message}">
        <script>
            alert("${requestScope.message}")
            window.location = '${pageContext.request.contextPath}/controller?command=show_profile_page';
        </script>
    </c:if>

</main>
<%@include file="footer.jsp" %>
</body>
</html>