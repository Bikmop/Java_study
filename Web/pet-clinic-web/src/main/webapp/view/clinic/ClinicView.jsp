<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Клиника домашних животных</title>
</head>
<body>
    <p align="center">КЛИНИКА ДОМАШНИХ ЖИВОТНЫХ</p>
    <hr>
    <hr>
    <p>Поиск клиентов:</p>
    <form action="${pageContext.servletContext.contextPath}/clinic/view" method='POST'>
        <table>
            <tr>
                <td align="right" >Имя клиента: </td>
                <td>
                    <input type="text" name="name">
                </td>
            </tr>
            <tr>
                <td align="right" >ID клиента: </td>
                <td>
                    <input type="text" name="id">
                </td>
            </tr>
            <tr>
                <td align="right" >Полное имя животного: </td>
                <td>
                    <input type="text" name="petName">
                </td>
            </tr>
            <tr>
                <td><input type="submit" align="center" value="Искать"/></td>
            </tr>
        </table>
    </form>




</body>
</html>
