<%@ page import="ru.job4j.crud.model.User" %>
<%@ page import="ru.job4j.crud.model.UserStore" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Kondov Danail (mailto:dkondov@yandex.ru)
  Date: 31.01.2018
  Time: 15:28
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update user page</title>
</head>
<body>
    <h1> Users database </h1><br>
    <a href="${pageContext.servletContext.contextPath}/out" style="color: green">Login out</a><br/>
    <table align='centre' bordercolor='#008000' border='2'>
        <tr><th> Name </th><th> Login </th><th> Password </th><th> Role </th><th> E-mail </th><th> Data </th><th> Update </th>
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
    <c:if test="${not empty param.oldLogin}">
        <h2> Update user - enter new data: </h2>
        <form action="${pageContext.servletContext.contextPath}/update" method="POST">
            <label>Name: <input type="text" name="name" value="new name" required></label><br>
            <label>Login: <input type="text" name="login" value="new login" required></label><br>
            <label>Password: <input type="text" name="password" value="newPassword" required></label><br>
            <label>E-mail: <input type="text" name="email" value="newemail@email.com" required></label><br>
            <c:choose>
                <c:when test="${sessionScope.role == 'admin'}">
                    <label><select name="role" required>
                        <c:forEach var="role" items="${roles}">
                            <option>${role.role}</option>
                        </c:forEach>
                    </select>Role: </label><br/>
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="role" value="${sessionScope.role}">
                </c:otherwise>
            </c:choose>
            <br>
                <%--${param.oldLogin} вместо <%=request.getParameter("oldLogin")%>--%>
            <input type="hidden" name="oldLogin" value="${param.oldLogin}">
            <input type="submit" value="Submit">
        </form>
    </c:if>

    <br>
    <a href="${pageContext.servletContext.contextPath}">Back to main page</a>
</body>
</html>
