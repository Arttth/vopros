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
    <title>${sessionScope.user.name()} ${sessionScope.user.lastname()}</title>
    <style>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
</head>
<body>
    <%@include file="header.jsp"%>
    <div class="container">
        <div class="user__info">
            <div class="user__name">
                ${sessionScope.user.name()} ${sessionScope.user.lastname()} (${sessionScope.user.nickname()})
            </div>
            <div class="user__birthday">
                Дата рождения: ${sessionScope.user.birthday()}
            </div>
            <div class="user__reputation">
                Репутация: ${sessionScope.user.reputation()}
            </div>
        </div>
    </div>
</body>
</html>
