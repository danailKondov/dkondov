<%@ page import="ru.job4j.crud.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.job4j.crud.model.UserStore" %><%--
  Created by IntelliJ IDEA.
  User: Kondov Danail (mailto:dkondov@yandex.ru)
  Date: 31.01.2018
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delete user page</title>
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
            background-image: url("${pageContext.servletContext.contextPath}/resources/images/uzor-4.jpg");
            background-repeat: no-repeat;
            background-size: contain;
            background-position: right;
        }
        h1, h2 {
            margin-left: 100px;
            color: gray;
        }
        .table-02 {
            margin-left: 50px;
            margin-right: 50px;
            background-color: white;
            border: 5px solid;
            border-radius: 5px;
        }
        a {
            margin-left: 100px;
            background-color: white;
        }
    </style>
</head>
<body>
    <h1> Users database </h1><br>
    <a href="${pageContext.servletContext.contextPath}/out" style="color: green" class="btn btn-default" role="button">Login out</a><br/><br/><br/>
    <div class="table-02">
        <table class="table">
            <tr><th> Name </th><th> Login </th><th> E-mail </th><th> Password </th><th> Role </th><th> Data </th><th> Update </th>
                <c:if test="${sessionScope.role == 'admin'}">
                    <th> Delete </th>
                </c:if>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>
                        <c:out value="${user.name}"/>
                    </td>
                    <td>
                        <c:out value="${user.login}"/>
                    </td>
                    <td>
                        <c:out value="${user.email}"/>
                    </td>
                    <td>
                        <c:out value="${user.password}"/>
                    </td>
                    <td>
                        <c:out value="${user.role}"/>
                    </td>
                    <td>
                        <c:out value="${user.country}"/>
                    </td>
                    <td>
                        <c:out value="${user.city}"/>
                    </td>
                    <td>
                        <c:out value="${user.createDate}"/>
                    </td>
                    <c:choose>
                        <c:when test="${sessionScope.role == 'admin'}">
                            <td>
                                <form action="${pageContext.servletContext.contextPath}/update" method="get">
                                    <input type="submit" value="Update" class="btn btn-default">
                                    <input type="hidden" name="oldLogin" value="${user.login}">
                                </form>
                            </td>
                            <td>
                                <form action="${pageContext.servletContext.contextPath}/delete" method="post">
                                    <input type="submit" value="Delete" class="btn btn-default">
                                    <input type="hidden" name="login" value="${user.login}">
                                </form>
                            </td>
                        </c:when>
                        <c:when test="${sessionScope.login == user.login}">
                            <td>
                                <form action="${pageContext.servletContext.contextPath}/update" method="get">
                                    <input type="submit" value="Update" class="btn btn-default">
                                    <input type="hidden" name="oldLogin" value="${user.login}">
                                </form>
                            </td>
                        </c:when>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
    </div>
    <br/>
    <c:if test="${sessionScope.role != 'admin'}">
        <p style="color: red">You don't have rights to delete users</p>
    </c:if>
    <br/>
    <a href="${pageContext.servletContext.contextPath}" class="btn btn-default" role="button">Back to main page</a>
</body>
</html>
