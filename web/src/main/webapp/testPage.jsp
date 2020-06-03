<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="ru_RU"/> <%-- en_US --%>
<fmt:setBundle basename="translations" var="messages"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <meta charset="UTF-8">
    <title><fmt:message key="testPage.title" bundle="${messages}"/></title>
</head>
<body style="background-color: #adc0d0">


<form id="testForm" name="testForm" method="get" action="checkTest">
    <fieldset>
        <c:set var="x" scope="page" value="1" />
<c:forEach items="${questionnaire.getQuestionnaireQuestions()}" var="question">
    <h2 class="display-4"> <c:out value="${question.getQuestionText()}"/></h2>
    <ol>
    <c:forEach items="${question.getAnswers()}" var="answer">

        <li>${answer.getAnswerText()} <input type="checkbox" id="answer" name="question_${x}[]" value="${answer.getId()}" form="testForm"></li>


    </c:forEach>
    </ol>
    <c:set var="x" scope="page" value="${x+1}" />
</c:forEach>
    </fieldset>
    <button class="btn btn-primary" type="submit" form="testForm">
        <fmt:message key="testPage.finish" bundle="${messages}"/>
    </button>

</form>





</body>
</html>