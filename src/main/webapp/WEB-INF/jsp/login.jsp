<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Войти</title>
    <style>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
</head>
<body>
    <form method="post" action="/login" class="form">
        <label for="email"> Почта:
            <input type="email" id="email" name="email">
        </label><br/>
        <label for="password"> Пароль:
            <input type="password" id="password" name="password">
        </label><br/>
        <button type="submit">Войти</button>
    </form>

    <c:if test="${param.error != null}">
        <div style="color: red" class="error">
            <span>Email or password are incorrect!</span>
        </div>
    </c:if>
</body>
</html>
