<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="bucket" scope="request" type="mate.academy.internetshop.model.Bucket"/>

<html>
<head>
    <title>Bucket</title>
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
    <h1 align="center"><b class="title">Items in bucket</b></h1>
    <div class="tables">
<table border="1">
    <tr>
        <th> ID </th>
        <th> Name </th>
        <th> Price </th>
        <th> Delete </th>
    </tr>
    <c:forEach var="item" items="${bucket.items}" >
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
                <a href="${pageContext.request.contextPath}/servlet/deleteItemFromBucket?item_id=${item.itemId}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
<button class="complete" onclick="location.href='${pageContext.request.contextPath}/servlet/completeOrder'" type="button">Complete order</button>
</div>
</body>
</html>
