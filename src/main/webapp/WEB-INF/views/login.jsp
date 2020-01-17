<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
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
<h1 align="center"><b class="title">Sign in</b></h1>
<div class="tables">
    <div class="errorLogin" >${errorMsg}</div>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="container">
            <h1 align="center">Registration</h1>
            <p align="center">Please fill in this form to sign in account.</p>
            <hr>

            <label for="Username"><b>Username</b></label>
            <input type="text" placeholder="Enter username" name="username" required>

            <label for="Password"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" required>

            <button type="submit" class="re">Sign in</button>
            <button class="re" onclick="location.href='${pageContext.request.contextPath}/registration'" type="button">Registration</button>
        </div>
        <div></div >
    </form>
</div>
</body>
</html>
