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
</head>
<body>
    <h1> Users database </h1><br>
    <a href="${pageContext.servletContext.contextPath}/out" style="color: green">Login out</a><br/><br/><br/>
    <table align='centre' bordercolor='#008000' border='2'>
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
                    <c:out value="${user.createDate}"/>
                </td>
                <c:choose>
                    <c:when test="${sessionScope.role == 'admin'}">
                        <td>
                            <form action="${pageContext.servletContext.contextPath}/update" method="get">
                                <input type="submit" value="Update">
                                <input type="hidden" name="oldLogin" value="${user.login}">
                            </form>
                        </td>
                        <td>
                            <form action="${pageContext.servletContext.contextPath}/delete" method="post">
                                <input type="submit" value="Delete">
                                <input type="hidden" name="login" value="${user.login}">
                            </form>
                        </td>
                    </c:when>
                    <c:when test="${sessionScope.login == user.login}">
                        <td>
                            <form action="${pageContext.servletContext.contextPath}/update" method="get">
                                <input type="submit" value="Update">
                                <input type="hidden" name="oldLogin" value="${user.login}">
                            </form>
                        </td>
                    </c:when>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <c:if test="${sessionScope.role != 'admin'}">
        <p style="color: red">You don't have rights to delete users</p>
    </c:if>
    <br/>
    <a href="${pageContext.servletContext.contextPath}">Back to main page</a>
</body>
</html>
