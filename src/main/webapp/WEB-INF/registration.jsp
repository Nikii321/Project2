<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Register</title>
</head>

<body>
<div>
    <form:form method="POST" modelAttribute="userForm">
        <h2>Register</h2>
        <div>
            <form:input type="text" path="username" placeholder="Username"
                        autofocus="true"/>
            <form:errors path="username"/>
                ${usernameError}
        </div>
        <div>
            <form:input type="password" path="password" placeholder="Password"/>
        </div>
        <div>
            <form:input type="password" path="passwordConfirm"
                        placeholder="Confirm your password"/>
            <form:errors path="password"/>
                ${passwordError}
        </div>
        <button type="submit">Register now</button>
    </form:form>
    <a href="/">Main</a>
</div>
</body>
</html>