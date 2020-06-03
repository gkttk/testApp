<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="ru_RU"/> <%-- en_US --%>
<fmt:setBundle basename="translations" var="messages"/>
<html>
<body style="background-color: #adc0d0">
<head>

    <meta charset="UTF-8">
    <title><fmt:message key="index.registration" bundle="${messages}"/></title>

    <!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>

<td align="left" valign="top">
    <h1 class="display-4"><fmt:message key="index.registration" bundle="${messages}"/></h1>

    <%--<tr>
        <td align="left" valign="top">
            <h4><fmt:message key="index.registration" bundle="${messages}"/></h4>--%>

    <form method="get" action="validationLoginReg">
        <fieldset>
            <div class="form-group" style="margin: 0px">
                <input type="text" id="login" name="login" required>
                <label for="login"><fmt:message key="index.username" bundle="${messages}"/></label>
            </div>
            <div>
                <input type="text" id="password" name="password" required>
                <label for="password"><fmt:message key="index.password" bundle="${messages}"/></label>
            </div>
            <div>
                <input type="text" id="email" name="email" required>
                <label for="email"><fmt:message key="registration.Email" bundle="${messages}"/></label>
            </div>
            <small id="help" class="form-text text-muted" style="margin: 0px"><fmt:message key="registrationPage.notNecessary" bundle="${messages}"/></small>
            <div>
                <input type="text" id="name" name="name">
                <label for="name"><fmt:message key="registrationPage.name" bundle="${messages}"/></label>
            </div>
            <div>
                <input type="text" id="surname" name="surname">
                <label for="surname"><fmt:message key="registrationPage.surname" bundle="${messages}"/></label>
            </div>
            <div>
                <input type="text" id="age" name="age">
                <label for="age"><fmt:message key="registrationPage.age" bundle="${messages}"/></label>
            </div>
            <button type="submit" class="btn btn-primary"><fmt:message key="index.registration" bundle="${messages}"/></button>
        </fieldset>
    </form>

    <button class="btn btn-primary" onclick='history.back()'><fmt:message key="teacherPage.backPagination" bundle="${messages}"/></button>

    <c:if test="${requestScope.get('UserExistsMessage') != null}">
    <div class="alert alert-danger" role="alert">${requestScope.get("UserExistsMessage")}</div>
    </c:if>

   <%-- <form id="registration" name="registration" method="get" action="validationLoginReg">
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

            <input type="submit" value=<fmt:message key="index.registration" bundle="${messages}"/>>
        </fieldset>
    </form>--%>

  <%--  <p>${requestScope.get("UserExistsMessage")}</p>--%>

    <%-- <form id="exit" name="exit" method="get" action="index.jsp">
         <input type="submit" value=<fmt:message key="teacherPage.backPagination" bundle="${messages}"/> align="middle">
     </form> --%>

</body>
</html>