<%--
  Created by IntelliJ IDEA.
  User: tumer1
  Date: 13.09.2024
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вопрос</title>
</head>
<body>
    <h1>Новый вопрос</h1>
    <form action="/question" method="post">
        <label for="name">Название:
            <input type="text" id="name" value="name">
        </label> <br/>
        <label for="main">Вопрос:
            <input type="text" id="main" value="main">
        </label> <br/>
        <label for="discipline">Вопрос:
            <input type="text" id="discipline" value="discipline">
        </label> <br/>
        <button type="submit">Создать</button>
    </form>
</body>
</html>
