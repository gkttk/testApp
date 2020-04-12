<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<h1><c:out value="Hello, ${authUser.getLogin()}!"/></h1>

<fmt:setLocale value="ru_RU"/> <%-- en_US --%>
<fmt:setBundle basename="translations" var="messages"/>

<form id="beginTest" name="beginTest" method="get" action="getQuestionnaire">
    <select id="testTheme" name="testTheme">
        <option value="1">ООП</option>
        <option value="2">Наследование</option>
        <option value="3">Коллекции</option>
    </select>
    <button type="submit" value="beginTest"><fmt:message key="HelloUser.beginTest" bundle="${messages}"/></button>
</form><br>

<h1><fmt:message key="teacherHelloUser.helloTeacher" bundle="${messages}"/></h1>

<h4><fmt:message key="teacherHelloUser.studentsResult" bundle="${messages}"/></h4>
<c:forEach items="${infoForTeacher}" var="info">
    <li>${info.toString()}</li>
</c:forEach>
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
    <input type="submit" value=<fmt:message key="changeOwnDate.changeData" bundle="${messages}"/> align="middle">
</form>
<form id="exit" name="exit" method="get" action="exit">
    <input type="submit" value=<fmt:message key="HelloUser.exit" bundle="${messages}"/> align="middle">
</form>
