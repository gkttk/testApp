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
    <title><fmt:message key="testResultPage.result" bundle="${messages}"/></title>
</head>
<body style="background-color: #adc0d0">

<h2 class="display-4">Пользователь ${sessionScope.get("authUser").getLogin()} выполнил тест на ${sessionScope.get("result")} процентов</h2>
<h2 class="display-4">Дата прохождения теста - ${sessionScope.get("date")}</h2>


<form id="backToContent" name="backToContent" method="get" action="index.jsp">
    <button class="btn btn-primary" type="submit" form="backToContent">
        <fmt:message key="testResultPage.back" bundle="${messages}"/>
    </button>
</form>






</body>
</html>