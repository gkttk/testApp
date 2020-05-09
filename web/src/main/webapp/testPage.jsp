<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="ru_RU"/> <%-- en_US --%>
<fmt:setBundle basename="translations" var="messages"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="testPage.title" bundle="${messages}"/></title>
</head>
<body>


<form id="testForm" name="testForm" method="get" action="checkTest">
    <fieldset>
        <c:set var="x" scope="page" value="1" />
<c:forEach items="${questionnaire.getQuestionnaireQuestions()}" var="question">
   <h2> <c:out value="${question.getQuestionText()}"/></h2>

    <c:forEach items="${question.getAnswers()}" var="answer">
    <ol>
        <li>${answer.getAnswerText()} <input type="checkbox" id="answer" name="question_${x}[]" value="${answer.getId()}" form="testForm"></li>
    </ol>

    </c:forEach>
    <c:set var="x" scope="page" value="${x+1}" />
</c:forEach>
    </fieldset>
    <input type="submit" value=<fmt:message key="testPage.finish" bundle="${messages}"/> form="testForm">
</form>





</body>
</html>