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
</head>
<body>
    <a href="${pageContext.servletContext.contextPath}/out" style="color: green">Login out</a><br/>
    <p> Enter user's login: </p>
    <form action="${pageContext.servletContext.contextPath}/display" method="post">
        Login: <input type="text" name="login">
        <input type="submit" value="Submit">
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
    <a href="${pageContext.servletContext.contextPath}">Back to main page</a>
</body>
</html>
