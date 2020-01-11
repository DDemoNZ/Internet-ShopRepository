<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="bucket" scope="request" type="mate.academy.internetshop.model.Bucket"/>

<html>
<head>
    <title>Bucket</title>
</head>
<body>
    <h1>Items in bucket</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Delete</th>
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
                <a href="${pageContext.request.contextPath}/servlet/deleteItemFromBucket?bucket_id=${bucket.bucketId}&item_id=${item.itemId}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td>
            <a href="${pageContext.request.contextPath}/servlet/completeOrder?bucket_id=${bucket.bucketId}">Complete order</a>
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/internetShop">Back to item list page</a>
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/index">Back to main page</a>
        </td>
        <td>
            <b>*</b>
        </td>
    </tr>
</table>
</body>
</html>
