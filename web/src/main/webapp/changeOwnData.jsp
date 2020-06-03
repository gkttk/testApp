<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="ru_RU"/> <%-- en_US --%>
<fmt:setBundle basename="translations" var="messages"/>
<html>

<head>
    <!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <meta charset="UTF-8">
    <title><fmt:message key="changeOwnDate.changeData" bundle="${messages}"/></title>
</head>
<body style="background-color: #adc0d0">
<tr>
    <td align="left" valign="top">
        <h2 class="display-4"><fmt:message key="changeOwnDate.NewData" bundle="${messages}"/></h2>
        <form id="changeData" name="changeData" method="get" action="changeOwnData">
            <fieldset>
                <div class="form-group">
                    <input type="text" id="newPassword" name="newPassword">
                    <label for="newPassword" class="text-dark"><fmt:message key="changeOwnDate.newPassword" bundle="${messages}"/></label>
                </div>
                <div class="form-group">
                    <input type="text" id="newEmail" name="newEmail">
                    <label for="newEmail"><fmt:message key="changeOwnDate.newEmail" bundle="${messages}"/></label>
                </div>
                <div class="form-group">
                    <input type="text" id="newName" name="newName">
                    <label for="newName"><fmt:message key="changeOwnData.newName" bundle="${messages}"/></label>
                </div>
                <div class="form-group">
                    <input type="text" id="newSurname" name="newSurname">
                    <label for="newSurname"><fmt:message key="changeOwnData.newSurname" bundle="${messages}"/></label>
                </div>
                <div class="form-group">
                    <input type="text" id="newAge" name="newAge">
                    <label for="newAge"><fmt:message key="changeOwnData.newAge" bundle="${messages}"/></label><br>
                </div>





                <button class="btn btn-primary" type="submit">
                    <fmt:message key="changeOwnDate.change" bundle="${messages}"/></button>

            </fieldset>
        </form>

        <button class="btn btn-primary" onclick='history.back()'><fmt:message key="teacherPage.backPagination" bundle="${messages}"/></button>





</body>
</html>