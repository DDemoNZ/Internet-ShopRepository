<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Registration</title>
        <style>
            <%@include file="/style/style.css"%>
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
        <h1 align="center"><b class="title">Let's create a new User!</b></h1>
        <div class="tables">
        <form action="${pageContext.request.contextPath}/registration" method="post">
            <div class="container">
                <h1>Register</h1>
                <p>Please fill in this form to create an account.</p>
                <hr>

                <label for="Username"><b>Username</b></label>
                <input type="text" placeholder="Enter username" name="username" required>

                <label for="Password"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" required>

                <label for="Password"><b>Repeat Password</b></label>
                <input type="password" placeholder="Repeat Password" name="password-repeat" required>

                <label for="First-name"><b>First name</b></label>
                <input type="text" placeholder="Enter your first name" name="first_name" required>

                <label for="Last-name"><b>Last name</b></label>
                <input type="text" placeholder="Enter your last name" name="second_name" required>
                <hr>

                <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
                <button type="submit" class="re">Register</button>
            </div>

            <div class="container signin" align="center">
                <p>Already have an account? <a href=${pageContext.request.contextPath}/login>Sign in</a>.</p>
            </div>
        </form>
        </div>
        </body>
</html>
