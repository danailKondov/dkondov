<%@ page import="ru.job4j.crud.model.User" %>
<%@ page import="java.io.Writer" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Kondov Danail (mailto:dkondov@yandex.ru)
  Date: 31.01.2018
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
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
            background-image: url("${pageContext.servletContext.contextPath}/resources/images/uzor-8.jpg");
            background-repeat: no-repeat;
            background-size: cover; /*растягиваем*/
        }
        h1, h2 {
            margin-left: 100px;
            color: whitesmoke;
        }
        a {
            margin-left: 100px;
            background-color: white;
        }
        p {
            margin-left: 100px;
            color: white;
            width: 300px;
        }
        #add-01 {
            width: 300px;
            margin-left: 100px;
        }
    </style>
</head>
<body>
    <a href="${pageContext.servletContext.contextPath}/out" style="color: green" class="btn btn-default" role="button">Login out</a><br/>
    <p> Enter user's login: </p>
    <form id="add-01" class="form-horizontal" action="${pageContext.servletContext.contextPath}/display" method="post">
        <input class="form-control" type="text" name="login" placeholder="Login">
        <input type="submit" value="Submit" class="btn btn-default">
    </form>
    <br>
    <p>User to display: </p>
    <p>
            <c:choose>
                <c:when test="${not empty user}">
                    <c:out value="${user}"/>
                </c:when>
                <c:otherwise>
                    no user to view
                </c:otherwise>
            </c:choose>
    </p>
    <br>
    <a href="${pageContext.servletContext.contextPath}" class="btn btn-default" role="button">Back to main page</a>
</body>
</html>
