<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <c:if test="${not empty sessionScope.user}">
        <form action="/logout" method="post">
            <button type="submit">Выйти</button>
        </form>
    </c:if>
</header>