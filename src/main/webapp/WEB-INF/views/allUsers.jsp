<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="users" scope="request" type="java.util.List<mate.academy.internetshop.model.User>"/>
<jsp:useBean id="greeting" scope="request" type="java.lang.String"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
    <title>All Users</title>
        <style>
            <%@include file='/style/style.css'%>
        </style>
    </head>
    <body>
    <ul class="menu">
        <li><a href="${pageContext.request.contextPath}/servlet/index">Main page</a></li>
        <hr>
        <h2 class="menu"><b>REG/LOG</b></h2>
        <li><a href="${pageContext.request.contextPath}/registration">Registration</a></li>
        <li><a href="${pageContext.request.contextPath}/login">LogIn</a></li>
        <li><a href="${pageContext.request.contextPath}/logout">LogOut</a></li>
        <hr>
        <h2 class="menu"><b>USER UTILS</b></h2>
        <li><a href="${pageContext.request.contextPath}/servlet/internetShop">Items list</a></li>
        <li><a href="${pageContext.request.contextPath}/servlet/getBucket">Check your bucket</a></li>
        <li><a href="${pageContext.request.contextPath}/servlet/orders">Check your orders</a></li>
        <hr>
        <h2 class="menu"><b>ADMIN UTIL</b></h2>
        <li><a href="${pageContext.request.contextPath}/servlet/getAllItems"> Add items to storage</a></li>
        <li><a href="${pageContext.request.contextPath}/servlet/getAllUsers">List of users</a></li>
        <li><a href="${pageContext.request.contextPath}/servlet/getAllOrders"> List of orders </a></li>
        <hr>
        <li><a href="${pageContext.request.contextPath}/inject">INJECT BUTTON</a></li>
    </ul>
    <h1 align="center"><b class="title">Hello, ${greeting}, welcome to all users page</b></h1>
        <div class="tables">
        <table border="1">
            <tr>
                <th> ID </th>
                <th> Username </th>
                <th> FirstName </th>
                <th> LastName </th>
                <th> Delete </th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>
                        <c:out value="${user.userId}" />
                    </td>
                    <td>
                        <c:out value="${user.userName}" />
                    </td>
                    <td>
                        <c:out value="${user.firstName}" />
                    </td>
                    <td>
                        <c:out value="${user.secondName}" />
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/servlet/deleteUsers?user_id=${user.userId}">DELETE</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </div>
    </body>
</html>
