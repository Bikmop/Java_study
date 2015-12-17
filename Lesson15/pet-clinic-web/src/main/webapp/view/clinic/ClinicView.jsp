<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Клиника домашних животных</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
<body>
    <div id="main">
        <div id="header">
            <div id="clinic">
                <a href="/pcw/clinic/view"><img src="../images/clinic.jpg"></a>
                <a href="/pcw/clinic/view"><img src="../images/logo.png" id="logo"></a>
            </div>
        </div>


        <div id="dialog">

            <form action="${pageContext.servletContext.contextPath}/clinic/view" method='GET'>
                <div id="addClient">
                    Добавить нового клиента:
                    <input type="submit" name="addClient" value="" id="addButton" />
                </div>
            </form>

            <div id="search">
                <h2>Поиск клиентов</h2>

                <form action="${pageContext.servletContext.contextPath}/clinic/view" method='POST'>
                    <div id="searchParams">
                        <p>
                            <label for="name">Имя клиента: </label>
                            <input type="text" name="name" value="${nameValue}" id="name" />
                            <input type="checkbox" name="nameFull" id="nameFull" <c:if test="${nameFullValue != null}">checked</c:if> />
                            <label for="nameFull">полное</label>
                        </p>

                        <p>
                            <label for="id">Id клиента: </label>
                            <input type="text" name="id" value="${idValue}" id="id" />
                            <input type="checkbox" name="idFull" id="idFull" <c:if test="${idFullValue != null}">checked</c:if> />
                            <label for="idFull">полный</label>
                        </p>

                        <p>
                            <label for="petName">Имя животного: </label>
                            <input type="text" name="petName" value="${petNameValue}" id="petName" />
                            <input type="submit" name="clear" value=" X " />
                        </p>

                        <input type="submit" name="search" value="" id="searchButton" />
                    </div>
                </form>

                <c:if test="${showClients == true}">
                    <c:choose>
                        <c:when test="${clients.size() > 0}">
                            <h3>Результат поиска:</h3>
                            <table>
                                <tr>
                                    <th>Полное имя</th>
                                    <th>Id</th>
                                    <th>Кол-во животных</th>
                                </tr>
                                <c:forEach items="${clients}" var="client" varStatus="status">
                                    <tr>
                                        <td class="clientName">${client.fullName}</td>
                                        <td>
                                            <a href="${pageContext.servletContext.contextPath}/client/edit?id=${client.id}">${client.id}</a>
                                        </td>
                                        <td class="petsNumber">${client.pets.size()}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <h4>Клиенты не найдены!</h4>
                        </c:otherwise>
                    </c:choose>
                </c:if>

            </div>
        </div>

    </div>

</body>
</html>

