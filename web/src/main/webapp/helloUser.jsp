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
    <title><fmt:message key="HelloUser.title" bundle="${messages}"/></title>
</head>
<body>
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