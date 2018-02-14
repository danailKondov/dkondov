<%--
  Created by IntelliJ IDEA.
  Date: 13.02.2018
  Time: 22:57
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Role Editor</title>
</head>
<body>
<h1>Roles editor</h1>
<br/>
<c:choose>
    <c:when test="${not empty requestScope.roles}">
        <table align='centre' bordercolor='#008000' border='2'>
            <tr><th>Role</th><th>Delete</th></tr>
            <c:forEach items="${requestScope.roles}" var="role">
                <tr>
                    <td>
                        <c:out value="${role.role}"/>
                    </td>
                    <td>
                        <form action="${pageContext.servletContext.contextPath}/role" method="post">
                            <input type="hidden" name="roleName" value="${role.role}">
                            <input type="hidden" name="action" value="delete">
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <h2>Add new role</h2>
        <br/>
        <form action="${pageContext.servletContext.contextPath}/role" method="post">
            <label>New role: <input type="text" name="roleName" value="new role" required></label><br/>
            <input type="hidden" name="action" value="create">
            <input type="submit" value="Create">
        </form>
    </c:when>
    <c:otherwise>
        <p style="color: red">You don't have rights to edit roles</p>
    </c:otherwise>
</c:choose>
<br/>
<a href="${pageContext.servletContext.contextPath}">Back to main page</a>
</body>
</html>
