<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Registration</title>
    </head>
        <body>
        <h1 align="center"><b>Let's create a new User!</b></h1>
        <form action="/Internet_ShopProject_war_exploded/servlet/registration" method="post">
            <div class="container">
                <h1>Register</h1>
                <p>Please fill in this form to create an account.</p>
                <hr>

                <label for="username"><b>username</b></label>
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
        <a href="${pageContext.request.contextPath}/index">Back to main page</a>
        </body>
</html>
