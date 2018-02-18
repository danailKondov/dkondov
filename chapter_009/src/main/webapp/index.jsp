<%--
  Created by IntelliJ IDEA.
  User: Kondov Danail (mailto:dkondov@yandex.ru)
  Date: 31.01.2018
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users database</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        html {
            height: 100%;
        }
        body {
            margin: 0; /* Убираем отступы */
            height: 100%; /* Высота страницы */
            background-image: url("${pageContext.servletContext.contextPath}/resources/images/uzor-2.jpg");
            background-repeat: no-repeat;
            background-size: contain;
        }
        h1 {
            margin-left: 100px;
        }
        a {
            background-color: white;
        }
        #btn_gp {
            margin-left: 100px;
        }
        .log {
            margin-left: 100px;
            width: auto;
        }
    </style>
</head>
<body>
    <h1> Users database </h1><br>
    <c:choose>
        <c:when test="${empty sessionScope.login}">
            <div class="log">
                <a href="${pageContext.servletContext.contextPath}/sign" class="btn btn-default" role="button" style="color: red">Login to continue!</a>
            </div><br/>
        </c:when>
        <c:otherwise>
            <div class="log">
                <a href="${pageContext.servletContext.contextPath}/out" class="btn btn-default" role="button" style="color: green">Login out</a>
            </div><br/>
        </c:otherwise>
    </c:choose>
    <p style="margin-left: 100px">Here you can manage users in database:</p>
    <div id="btn_gp" class="btn-group-vertical">
        <a href="${pageContext.servletContext.contextPath}/create" class="btn btn-default" role="button">Add new user</a>
        <a href="${pageContext.servletContext.contextPath}/delete" class="btn btn-default" role="button">Delete user</a>
        <a href="${pageContext.servletContext.contextPath}/update" class="btn btn-default" role="button">Update user</a>
        <a href="${pageContext.servletContext.contextPath}/display" class="btn btn-default" role="button">See user's info (by login)</a>
        <a href="${pageContext.servletContext.contextPath}/role" class="btn btn-default" role="button">Edit roles</a>
    </div>
</body>
</html>
