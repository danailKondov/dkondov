<%@ page import="ru.job4j.crud.model.User" %>
<%@ page import="ru.job4j.crud.model.UserStore" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Kondov Danail (mailto:dkondov@yandex.ru)
  Date: 31.01.2018
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create user page</title>
</head>
<body>
<h1> Users database </h1><br>
<a href="${pageContext.servletContext.contextPath}/out" style="color: green">Login out</a><br/><br/>
<table align='centre' bordercolor='#008000' border='2'>
    <tr><th> Name </th><th> Login </th><th> Password </th><th> Role </th><th> E-mail </th><th> Data </th><th> Update </th>
    <c:if test="${sessionScope.role == 'admin'}">
        <th> Delete </th>
    </c:if>
    </tr>
    <%--получаем users из атрибутов request, вложенных сервлетом--%>
    <c:forEach items="${users}" var="user">
    <tr>
        <td>
            <%--вместо <%=user.getName()%>--%>
            <c:out value="${user.name}"/>
        </td>
        <td>
            <c:out value="${user.login}"/>
        </td>
        <td>
            <c:out value="${user.password}"/>
        </td>
        <td>
            <c:out value="${user.role}"/>
        </td>
        <td>
            <c:out value="${user.email}"/>
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
<br>
<br>
<c:if test="${not empty createErrorMessage}">
    <p style="color: red;"><c:out value="${createErrorMessage}"/></p>
</c:if>
<br>
<br>
<c:choose>
    <c:when test="${sessionScope.role == 'admin'}">
        <h2> Add new user - enter new data: </h2>
        <form action="${pageContext.servletContext.contextPath}/create" method="POST">
            Name:   <input type="text" name="name" value="new name" required><br>
            Login:  <input type="text" name="login" value="new login" required><br>
            E-mail: <input type="text" name="email" value="newemail@email.com" required><br>
            Password: <input type="text" name="password" value="newPassword" required><br>
                <%--выпадающий список c доступными ролями--%>
            Role: <select name="role" required>
            <c:forEach var="role" items="${roles}">
                <option>${role.role}</option>
            </c:forEach>
        </select><br/>
            <input type="submit" value="Submit">
        </form>
    </c:when>
    <c:otherwise>
        <p style="color: red">You don't have rights to enter new users</p>
    </c:otherwise>
</c:choose>

<br>
<div>
    <a href="${pageContext.servletContext.contextPath}">Back to main page</a>
</div>
</body>
</html>
