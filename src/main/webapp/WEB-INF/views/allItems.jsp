<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="items" scope="request" type="java.util.List"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AllItems</title>
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
            padding: 50px 0px;
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

        table, td, th {
            border: 1px solid #ddd;
            text-align: left;
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            padding: 15px;
        }

        title {
            padding: 15px 0px;
        }

        button {
            background-color: #0a791d;
            color: white;
            padding: 16px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
            opacity: 0.9;
            text-align: center;
        }

        input {
            width: 30%;
        }

    </style>
</head>
    <body>
    <h1 align="center">All items page</h1>
    <ul class="menu">
        <li style="align-content: center"><b>MENU</b></li>
        <li><a href="${pageContext.request.contextPath}/servlet/index">Main page</a></li>
        <li><b></b></li>
        <li><a href="${pageContext.request.contextPath}/registration">Registration</a></li>
        <li><a href="${pageContext.request.contextPath}/login">LogIn</a></li>
        <li><a href="${pageContext.request.contextPath}/logout">LogOut</a></li>
        <li><b></b></li>
        <li><a href="${pageContext.request.contextPath}/servlet/internetShop">Items list</a></li>
        <li><a href="${pageContext.request.contextPath}/servlet/getBucket">Check your bucket</a></li>
        <li><a href="${pageContext.request.contextPath}/servlet/orders">Check your orders</a></li>
        <li><b></b></li>
        <li><a href="${pageContext.request.contextPath}/servlet/getAllItems"> Add items to storage</a></li>
        <li><a href="${pageContext.request.contextPath}/servlet/getAllUsers">List of users</a></li>
        <li><a href="${pageContext.request.contextPath}/servlet/getAllOrders"> List of orders </a></li>
        <li><a href="${pageContext.request.contextPath}/inject">INJECT BUTTON</a></li>
    </ul>
        <div style="margin-left:25%;padding:1px 16px;height:1000px;">
        <form action="${pageContext.request.contextPath}/servlet/addItems" method="get">
            <div class="container">
                <h1>Add items</h1>
                <p>Please fill in this form to create an item</p>
                <hr>
                <label for="name"><b>ItemName</b></label>
                <input type="text" placeholder="Enter item name" name="name">
                <label for="price"><b>ItemPrice</b></label>
                <input type="text" placeholder="Enter item price" name="price">
                <hr>
                <button type="submit">ADD</button>
            </div>
        </form>
    <table border="1">
        <tr>
            <th> ID </th>
            <th> Name </th>
            <th> Price </th>
            <th> DELETE </th>
        </tr>
        <c:forEach var="item" items="${items}">
            <tr>
                <td>
                    <c:out value="${item.itemId}" />
                </td>
                <td>
                    <c:out value="${item.name}" />
                </td>
                <td>
                    <c:out value="${item.price}" />
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/servlet/deleteItems?item_id=${item.itemId}">DELETE</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    </div>
    </body>
</html>
