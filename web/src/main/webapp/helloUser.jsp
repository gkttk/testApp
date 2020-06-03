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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

    <meta charset="UTF-8">
    <title><fmt:message key="HelloUser.title" bundle="${messages}"/></title>
</head>
<body style="background-color: #adc0d0">
<c:choose>
    <c:when test="${sessionScope.get('authUser').getRole().name() == 'STUDENT'}">
        <c:import url="studentHelloUser.jsp"/>
    </c:when>
    <c:when test="${sessionScope.get('authUser').getRole().name() == 'TEACHER'}">
        <c:import url="teacherHelloUser.jsp"/>
    </c:when>
    <c:otherwise>
        <c:import url="adminHelloUser.jsp"/>
    </c:otherwise>
</c:choose>

</body>
</html>