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
<!DOCTYPE html>
<html>
<head>
    <title>Delete user page</title>
</head>
<body>
    <h1> Users database </h1><br>
    <table align='centre' bordercolor='#008000' border='2'>
        <tr><td> Name </td><td> Login </td><td> E-mail </td><td> Data </td><td> Update </td><td> Delete </td></tr>
        <%List<User> users = UserStore.getInstance().getAllUsers();%>
        <%for (User user : users) {%>
        <tr>
            <td>
                <%=user.getName()%>
            </td>
            <td>
                <%=user.getLogin()%>
            </td>
            <td>
                <%=user.getEmail()%>
            </td>
            <td>
                <%=user.getCreateDate()%>
            </td>
            <td>
                <form action="<%=request.getContextPath()%>/update" method="get">
                    <input type="submit" value="Update">
                    <input type="hidden" name="oldLogin" value="<%=user.getLogin()%>">
                </form>
            </td>
            <td>
                <form action="<%=request.getContextPath()%>/delete" method="post">
                    <input type="submit" value="Delete">
                    <input type="hidden" name="login" value="<%=user.getLogin()%>">
                </form>
            </td>
        </tr>
        <%}%>
    </table>
    <br>
    <a href="<%=request.getContextPath()%>">Back to main page</a>
</body>
</html>
