<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="ru_RU"/> <%-- en_US --%>
<fmt:setBundle basename="translations" var="messages"/>
<h1><c:out value="Hello, ${authUser.getLogin()}!"/></h1>

<form id="beginTest" name="beginTest" method="get" action="getQuestionnaire">
    <select id="testTheme" name="testTheme">
        <option value="1">ООП</option>
        <option value="2">Наследование</option>
        <option value="3">Коллекции</option>
    </select>
    <button type="submit" value="beginTest"><fmt:message key="HelloUser.beginTest" bundle="${messages}"/></button>
</form><br>

<h4><fmt:message key="adminHelloUser.registeredUsers" bundle="${messages}"/></h4>
<c:forEach items="${usersList}" var="user">
    <c:out value="${user}"/><br>
</c:forEach>
<h4><fmt:message key="adminHelloUser.deleteUser" bundle="${messages}"/></h4>
<form id="deleteUserForm" name="deleteUserForm" method="get" action="deleteUser">
    <fieldset>
        <input type="text" id="deleteUserLogin" name="deleteUserLogin" required align="middle">
        <label for="deleteUserLogin"><fmt:message key="index.username" bundle="${messages}"/></label><br>
        <input type="submit" value="Go" align="middle">
    </fieldset>
</form>
<div align="middle"><c:out value="${deleteUserMessage}"/></div>


<h1><fmt:message key="HelloUser.pastTests" bundle="${messages}"/></h1>
<fieldset>
<ul>
    <c:set var="x" value="1"/>
    <c:forEach items="${studentQuestionnairesList}" var="studQuest">
        <li>${studQuest.getId()} - ${sessionScope.get("themeName" += x)} - ${studQuest.getScore()}</li>
        <c:set var="x" value= "${x+1}"/>
    </c:forEach>
</ul>
</fieldset>
<form id="changeOwnData" name="changeOwnData" method="get" action="changeOwnData.jsp">
    <input type="submit" value=<fmt:message key="HelloUser.changeData" bundle="${messages}"/> align="middle">
</form>
<form id="exit" name="exit" method="get" action="exit">
    <input type="submit" value=<fmt:message key="HelloUser.exit" bundle="${messages}"/> align="middle">
</form>
