<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="items" scope="request" type="java.util.List"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AllItems</title>
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
    <h1 align="center"><b class="title">All items page</b></h1>
    <div class="tables">
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
                <button class="addItem" type="submit">ADD</button>
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
