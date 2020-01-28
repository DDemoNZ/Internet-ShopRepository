<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DB ERROR</title>
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
<h1 class="dbErrors">DATA BASE ERROR</h1>
<div class="dbErrors" >${errorMsg}</div>
</body>
</html>
