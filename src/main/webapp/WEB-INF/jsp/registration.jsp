<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
    <form method="post" action="/registration">
        <label for="name">Имя:
            <input type="text" id="name" name="name">
        </label><br/>
        <label for="lastname">Фамилия:
            <input type="text" id="lastname" name="lastname">
        </label><br/>
        <label for="nickname">Логин:
            <input type="text" id="nickname" name="nickname">
        </label><br/>
        <label for="birthday">Дата рождения:
            <input type="date" id="birthday" name="birthday">
        </label><br/>
        <label for="email">Почта:
            <input type="email" id="email" name="email">
        </label><br/>
        <label for="password">Пароль:
            <input type="password" id="password" name="password">
        </label><br/>
        <button type="submit">Отправить</button>
    </form>
    <c:forEach var="error" items="${requestScope.errors}">
        <div class="error">
            ${error.message}
        </div>
    </c:forEach>
</body>
</html>
