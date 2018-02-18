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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        html {
            height: 100%;
        }
        body {
            /*margin: 0; !* Убираем отступы *!*/
            height: 100%; /* Высота страницы */
            background-image: url("${pageContext.servletContext.contextPath}/resources/images/uzor-9.jpg");
            background-repeat: no-repeat;
            background-size: contain;
            background-position: right;
        }
        h1, h2 {
            margin-left: 100px;
            color: gray;
        }
        .table-04 {
            background-color: white;
            border: 5px solid;
            border-radius: 5px;
            margin-left: 100px;
            width: 500px
        }
        a {
            margin-left: 100px;
            background-color: white;
        }
        #form-01 {
            width: 300px;
            margin-left: 100px;
        }
    </style>
</head>
<body>
<h1>Roles editor</h1>
<br/>
<a href="${pageContext.servletContext.contextPath}/out" style="color: green" class="btn btn-default" role="button">Login out</a>
<br/>
<br/>
<c:choose>
    <c:when test="${not empty requestScope.roles}">
        <div class="table-04">
            <table class="table">
                <tr><th>Role</th><th>Delete</th></tr>
                <c:forEach items="${requestScope.roles}" var="role">
                    <tr>
                        <td>
                            <c:out value="${role.role}"/>
                        </td>
                        <td>
                            <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/role" method="post">
                                <input type="hidden" name="roleName" value="${role.role}">
                                <input type="hidden" name="action" value="delete">
                                <input type="submit" value="Delete" class="btn btn-default">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <br/>
        <h2>Add new role</h2>
        <br/>
        <form id="form-01" class="form-horizontal" action="${pageContext.servletContext.contextPath}/role" method="post">
            <input class="form-control" type="text" name="roleName" placeholder="Role name" required>
            <input type="hidden" name="action" value="create">
            <input type="submit" value="Create" class="btn btn-default">
        </form>
    </c:when>
    <c:otherwise>
        <p style="color: red">You don't have rights to edit roles</p>
    </c:otherwise>
</c:choose>
<br/>
<a href="${pageContext.servletContext.contextPath}" class="btn btn-default" role="button">Back to main page</a>
</body>
</html>
