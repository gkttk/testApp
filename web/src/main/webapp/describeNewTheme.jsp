<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="ru_RU"/> <%-- en_US --%>
<fmt:setBundle basename="translations" var="messages"/>
<html>

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <title>Описать новую тему</title>
</head>
<body style="background-color: #adc0d0">

<h2 class="display-4">Описание новой темы</h2>

<form method="get" action="addNewTheme">
    <input type="hidden" name="newTempThemeName" value="${newTempUserTheme.getThemeName()}">
    <fieldset>
        <h2 class="display-6">${newTempUserTheme.getThemeName()}</h2>
        <ol>
            <c:forEach begin="1" end="${newTempUserTheme.getNumberOfQuestions()}" var="question" varStatus="loop">
                <li><div class="form-group">
                    <input type="text" id="questionText" name="questionText" style="width: 600px">
                    <label for="questionText" class="text-dark">Текст вопроса</label><br>
                    <ol>
                        <c:set var="x" value="1"/>
                        <li><input type="text" name="answer${loop.count}"  style="width: 300px">
                            <label class="text-dark">Текст ответа</label>
                            <input type="checkbox" name="answerCorrectness${loop.count}_${x}" value="true"><br>
                        </li>
                        <c:set var="x" value= "${x+1}"/>
                        <li><input type="text" name="answer${loop.count}"  style="width: 300px">
                            <label class="text-dark">Текст ответа</label>
                        <input type="checkbox" name="answerCorrectness${loop.count}_${x}" value="true"><br>
                        </li>
                        <c:set var="x" value= "${x+1}"/>
                        <li><input type="text" name="answer${loop.count}"  style="width: 300px">
                            <label class="text-dark">Текст ответа</label>
                            <input type="checkbox" name="answerCorrectness${loop.count}_${x}" value="true"><br>
                        </li>
                        <c:set var="x" value= "${x+1}"/>
                        <li><input type="text" name="answer${loop.count}"  style="width: 300px">
                            <label class="text-dark">Текст ответа</label>
                            <input type="checkbox" name="answerCorrectness${loop.count}_${x}" value="true"><br>
                        </li>
                        <c:set var="x" value= "${x+1}"/>
                        <li>
                            <input type="text" name="answer${loop.count}"  style="width: 300px">
                            <label class="text-dark">Текст ответа</label>
                            <input type="checkbox" name="answerCorrectness${loop.count}_${x}" value="true"><br>
                        </li>
                    </ol>
                </div>
                </li>
            </c:forEach>
        </ol>
        <button type="submit" class="btn btn-primary">Добавить тему</button>
    </fieldset>
</form>





</body>
</html>