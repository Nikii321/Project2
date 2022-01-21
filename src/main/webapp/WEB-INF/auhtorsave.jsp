<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Add author</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>
<div>
    <form:form method="POST" modelAttribute="authorSave">
        <h2>Add author</h2>
        <div>
            <form:input type="text" path="name" placeholder="name"
                        autofocus="true"/>
            <div style="color: red">
                <form:errors path="name"/>
                    ${authorSaveError}
            </div>
        </div>
        <div>
            <form:input type="text" path="surname" placeholder="surname"/>
        </div>


        <button type="submit">Add</button>
    </form:form>
    <a href="/">Main</a>
</div>
</body>
</html>