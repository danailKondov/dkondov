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
</head>
<body>
    <h1> Users database </h1><br>
    <c:choose>
        <c:when test="${empty sessionScope.login}">
            <p>
                <a href="${pageContext.servletContext.contextPath}/sign" style="color: red">Login to continue!</a>
            </p><br/>
        </c:when>
        <c:otherwise>
            <p>
                <a href="${pageContext.servletContext.contextPath}/out" style="color: green">Login out</a>
            </p><br/>
        </c:otherwise>
    </c:choose>
    <p>Here you can manage users in database:</p>
    <ol>
        <li>
            <a href="${pageContext.servletContext.contextPath}/create">Add new user</a>
        </li>
        <li>
            <a href="${pageContext.servletContext.contextPath}/delete">Delete user</a>
        </li>
        <li>
            <a href="${pageContext.servletContext.contextPath}/update">Update user</a>
        </li>
        <li>
            <a href="${pageContext.servletContext.contextPath}/display">See user's info (by login)</a>
        </li>
        <li>
            <a href="${pageContext.servletContext.contextPath}/role">Edit roles</a>
        </li>
    </ol>
</body>
</html>
