<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="ru_RU"/> <%-- en_US --%>
<fmt:setBundle basename="translations" var="messages"/>
<html>
<body>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="index.registration" bundle="${messages}"/></title>
</head>

<tr>
    <td align="left" valign="top">
        <h4><fmt:message key="index.registration" bundle="${messages}"/></h4>
        <form id="registration" name="registration" method="get" action="validationLoginReg">
            <fieldset>
                <input type="text" id="login" name="login" required>
                <label for="login"><fmt:message key="index.username" bundle="${messages}"/></label><br>
                <input type="text" id="password" name="password" required>
                <label for="password"><fmt:message key="index.password" bundle="${messages}"/></label><br>
                <input type="text" id="email" name="email" required>
                <label for="email"><fmt:message key="registration.Email" bundle="${messages}"/></label><br>

                <fmt:message key="registrationPage.notNecessary" bundle="${messages}"/><br>
                <input type="text" id="name" name="name">
                <label for="name"><fmt:message key="registrationPage.name" bundle="${messages}"/></label><br>
                <input type="text" id="surname" name="surname">
                <label for="surname"><fmt:message key="registrationPage.surname" bundle="${messages}"/></label><br>
                <input type="text" id="age" name="age">
                <label for="age"><fmt:message key="registrationPage.age" bundle="${messages}"/></label><br>

                <input type="submit" value=<fmt:message key="index.registration" bundle="${messages}"/> >
            </fieldset>
        </form>

        <p>${requestScope.get("UserExistsMessage")}</p>

       <%-- <form id="exit" name="exit" method="get" action="index.jsp">
            <input type="submit" value=<fmt:message key="teacherPage.backPagination" bundle="${messages}"/> align="middle">
        </form> --%>
        <button onclick='history.back()'><fmt:message key="teacherPage.backPagination" bundle="${messages}"/></button>
</body>
</html>