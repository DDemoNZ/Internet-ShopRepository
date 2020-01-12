<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Registration</title>
        <style>
            body {
                margin: 0;
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
            }

            .registerbtn:hover {
                opacity: 1;
            }

            a {
                color: dodgerblue;
            }

            .signin {
                background-color: #f1f1f1;
                text-align: center;
            }

            title {
                padding: 15px 0px;
            }
        </style>
    </head>
        <body>
        <h1 align="center"><b>Let's create a new User!</b></h1>
        <ul class="menu">
            <li><a href="${pageContext.request.contextPath}/servlet/registration">Registration</a></li>
            <li><a href="${pageContext.request.contextPath}/internetShop">Items list</a></li>
            <li><a href="${pageContext.request.contextPath}/getBucket?user_id=1">Check your bucket</a></li>
            <li><a href="${pageContext.request.contextPath}/orders?user_id=1">Check your orders</a></li>
            <li><a href="${pageContext.request.contextPath}/servlet/getAllItems"> Add items to storage</a></li>
            <li><a href="${pageContext.request.contextPath}/servlet/getAllUsers">List of users</a></li>
            <li><a href="${pageContext.request.contextPath}/servlet/getAllOrders"> List of orders </a></li>
        </ul>

        <div style="margin-left:25%;padding:1px 16px;height:1000px;">
        <form action="${pageContext.request.contextPath}/servlet/registration" method="post">
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
                <p>Already have an account? <a href="#">Sign in</a>.</p>
            </div>
        </form>
        </div>
        </body>
</html>
