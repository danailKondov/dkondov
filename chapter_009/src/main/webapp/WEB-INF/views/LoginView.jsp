<%--
  Created by IntelliJ IDEA.
  Date: 13.02.2018
  Time: 11:26
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        body {
            margin: 0; /* Убираем отступы */
            height: 100%; /* Высота страницы */
            background-image: url("${pageContext.servletContext.contextPath}/resources/images/uzor-5.jpg");
            background-repeat: no-repeat;
            background-size: contain;
        }
        form {
            width: 300px;
            margin-left: 100px;
        }
        h1 {
            margin-left: 100px;
            color: whitesmoke;
        }
    </style>
</head>
<body>
    <h1>Enter login</h1><br/>
    <c:if test="${error != ' '}">
        <div style="color: red; margin-left: 100px" >
            <c:out value="${error}"/>
        </div>
    </c:if>
    <form action="${pageContext.servletContext.contextPath}/sign" method="post">
        <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <input type="text" class="form-control" name="login" placeholder="login">
        </div>
        <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
            <input type="password" class="form-control" name = "password" placeholder="password">
        </div>
        <input type="submit" class="btn btn-default">
    </form>
</body>
</html>
