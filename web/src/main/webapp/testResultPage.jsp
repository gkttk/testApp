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
    <title><fmt:message key="testResultPage.result" bundle="${messages}"/></title>
</head>
<body>

<h1>Пользователь ${sessionScope.get("authUser").getLogin()} выполнил тест на ${sessionScope.get("result")} процентов</h1>
<h1>Дата прохождения теста - ${sessionScope.get("date")}</h1>


<form id="backToContent" name="backToContent" method="get" action="index.jsp">
    <input type="submit" value=<fmt:message key="testResultPage.back" bundle="${messages}"/> form="backToContent">
</form>






</body>
</html>