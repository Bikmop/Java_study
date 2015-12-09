<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Редактирование клиента</title>
</head>
<body>
    <h1 align="center">Редактирование клиента</h1>
    <hr>

    <br>
    <a href="${pageContext.servletContext.contextPath}/clinic/view">Вернуться в клинику</a>

    <br>
    <br>
    <br>
    <br>
    <form action="${pageContext.servletContext.contextPath}/client/edit" method='POST'>
        Имя: <input type="text" name="name" value="${client.fullName}" />
        Id: ${client.id}
        <input type="submit" name="update" value="Изменить" />
        ${changes}
        <br>
        <br>
        <input type="submit" name="remove" value="Удалить!" />
    </form>

    <br>
    <br>
    <h2>Добавить животное:</h2>
    <form action="${pageContext.servletContext.contextPath}/client/edit" method='POST'>
        Тип: <select name="petType">
                <option value='7'>Другое
                <option value='1'>Кошка
                <option value='2'>Собака
                <option value='3'>Рыбка
                <option value='4'>Птица
                <option value='5'>Рептилия
                <option value='6'>Грызун
        </select>

        Имя: <input type="text" name="petName" />
        <input type="submit" name="addPet" value="Добавить" />
    </form>

    <br>
    <br>
    <c:choose>
        <c:when test="${client.pets.size() > 0}">
            <h3>Домашние животные:</h3>
            <table border="1">
                <tr>
                    <td align="center" style='border : 2px solid black' >Тип</td>
                    <td align="center" style='border : 2px solid black' >Имя</td>
                    <td align="center" style='border : 2px solid black' >Удалить</td>
                </tr>
                <c:forEach items="${client.pets}" var="pet" varStatus="status">
                    <tr valign="top">
                        <td>${pet.getRuStringPetType()}</td>
                        <td>${pet.name}</td>
                        <td>
                            <a href="${pageContext.servletContext.contextPath}/pet/delete?name=${pet.name}">delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <h3>Животных нет!</h3>
        </c:otherwise>
    </c:choose>

</body>
</html>
