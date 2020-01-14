<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="items" scope="request" type="java.util.List"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>InternetShop</title>
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

    </style>
</head>
<body>
    <h1 align="center">Welcome, to my internet shop</h1>
    <ul class="menu">
        <li><a href="${pageContext.request.contextPath}/servlet/index">Main page</a></li>
        <li><a href="${pageContext.request.contextPath}/registration">Registration</a></li>
        <li><a href="${pageContext.request.contextPath}/servlet/internetShop">Items list</a></li>
        <li><a href="${pageContext.request.contextPath}/servlet/getBucket">Check your bucket</a></li>
        <li><a href="${pageContext.request.contextPath}/servlet/orders">Check your orders</a></li>
        <li><a href="${pageContext.request.contextPath}/servlet/getAllItems"> Add items to storage</a></li>
        <li><a href="${pageContext.request.contextPath}/servlet/getAllUsers">List of users</a></li>
        <li><a href="${pageContext.request.contextPath}/servlet/getAllOrders"> List of orders </a></li>
    </ul>
    <div style="margin-left:25%;padding:1px 16px;height:1000px;">
    <table border="1">
        <tr>
            <th colspan="4">Items List</th>
        </tr>
        <tr>
            <th> ID </th>
            <th> Name </th>
            <th> Price </th>
            <th> ADD </th>
        </tr>
        <c:forEach var="item" items="${items}" >
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
                    <a href="${pageContext.request.contextPath}/servlet/addItemsToBucket?item_id=${item.itemId}">ADD</a>
                </td>
            </tr>
        </c:forEach>
    </table>
        <button onclick="location.href='${pageContext.request.contextPath}/servlet/getBucket'" type="button">Back to your bucket</button>
    </div>
</body>
</html>
