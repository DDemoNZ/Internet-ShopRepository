<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="items" scope="request" type="java.util.List"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>InternetShop</title>
</head>
<body>
    <h1>Welcome, to my internet shop</h1>

    <table border="1">
        <tr>
            <th colspan="4">Items List</th>
        </tr>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
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
                    <a href="${pageContext.request.contextPath}/servlet/addItemsToBucket?item_id=${item.itemId}&user_id=1">ADD</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="2">
                <a href="${pageContext.request.contextPath}/getBucket?user_id=1">Back to your bucket</a>
            </td>
            <td colspan="2">
                <a href="${pageContext.request.contextPath}/index">Return to main page</a>
            </td>
        </tr>
    </table>

</body>
</html>
