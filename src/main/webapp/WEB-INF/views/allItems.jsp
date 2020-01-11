<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="items" scope="request" type="java.util.List"/>
<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 11.01.2020
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AllItems</title>
</head>
<body>
    AllItems

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
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
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
</body>
</html>
