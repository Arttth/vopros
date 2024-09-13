<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вопросы</title>
</head>
<body>
    <c:forEach var="question" items="requestScope.questions">
        <div class="question__container">
            <div class="question__info">
                <div class="question__name">
                    ${question.questionName()}
                </div>
                <div class="question__main">
                        ${question.questionMainPart()}
                </div>
                <div class="question__like">
                        ${question.questionLikeCount()}
                </div>
            </div>
            <div class="author__info">
                <div class="user__nickname">
                        ${question.userNickname()}
                </div>
            </div>
            <div class="discipline__info">
                <div class="discipline__name">
                        ${question.disciplineName()}
                </div>
            </div>
        </div>
    </c:forEach>
</body>
</html>
