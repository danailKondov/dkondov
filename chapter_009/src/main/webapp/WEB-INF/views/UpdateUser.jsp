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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        //      проверки на наличие значений избыточны из-за "required" в form,
        //      поэтому здесь мы будем проверять длинну пароля (не меньше 8 символов),
        //      наличие в нем строчных и прописных букв, а так же цифр
        function validatePass() {
            var result = false;
            var pass = document.getElementById("pass").value;
            var regex1 = RegExp('[0-9]');
            var regex2 = RegExp('[A-Z]');
            var regex3 = RegExp('[a-z]');
            if (pass.length >= 8 && regex1.test(pass) && regex2.test(pass) && regex3.test(pass)) {
                result = true;
            } else {
                alert('Пароль должен содержать не менее 8 символов, а так же цифры, строчные и прописные буквы!');
            }
            return result;
        }
        function updateCitiesByCountry() {
            var country = document.getElementById("country-opt").value;
            $.ajax('./cities?country=' + country, {
                method: 'get',
                complete: function (data) {
                    var cities = JSON.parse(data.responseText);
                    for(var i = 0; i < cities.length; ++i) {
                        document.getElementById("city-opt").options[i] = new Option(cities[i].name, cities[i].name);
                    }
                }
            })
        }
    </script>
    <style>
        html {
            height: 100%;
        }
        body {
            margin: 0; /* Убираем отступы */
            height: 100%; /* Высота страницы */
            background-image: url("${pageContext.servletContext.contextPath}/resources/images/uzor-7.jpg");
            background-repeat: no-repeat;
            background-size: cover; /*растягиваем*/
        }
        h1, h2 {
            margin-left: 100px;
            color: whitesmoke;
        }
        .table-03 {
            margin-left: 50px;
            margin-right: 50px;
            background-color: white;
        }
        a {
            margin-left: 100px;
            background-color: white;
        }
        #add-01 {
            width: 300px;
            margin-left: 100px;
        }
    </style>
</head>
<body>
    <h1> Users database </h1><br>
    <a href="${pageContext.servletContext.contextPath}/out" style="color: green" class="btn btn-default" role="button">Login out</a>
    <br/>
    <br/>
    <div class="table-03">
        <table class="table">
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
    <br>
    <br>
    <c:if test="${not empty param.oldLogin}">
        <h2> Update user - enter new data: </h2>
        <form id="add-01" class="form-horizontal" action="${pageContext.servletContext.contextPath}/update" method="POST" onsubmit="return validatePass();">
            <div class="form-group">
                <div class="col-sm-10">
                    <input class="form-control" type="text" name="name" placeholder="Name" required>
                </div>

            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <input class="form-control" type="text" name="login" placeholder="Login" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <input class="form-control" type="email" name="email" placeholder="E-mail" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <input id="pass" class="form-control" type="text" name="password" placeholder="Password" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <select id="country-opt" class="form-control" name="country" required onchange="updateCitiesByCountry()">
                        <c:forEach var="country" items="${requestScope.countries}">
                            <option>${country.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <select id="city-opt" class="form-control" name="city" required></select>
                </div>
            </div>
            <c:choose>
                <c:when test="${sessionScope.role == 'admin'}">
                    <div class="form-group">
                        <div class="col-sm-10">
                            <select class="form-control" name="role" required>
                                <c:forEach var="role" items="${roles}">
                                    <option>${role.role}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="role" value="${sessionScope.role}">
                </c:otherwise>
            </c:choose>
            <br>
                <%--${param.oldLogin} вместо <%=request.getParameter("oldLogin")%>--%>
            <input type="hidden" name="oldLogin" value="${param.oldLogin}">
            <div class="form-group">
                <div class="col-sm-10">
                    <input class="btn btn-default" type="submit" value="Submit">
                </div>
            </div>
        </form>
    </c:if>

    <br>
    <a href="${pageContext.servletContext.contextPath}" class="btn btn-default" role="button">Back to main page</a>
</body>
</html>
