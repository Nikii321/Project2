<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Authors</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>
<body>
<div>
    <h2>Authors <br> For logged in users only.</h2>
    <a href="/">Main</a>
</div>
<div>
    <table>
        <thead>
        <th>Name</th>
        <th>Surname</th>
        <th>Rating</th>
        </thead>
        <c:forEach items="${allAuthors}" var="author">
            <tr>
                <td>${author.name}</td>
                <td>${author.surname}</td>
                <td>${author.rating}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/main" method="post">
                        <input type="hidden" name="id" value="${author.id}"/>
                        <input type="hidden" name="action" value="showBook"/>
                        <button type="submit">Show book</button>
                    </form>

                </td>

            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
