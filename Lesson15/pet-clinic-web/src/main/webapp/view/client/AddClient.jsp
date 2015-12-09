<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Добавление клиента</title>
</head>
<body>
    <h1 align="center">Добавление клиента</h1>
    <hr>

    <br>
    <a href="${pageContext.servletContext.contextPath}/clinic/view">Вернуться в клинику</a>

    <br>
    <br>
    <br>
    <br>
    <form action="${pageContext.servletContext.contextPath}/clinic/add_client" method='POST'>
        <table>
            <tr>
                <td align="left" >Имя клиента: </td>
                <td>
                    <input type="text" name="name" value="${nameValue}" />
                </td>
            </tr>
            <tr>
                <td align="left" >Id* клиента: </td>
                <td>
                    <input type="text" name="id" value="${idValue}" />
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" name="add" value="Добавить" />
                </td>
            </tr>
        </table>
    </form>

    <br>
    <br>
    <c:if test="${errorValue != null}">
        <h3>${errorValue}</h3>
    </c:if>

</body>
</html>
