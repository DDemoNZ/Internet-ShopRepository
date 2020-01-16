<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Access denied</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        a {
            color: dodgerblue;
            text-align: center;
            font-size: 20px;
        }

        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            width: 25%;
            background-color: #f1f1f1;
            position: fixed;
            height: 100%;
            overflow: auto;
        }

        li a {
            display: block;
            color: #000;
            padding: 8px 16px;
            text-decoration: none;
        }

        li a.active {
            background-color: #4CAF50;
            color: white;
        }

        li a:hover:not(.active) {
            background-color: #555;
            color: white;
        }

        title {
            padding: 15px 0px;
        }

        body {
            font-family: Arial, Helvetica, sans-serif;
        }
    </style>
</head>
<body>
<h1 align="center">ACCESS</h1>
<ul class="menu">
    <li style="align-content: center"><b>MENU</b></li>
    <li><a href="${pageContext.request.contextPath}/servlet/index">Main page</a></li>
    <li style="align-content: center"><b>REG/LOG</b></li>
    <li><a href="${pageContext.request.contextPath}/registration">Registration</a></li>
    <li><a href="${pageContext.request.contextPath}/login">LogIn</a></li>
    <li><a href="${pageContext.request.contextPath}/logout">LogOut</a></li>
    <li><b>USER UTILS</b></li>
    <li><a href="${pageContext.request.contextPath}/servlet/internetShop">Items list</a></li>
    <li><a href="${pageContext.request.contextPath}/servlet/getBucket">Check your bucket</a></li>
    <li><a href="${pageContext.request.contextPath}/servlet/orders">Check your orders</a></li>
    <li><b>ADMIN UTILS</b></li>
    <li><a href="${pageContext.request.contextPath}/servlet/getAllItems"> Add items to storage</a></li>
    <li><a href="${pageContext.request.contextPath}/servlet/getAllUsers">List of users</a></li>
    <li><a href="${pageContext.request.contextPath}/servlet/getAllOrders"> List of orders </a></li>
    <li><a href="${pageContext.request.contextPath}/inject">INJECT BUTTON</a></li>
</ul>
<div align="center" style="font-size: 60px;padding-left: 20%;padding-top: 10%">YOU SHALL NOT PASS (because access denied)</div>
<div style="padding-left: 25%;size: auto"><img src="<c:url value="/webContent/image/8be247af7594ac0c5426a256363a86e1.png"/>"/></div>
</body>
</html>
