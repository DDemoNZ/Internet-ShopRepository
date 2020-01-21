<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Access denied</title>
    <style>
        <%@include file='/style/style.css'%>
    </style>
</head>
<body>

<ul class="menu">
    <h2 class="menu"><b>REG/LOG</b></h2>
    <li><a href="${pageContext.request.contextPath}/servlet/index">Main page</a></li>
    <hr>
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
<h1 align="center"><b class="title">ACCESS</b></h1>
<%--<div align="center" style="font-size: 50px;padding-left: 30%" class="NotPass">YOU SHALL NOT PASS</div>--%>
<div class="NotPass">YOU SHALL NOT PASS</div>
<div class="access" >(because access denied)</div>
<div align="center" style="padding-left: 25%;padding-bottom: 10%;size: auto"><img class="gendalf" style="size: legal" src="<c:url value="/webContent/image/8be247af7594ac0c5426a256363a86e1.png"/>"/></div>
</body>
</html>
