<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Books</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>
<body>
<div>
    <h2>Books <br> For logged in users only.</h2>
    <a href="/">Main</a>
</div>
<div>
    <table>
        <thead>
        <th>Name</th>
        <th>Author</th>
        <th>Rating</th>
        </thead>
        <c:forEach items="${allAdminAuthor}" var="author">
            <tr>
                <td>${author.id}</td>
                <td>${author.name}</td>
                <td>${author.surname}</td>
                <td>${author.rating}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/author" method="post">
                        <input type="hidden" name="AuthorID" value="${author.id}"/>
                        <input type="hidden" name="action" value="delete"/>
                        <button type="submit">Delete</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/admin/book/save/${author.id}" method="get">
                        <button type="submit">Add Book</button>
                    </form>

                </td>

            </tr>
        </c:forEach>
    </table>
    <form action="${pageContext.request.contextPath}/admin/author/save" method="get">
        <button type="submit">Add</button>
    </form>
</div>
</body>
</html>
