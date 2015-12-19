<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Редактирование клиента</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css" />

    <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">

        var name = "";

        $(document).ready(function(){
            name = $('#name').val();
        });

        function nameChanged() {
            $("#changes").text("");
        }

        function createPet() {
            if (validatePetName()) {
                $('#petName').css('background-color', '');
            } else {
                alert("Нужно заполнить имя!");
                return false;   // cancel submit
            }
        }

        function validatePetName() {
            var result = true;
            if ($('#petName').val() == '') {
                $('#petName').css('background-color', 'pink');
                result = false;
            }
            return result;
        }

        function updateClient() {
            if (name == $('#name').val()) {
                alert("Имя не изменилось!");
                return false;   // cancel submit
            }
        }

    </script>
</head>
<body>
    <div id="main">
        <div id="header">
            <div id="clinic">
                <img src="../images/editclient.jpg">
                <a href="/pcw/clinic/view"><img src="../images/logo.png" id="logo"></a>
            </div>
        </div>

        <div id="dialog">

            <div id="backToClinic">
                <a href="${pageContext.servletContext.contextPath}/clinic/view">Вернуться в клинику</a>
            </div>


            <form action="${pageContext.servletContext.contextPath}/client/edit" method='POST'>

                <div id="updateParams">
                    <p>
                        Имя:
                        <input type="text" name="name" value="${client.fullName}" id="name"
                               onchange="return nameChanged();">
                         Id: ${client.id}
                        <input type="submit" name="update" value="Изменить"
                               id="updateClientButton" onclick="return updateClient();">
                        <span id="changes">${changes}</span>
                    </p>
                    <input type="submit" name="remove" value="Удалить!" id="removeClientButton" />
                </div>
            </form>

            <div id="addClientDialog">
                <h2>Добавить животное:</h2>
                <form action="${pageContext.servletContext.contextPath}/client/edit" method='POST'>
                    <div id="addClientParams">
                        Тип: <select name="petType">
                                <option value='7'>Другое
                                <option value='1'>Кошка
                                <option value='2'>Собака
                                <option value='3'>Рыбка
                                <option value='4'>Птица
                                <option value='5'>Рептилия
                                <option value='6'>Грызун
                        </select>

                        Имя*: <input type="text" name="petName" id="petName" />
                        <input type="submit" name="addPet" value="" id="addPetButton" onclick="return createPet();">
                    </div>
                </form>

                <c:choose>
                    <c:when test="${client.pets.size() > 0}">
                        <h3>Домашние животные:</h3>
                        <table>
                            <tr>
                                <th>Тип</th>
                                <th>Имя</th>
                                <th>Удалить</th>
                            </tr>
                            <c:forEach items="${client.pets}" var="pet" varStatus="status">
                                <tr>
                                    <td class="petType" >${pet.getRuStringPetType()}</td>
                                    <td class="petName" >${pet.name}</td>
                                    <td>
                                        <a href="${pageContext.servletContext.contextPath}/pet/delete?name=${pet.name}">delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <h4>Животных нет!</h4>
                    </c:otherwise>
                </c:choose>
            </div>

        </div>

    </div>

</body>
</html>
