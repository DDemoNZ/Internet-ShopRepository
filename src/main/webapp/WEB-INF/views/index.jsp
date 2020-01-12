<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
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
        <h1 align="center"><b>Main page</b></h1>
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

        </div>
    </body>
</html>