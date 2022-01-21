<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Pass grade</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>
<div>
    <form:form method="POST" modelAttribute="GradePerson">
        <div>
            <form:input type="text" path="grade" placeholder="grade"
                        autofocus="true"/>
            <button type="submit">Sumbit</button>

            <form:errors  path="grade"/>
            <div style="color: red">
                ${GradeNegativeError}
                ${GradeToMuchError}
            </div>
        </div>

    </form:form>
</div>
</body>
</html>