<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            margin: 0;
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

        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        * {
            box-sizing: border-box;
        }

        .container {
            padding: 16px;
            background-color: white;
        }

        input[type=text], input[type=password] {
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: none;
            background: #f1f1f1;
        }

        input[type=text]:focus, input[type=password]:focus {
            background-color: #ddd;
            outline: none;
        }

        hr {
            border: 1px solid #f1f1f1;
            margin-bottom: 25px;
        }

        .registerbtn {
            background-color: #4CAF50;
            color: white;
            padding: 16px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
            opacity: 0.9;
            text-align: center;
        }

        .registerbtn:hover {
            opacity: 1;
        }

        a {
            color: dodgerblue;
            text-align: center;
            font-size: 20px;
        }

        .signin {
            background-color: #f1f1f1;
            text-align: center;
        }

        title {
            padding: 15px 0px;
        }

        p {
            text-align: center;
        }
        /*button.re {*/
        /*    background-color: #030daf;*/
        /*    color: white;*/
        /*    padding: 16px 20px;*/
        /*    margin: 8px 0;*/
        /*    border: none;*/
        /*    cursor: pointer;*/
        /*    width: 100%;*/
        /*    opacity: 0.9;*/
        /*    text-align: center;*/
        /*}*/

        button.re {
            background-color: #030daf;
            color: white;
            padding: 16px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
            opacity: 0.9;
            text-align: center;
        }

    </style>
</head>
<body>
<h1 align="center"><b>Sign in</b></h1>
<ul class="menu">
    <li><a href="${pageContext.request.contextPath}/index">Main page</a></li>
    <li><a href="${pageContext.request.contextPath}/servlet/registration">Registration</a></li>
    <li><a href="${pageContext.request.contextPath}/internetShop">Items list</a></li>
    <li><a href="${pageContext.request.contextPath}/getBucket?user_id=1">Check your bucket</a></li>
    <li><a href="${pageContext.request.contextPath}/orders?user_id=1">Check your orders</a></li>
    <li><a href="${pageContext.request.contextPath}/servlet/getAllItems"> Add items to storage</a></li>
    <li><a href="${pageContext.request.contextPath}/servlet/getAllUsers">List of users</a></li>
    <li><a href="${pageContext.request.contextPath}/servlet/getAllOrders"> List of orders </a></li>
</ul>
<div style="margin-left:25%;padding:1px 16px;height:1000px;">
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="container">
            <h1>Registration</h1>
            <p>Please fill in this form to sign in account.</p>
            <hr>

            <label for="Username"><b>Username</b></label>
            <input type="text" placeholder="Enter username" name="username" required>

            <label for="Password"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" required>


            <button type="submit" class="re">Sign in</button>
            <button class="re" onclick="location.href='${pageContext.request.contextPath}/servlet/registration'" type="button">Registration</button>

        </div>

        <div align="center">
        </div >
    </form>
</div>
</body>
</html>
