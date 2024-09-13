<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <div class="left__header">
        <c:if test="${not empty sessionScope.user}">
            <div class="header__btn">
                <a class="logout__btn"   href="http://localhost:8080/question-create">Задать вопрос</a>
            </div>
        </c:if>
    </div>
    <div class="right__header">
        <c:if test="${not empty sessionScope.user}">
            <div class="header__btn">
                <form action="/logout" method="post">
                    <button class="logout__btn" type="submit">Выйти</button>
                </form>
            </div>
        </c:if>
    </div>
</header>