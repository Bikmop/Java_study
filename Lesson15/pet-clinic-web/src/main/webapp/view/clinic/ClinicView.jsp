<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Клиника домашних животных</title>
</head>
<body>
    <h1 align="center">Клиника домашних животных</h1>
    <hr>

    <br>
    <form action="${pageContext.servletContext.contextPath}/clinic/add_client" method='POST'>
        <input type="submit" name="addClient" value="Добавить клиента" />
    </form>
    <br>

    <h2>Поиск клиентов:</h2>
    <form action="${pageContext.servletContext.contextPath}/clinic/view" method='POST'>
        <table>
            <tr>
                <td align="left" >Имя клиента: </td>
                <td>
                    <input type="text" name="name" value="${nameValue}" />
                </td>
                <td>
                    <input type="checkbox" name="nameFull" <c:if test="${nameFullValue != null}">checked</c:if> />
                </td>
                <td align="left" >полное</td>
            </tr>
            <tr>
                <td align="left" >Id клиента: </td>
                <td>
                    <input type="text" name="id" value="${idValue}" />
                </td>
                <td>
                    <input type="checkbox" name="idFull"  <c:if test="${idFullValue != null}">checked</c:if> />
                </td>
                <td align="left" >полный</td>
            </tr>
            <tr>
                <td align="left" >Имя животного: </td>
                <td>
                    <input type="text" name="petName" value="${petNameValue}" />
                </td>
                <td>
                    <input type="submit" name="clear" value="X" />
                </td>
                <td></td>
                <td>
                    <input type="submit" name="search" value="Найти" />
                </td>
            </tr>

        </table>
    </form>

    <c:if test="${showClients == true}">
        <br>
        <c:choose>
            <c:when test="${clients.size() > 0}">
                <h3>Клиенты:</h3>
                <table border="1">
                    <tr>
                        <td align="center" style='border : 2px solid black' >Полное имя</td>
                        <td align="center" style='border : 2px solid black' >Id</td>
                        <td align="center" style='border : 2px solid black' >Кол-во животных</td>
                    </tr>
                    <c:forEach items="${clients}" var="client" varStatus="status">
                        <tr valign="top">
                            <td>${client.fullName}</td>
                            <td>
                                <a href="${pageContext.servletContext.contextPath}/client/edit?id=${client.id}">${client.id}</a>
                            </td>
                            <td align="center" >${client.pets.size()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <h3>Клиенты не найдены!</h3>
            </c:otherwise>
        </c:choose>
    </c:if>


</body>
</html>

