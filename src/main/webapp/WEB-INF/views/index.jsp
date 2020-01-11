<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Main Page</title>
    </head>
    <body>
        <h1 align="center"><b>Main page</b></h1>
        <ul class="menu">
            <li><a href="${pageContext.request.contextPath}/servlet/registration">Registration</a></li>
            <li><a href="${pageContext.request.contextPath}/internetShop">Items list</a></li>
            <li><a href="${pageContext.request.contextPath}/getBucket">Your bucket</a></li>
            <li><a href="${pageContext.request.contextPath}/servlet/getAllItems"> Add items to storage</a></li>
            <li><a href="${pageContext.request.contextPath}/servlet/getAllUsers"> List of all users </a></li>
        </ul>
    </body>
</html>