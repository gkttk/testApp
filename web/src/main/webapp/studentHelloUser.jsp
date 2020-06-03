<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="ru_RU"/> <%-- en_US --%>
<fmt:setBundle basename="translations" var="messages"/>

<h2 class="display-4"><c:out value="Hello, ${authUser.getLogin()}!"/></h2>


<form id="beginTest" name="beginTest" method="get" action="getQuestionnaire">
    <select id="testTheme" name="testTheme" class="custom-select" style="background-color: #adc0d0">
        <option value="1">ООП</option>
        <option value="2">Наследование</option>
        <option value="3">Коллекции</option>
    </select>
    <button class="btn btn-primary" type="submit" value="beginTest" style="margin-top: 4px">
        <fmt:message key="HelloUser.beginTest" bundle="${messages}"/></button>
</form><br>

<h2 class="display-4"><fmt:message key="studentHelloUser.helloStudent" bundle="${messages}"/></h2>

<h5 class="display-4"><fmt:message key="HelloUser.pastTests" bundle="${messages}"/></h5>

<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col">ID вопросника</th>
        <th scope="col">Название темы</th>
        <th scope="col">Процент выполнения</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="x" value="1"/>
    <c:forEach items="${studentQuestionnairesList}" var="studQuest">
    <tr>
        <th scope="row">${x}</th>
        <td>${studQuest.getId()}</td>
        <td>${sessionScope.get("themeName" += x)}</td>
        <td>${studQuest.getScore()}</td>
    </tr>
        <c:set var="x" value= "${x+1}"/>
    </c:forEach>

    </tbody>
</table>




<%--<fieldset>
<ul class="list-group">
    <c:set var="x" value="1"/>
    <c:forEach items="${studentQuestionnairesList}" var="studQuest">
        <li class="list-group-item list-group-item-dark">${studQuest.getId()} - ${sessionScope.get("themeName" += x)} - ${studQuest.getScore()}</li>
        <c:set var="x" value= "${x+1}"/>
    </c:forEach>
</ul>
</fieldset>--%>



<form id="changeOwnData" name="changeOwnData" method="get" action="changeOwnData.jsp">
    <button class="btn btn-primary" type="submit"><fmt:message key="changeOwnDate.changeData" bundle="${messages}"/></button>
</form>

<form id="exit" name="exit" method="get" action="exit" style="margin-top:3px">
    <button class="btn btn-primary" type="submit"><fmt:message key="HelloUser.exit" bundle="${messages}"/></button>
</form>

