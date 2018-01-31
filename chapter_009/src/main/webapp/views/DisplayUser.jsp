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
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p> Enter user's login: </p>
    <form action="<%=request.getContextPath()%>/display" method="post">
        Login: <input type="text" name="login">
        <input type="submit" value="Submit">
    </form>
    <br>
    <p>User to display: </p>
    <p>
        <%-- какая-то содомия со скриплетами чтобы сделать out.write с проверкой user на null
        интересно, есть другой способ? --%>
        <% User user = (User) request.getAttribute("user");
            PrintWriter writer = response.getWriter();
            if (user != null) {%>
            <%=user.toString()%>
            <%} else {%>
            no user to view
            <%}%>
    </p>
    <br>
    <a href="<%=request.getContextPath()%>">Back to main page</a>
</body>
</html>
