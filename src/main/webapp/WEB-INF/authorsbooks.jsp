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
    <h2>Authors Book <br> For logged in users only.</h2>
    <a href="/">Main</a>
</div>
<div>
    <table>
        <thead>
        <th>Name</th>
        <th>Author</th>
        <th>Rating</th>
        </thead>
        <c:forEach items="${allAuthorBook}" var="book">
            <tr>
                <td>${book.name }</td>
                <td>${book.name_and_surname_author}</td>
                <td>${book.rating}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/grade/${book.id}" method="get">
                        <button type="submit">Pass grade</button>
                    </form>

                </td>


            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
