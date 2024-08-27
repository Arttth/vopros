<%--
  Created by IntelliJ IDEA.
  User: tumer1
  Date: 27.08.2024
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${sessionScope.user.name()}</title>
</head>
<body>
    <%@include file="header.jsp"%>
    Имя: ${sessionScope.user.name()}
    Фамилия: ${sessionScope.user.lastname()}
    <form method="post" action="/question">
        <input type="text" name="question_text">
        <input type="text" name="question_expl">
        <button type="submit">Опубликовать</button>
    </form>
</body>
</html>
