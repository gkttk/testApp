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
</form>
<br>

<h2 class="display-4"><fmt:message key="teacherHelloUser.helloTeacher" bundle="${messages}"/></h2>

<h5 class="display-4"><fmt:message key="teacherHelloUser.studentsResult" bundle="${messages}"/></h5>


<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col">Логин студента</th>
        <th scope="col">E-mail студента</th>
        <th scope="col">Тема вопросника</th>
        <th scope="col">Процент выполнения</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="x" value="1"/>
    <c:forEach items="${infoForTeacher}" var="info">
        <tr>
            <th scope="row">${x}</th>
            <td>${info.getUserLogin()}</td>
            <td>${info.getUserEmail()}</td>
            <td>${info.getThemeName()}</td>
            <td>${info.getScore()}</td>
        </tr>
        <c:set var="x" value="${x+1}"/>
    </c:forEach>
    </tbody>
</table>


<%--<c:forEach items="${infoForTeacher}" var="info">
<li>${info.toString()}</li>
</c:forEach>--%>


<nav aria-label="Page navigation example">
    <ul class="pagination">
        <li class="page-item">
            <c:if test="${currentPage != 1}">
                <a class="page-link" href="getResultForTeacher?currentPage=${currentPage - 1}"
                   aria-label="<fmt:message key="teacherPage.backPagination" bundle="${messages}"/>">
                    <span aria-hidden="false"><fmt:message key="teacherPage.backPagination"
                                                           bundle="${messages}"/></span>
                </a>
            </c:if>
        </li>
        <c:forEach begin="1" end="${pagesCount}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li class="page-item"><a class="page-link" href="#">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="getResultForTeacher?currentPage=${i}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <li class="page-item">
            <c:if test="${currentPage lt pagesCount}">
                <a class="page-link" href="getResultForTeacher?currentPage=${currentPage + 1}"
                   aria-label="<fmt:message key="teacherPage.forwardPagination" bundle="${messages}"/>">
                    <span aria-hidden="false"><fmt:message key="teacherPage.forwardPagination"
                                                           bundle="${messages}"/></span>
                </a>
            </c:if>
        </li>
    </ul>
</nav>


<%--<c:if test="${currentPage != 1}">
    <td><a href="getResultForTeacher?currentPage=${currentPage - 1}"><fmt:message key="teacherPage.backPagination" bundle="${messages}"/></a></td>
</c:if>

<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${pagesCount}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="getResultForTeacher?currentPage=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

<c:if test="${currentPage lt pagesCount}">
    <td><a href="getResultForTeacher?currentPage=${currentPage + 1}"><fmt:message key="teacherPage.forwardPagination" bundle="${messages}"/></a></td>
</c:if>--%>


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
        <c:set var="x" value="${x+1}"/>
    </c:forEach>
    </tbody>
</table>


<%--<fieldset>
    <ul>
        <c:set var="x" value="1"/>
        <c:forEach items="${studentQuestionnairesList}" var="studQuest">
            <li>${studQuest.getId()} - ${sessionScope.get("themeName" += x)} - ${studQuest.getScore()}</li>
            <c:set var="x" value="${x+1}"/>
        </c:forEach>
    </ul>
</fieldset>--%>

<c:if test="${tempUserNewThemes.size() > 0}">
    <h5 class="display-4">Новые темы на согласовании</h5>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Название темы</th>
            <th scope="col">Количество вопросов</th>
            <th scope="col">Статус</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="x" value="1"/>
        <c:forEach items="${tempUserNewThemes}" var="newTempUserTheme">
            <tr>
                <th scope="row">${x}</th>
                <td>${newTempUserTheme.getThemeName()}</td>
                <td>${newTempUserTheme.getNumberOfQuestions()}</td>
                <c:choose>
                    <c:when test="${newTempUserTheme.getPermit() == false}">
                        <td> В процессе согласования</td>
                    </c:when>
                    <c:otherwise>
                        <td>
                            <form method="get" action="getTempNewTheme">
                             <input type="hidden" name="newTempUserThemeId" value="${newTempUserTheme.getId()}">
                            <button class="btn btn-primary" type="submit">Добавить описание темы</button>
                            </form>
                        </td>
                    </c:otherwise>
                </c:choose>
            </tr>
            <c:set var="x" value="${x+1}"/>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<form id="addNewTheme" method="get" action="addTheme.jsp">
    <button class="btn btn-primary" type="submit" style="margin-bottom: 4px">Добавить тему</button>
</form>


<form id="changeOwnData" name="changeOwnData" method="get" action="changeOwnData.jsp">
    <button class="btn btn-primary" type="submit"><fmt:message key="changeOwnDate.changeData"
                                                               bundle="${messages}"/></button>
</form>
<form id="exit" name="exit" method="get" action="exit" style="margin-top:3px">
    <button class="btn btn-primary" type="submit"><fmt:message key="HelloUser.exit" bundle="${messages}"/></button>
</form>
