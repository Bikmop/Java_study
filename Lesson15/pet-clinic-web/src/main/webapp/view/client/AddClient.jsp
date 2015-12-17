<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Добавление клиента</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
<body>
    <div id="main">
        <div id="header">
            <div id="clinic">
                <img src="../images/addclient.jpg">
                <a href="/pcw/clinic/view"><img src="../images/logo.png" id="logo"></a>
            </div>
        </div>

        <div id="dialog">

            <div id="backToClinic">
                <a href="${pageContext.servletContext.contextPath}/clinic/view">Вернуться в клинику</a>
            </div>

            <form action="${pageContext.servletContext.contextPath}/clinic/add_client" method='POST'>
                <div id="addParams">
                    <p>
                        <label for="name">Имя клиента: </label>
                        <input type="text" name="name" value="${nameValue}" id="name" />
                    </p>
                    <p>
                        <label for="id">Id* клиента: </label>
                        <input type="text" name="id" value="${idValue}" id="id" />
                    </p>
                    <input type="submit" name="add" value="" id="addClientButton" />

                    <c:if test="${errorValue != null}">
                        <h4>${errorValue}</h4>
                    </c:if>
                </div>
            </form>

        </div>
    </div>

</body>
</html>
