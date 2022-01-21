<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>Main</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>
<body>
<div>
    <h3>${pageContext.request.userPrincipal.name}</h3>
    <sec:authorize access="!isAuthenticated()">
        <h4><a href="/login">enter</a></h4>
        <h4><a href="/registration">register</a></h4>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <h4><a href="/logout">Log of</a></h4>
    </sec:authorize>
    <h4><a href="/reallmain">News</a></h4>
    <security:authorize access="hasRole('ADMIN')">
    <h4><a href="/admin">admin only</a></h4>
    </security:authorize>
</div>
</body>
</html>