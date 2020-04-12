<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="ru_RU"/> <%-- en_US --%>
<fmt:setBundle basename="translations" var="messages"/>

<html>
<body>
<c:if test="${authUser != null}">
<c:redirect url="facade"/>
</c:if>
<head>
    <title><fmt:message key="index.enter" bundle="${messages}"/></title>
</head>

<tr>
    <td align="left" valign="top">
        <h4><fmt:message key="index.enter" bundle="${messages}"/></h4>
        <form id="form" name="form" method="get" action="checkLoginAuth">
            <fieldset>
                <input type="text" id="login" name="login" required>
                <label for="login"><fmt:message key="index.username" bundle="${messages}"/></label><br>
                <input type="text" id="password" name="password" required>
                <label for="password"><fmt:message key="index.password" bundle="${messages}"/></label><br>
                <input type="submit" value=<fmt:message key="index.submit" bundle="${messages}"/>>
            </fieldset>
        </form>



        <form id="registration" name="registration" method="get" action="registration.jsp">
            <fieldset>
            <button type="submit" value=<fmt:message key="index.registration" bundle="${messages}"/>><fmt:message key="index.registration" bundle="${messages}"/></button>
            </fieldset>
        </form><br>







        <p>${requestScope.get("UserNotExistsMessage")}</p>
        <p>${requestScope.get("UserWrongPasswordMessage")}</p>







</body>
</html>