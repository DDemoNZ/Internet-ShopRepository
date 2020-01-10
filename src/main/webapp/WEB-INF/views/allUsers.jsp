<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="users" scope="request" type="java.util.List<mate.academy.internetshop.model.User>"/>
<jsp:useBean id="greeting" scope="request" type="java.lang.String"/>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
    <title>All Users</title>
    </head>
    <body>
        <h1>Hello, ${greeting}, welcome to all users page</h1>

        <table border="1">
            <!-- here should go some titles... -->
            <tr>
                <th>Username</th>
                <th>ID</th>
                <th>FirstName</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>
                        <c:out value="${user.userName}" />
                    </td>
                    <td>
                        <c:out value="${user.userId}" />
                    </td>
                    <td>
                        <c:out value="${user.firstName}" />
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
