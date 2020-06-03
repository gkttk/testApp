<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="ru_RU"/> <%-- en_US --%>
<fmt:setBundle basename="translations" var="messages"/>
<html>

<c:if test="${authUser != null}">
    <c:redirect url="facade"/>
</c:if>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <title><fmt:message key="index.enter" bundle="${messages}"/></title>
</head>
<body style="background-color: #adc0d0">

    <td align="left" valign="top">
        <h2 class="display-4"><fmt:message key="index.enter" bundle="${messages}"/></h2>

        <form method="get" action="checkLoginAuth">
            <fieldset>
                <div class="form-group">
                    <input type="text" id="login" name="login" required>
                    <label for="login" class="text-dark"><fmt:message key="index.username" bundle="${messages}"/></label>
                </div>
                <div class="form-group">
                    <input type="text" id="password" name="password" required>
                    <label for="password" class="text-dark"><fmt:message key="index.password" bundle="${messages}"/></label>
                </div>
                <button type="submit" class="btn btn-primary"><fmt:message key="index.submit" bundle="${messages}"/></button>
            </fieldset>
        </form>
        <form id="registration" name="registration" method="get" action="registration.jsp">
            <button type="submit" class="btn btn-primary"><fmt:message key="index.registration" bundle="${messages}"/></button>
        </form>


        <c:if test="${requestScope.get('UserNotExistsMessage') != null}">
            <div class="alert alert-danger" role="alert">${requestScope.get("UserNotExistsMessage")}</div>
            </c:if>

            <c:if test="${requestScope.get('UserWrongPasswordMessage') != null}" >
            <div class="alert alert-danger" role="alert">${requestScope.get("UserWrongPasswordMessage")}</div>
            </c:if>

        <%-- <p>${requestScope.get("UserNotExistsMessage")}</p>
         <p>${requestScope.get("UserWrongPasswordMessage")}</p>--%>


        <%--<form id="form" name="form" method="get" action="checkLoginAuth">
            <fieldset>
                <input type="text" id="login" name="login" required>
                <label for="login"><fmt:message key="index.username" bundle="${messages}"/></label><br>
                <input type="text" id="password" name="password" required>
                <label for="password"><fmt:message key="index.password" bundle="${messages}"/></label><br>
                <input type="submit" value=<fmt:message key="index.submit" bundle="${messages}"/>>
            </fieldset>
        </form>--%>

        <%--
                <form id="registration" name="registration" method="get" action="registration.jsp">
                    <fieldset>
                        <button type="submit" value=<fmt:message key="index.registration" bundle="${messages}"/>><fmt:message
                                key="index.registration" bundle="${messages}"/></button>
                    </fieldset>
                </form>
                <br>--%>


        <%--     <p>${requestScope.get("UserNotExistsMessage")}</p>
             <p>${requestScope.get("UserWrongPasswordMessage")}</p>--%>


</body>
</html>