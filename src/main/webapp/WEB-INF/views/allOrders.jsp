<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="orders" scope="request" type="java.util.List<mate.academy.internetshop.model.Order>"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Orders</title>
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
    </style>
</head>
<body>
<h1 align="center">All orders page</h1>
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
<table border="1">
    <tr>
        <th> OrderID </th>
        <th> UserID </th>
        <th> Items in order </th>
        <th> Price </th>
        <th> DELETE </th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.orderId}" />
            </td>
            <td>
                <c:out value="${order.userId}" />
            </td>
            <td>
                <table>
                    <tr>
                        <th> ID </th>
                        <th> Name </th>
                        <th> Price </th>
                    </tr>
                    <c:forEach var="item" items="${order.items}">
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
                        </tr>
                    </c:forEach>
                </table>
            </td>
            <td><c:out value="${order.allPrice}" /></td>
            <td>
                <a href="${pageContext.request.contextPath}/servlet/deleteOrder?order_id=${order.orderId}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
