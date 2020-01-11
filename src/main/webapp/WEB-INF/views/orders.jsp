<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="orders" scope="request" type="java.util.List"/>
<jsp:useBean id="items" scope="request" type="mate.academy.internetshop.model.Order"/>
<jsp:useBean id="item" scope="request" type="mate.academy.internetshop.model.Item"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Items</th>
        <th>Price</th>
        <th>DELETE</th>
    </tr>
    <c:forEach items="${orders}" var="order">
    <tr>
        <td>
            <c:out value="${order.orderId}" />
        </td>
        <td>
            <table border="1">
                <tr>
                    <th>
                        Name
                    </th>
                    <th>
                        Price
                    </th>
                </tr>
            <c:forEach var="order" items="${items}" >
                <tr>
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
        <td>
            <a href="${pageContext.request.contextPath}servlet/deleteUsersOrder?order_id=${order.orderId}">DELETE</a>
        </td>
    </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/internetShop">Back to items page</a>
<a href="${pageContext.request.contextPath}/index">Back to main page</a>
</body>
</html>
