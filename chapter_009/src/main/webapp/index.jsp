<%--
  Created by IntelliJ IDEA.
  User: Kondov Danail (mailto:dkondov@yandex.ru)
  Date: 31.01.2018
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users database</title>
</head>
<body>
    <h1> Users database </h1><br>
    <p>Here you can manage users in database:</p>
    <ol>
        <li>
            <a href="<%=request.getContextPath()%>/create">Add new user</a>
        </li>
        <li>
            <a href="<%=request.getContextPath()%>/delete">Delete user</a>
        </li>
        <li>
            <a href="<%=request.getContextPath()%>/update">Update user</a>
        </li>
        <li>
            <a href="<%=request.getContextPath()%>/display">See user's info (by login)</a>
        </li>
    </ol>
</body>
</html>
