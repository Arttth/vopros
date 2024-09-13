<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div class="user__photo__container">
            <c:if test="${not empty sessionScope.user.profilePhoto()}">
                <img class="user__photo" src="${sessionScope.user.profilePhoto()}"/>
            </c:if>
            <c:if test="${empty sessionScope.user.profilePhoto()}">
                <img class="user__photo" src="/images/default.png"/>
            </c:if>
<%--            <form enctype='multipart/form-data' method="post" action="/user/photo-upload">--%>
<%--                <input type="file" class="photo__upload" name="photo"/>--%>
<%--                <button type="submit">Загрузить</button>--%>
<%--            </form>--%>
        </div>
    </div>
</body>
</html>
