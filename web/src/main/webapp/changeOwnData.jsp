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
    <title><fmt:message key="changeOwnDate.changeData" bundle="${messages}"/></title>
</head>

<tr>
    <td align="left" valign="top">
        <h4><fmt:message key="changeOwnDate.NewData" bundle="${messages}"/></h4>
        <form id="changeData" name="changeData" method="get" action="changeOwnData">
            <fieldset>
                <input type="text" id="newPassword" name="newPassword">
                <label for="newPassword"><fmt:message key="changeOwnDate.newPassword" bundle="${messages}"/></label><br>
                <input type="text" id="newEmail" name="newEmail">
                <label for="newEmail"><fmt:message key="changeOwnDate.newEmail" bundle="${messages}"/></label><br>

                <input type="text" id="newName" name="newName">
                <label for="newName"><fmt:message key="changeOwnData.newName" bundle="${messages}"/></label><br>
                <input type="text" id="newSurname" name="newSurname">
                <label for="newSurname"><fmt:message key="changeOwnData.newSurname" bundle="${messages}"/></label><br>
                <input type="text" id="newAge" name="newAge">
                <label for="newAge"><fmt:message key="changeOwnData.newAge" bundle="${messages}"/></label><br>


                <input type="submit" value=<fmt:message key="changeOwnDate.change" bundle="${messages}"/> >



            </fieldset>
        </form>

        <button onclick='history.back()'><fmt:message key="teacherPage.backPagination" bundle="${messages}"/></button>





</body>
</html>