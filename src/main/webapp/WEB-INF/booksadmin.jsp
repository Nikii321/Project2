<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>
<div>
    <table>
        <thead>
        <th>ID</th>
        <th>Name</th>
        <th>Author</th>
        <th>Rating</th>
        </thead>
        <c:forEach items="${allAdminBooks}" var="book">
            <tr>
                <td>${book.id}</td>
                <td>${book.name}</td>
                <td>${book.name_and_surname_author}</td>
                <td>${book.rating}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/books" method="post">
                        <input type="hidden" name="bookId" value="${book.id}"/>
                        <input type="hidden" name="action" value="delete"/>
                        <button type="submit">Delete</button>
                    </form>

                </td>

            </tr>
        </c:forEach>
    </table>
    <a href="/">Main</a>
</div>
</body>
</html>