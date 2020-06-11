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
    <title>Добавить новую тему</title>
</head>
<body style="background-color: #adc0d0">

<h2 class="display-4">Добавить новую тему</h2>

<form method="get" action="addThemeForPermit">
    <fieldset>
        <div class="form-group">
            <input type="text" id="themeName" name="themeName" required>
            <label for="themeName" class="text-dark">Название темы</label>
        </div>
        <div class="form-group">
            <input type="text" id="numberOfQuestions" name="numberOfQuestions" pattern="[0-9]+" title="please enter number only" required>
            <label for="numberOfQuestions" class="text-dark">Количество вопросов</label>
        </div>
        <button type="submit" class="btn btn-primary" style="margin-top: 4px">Отправить на согласование</button>
    </fieldset>
</form>






</body>
</html>