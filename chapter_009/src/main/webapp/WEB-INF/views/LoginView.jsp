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
</head>
<body>
    <c:if test="${error != ' '}">
        <div style="color: red" >
            <c:out value="${error}"/>
        </div>
    </c:if>
    <form action="${pageContext.servletContext.contextPath}/sign" method="post">
        <label>Login: </label>
        <input type="text" name="login"><br/>
        <label>Password: </label>
        <input type="password" name = "password"><br/>
        <input type="submit">
    </form>
</body>
</html>
