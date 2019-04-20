<%--
  Created by IntelliJ IDEA.
  User: Reneuby
  Date: 27.03.2019
  Time: 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Client</title>
</head>
<body>
<fieldset>
    <form action="/updateCurrentClient" method="put" >
    <table>
        <tbody>
        <tr>
            <td>
                <h3>Name:</h3>
            </td>
            <td>
                <input type="text" name="name" value="${name}">
            </td>
        </tr>
        <tr>
            <td>
                <h3>Surname:</h3>
            </td>
            <td>
                <input type="text" name="surname" value="${surname}">
            </td>
        </tr>
        <tr>
            <td>
                <h3>Age:</h3>
            </td>
            <td>
                <input type="text" name="age" value="${age}">
            </td>
        </tr>
        <tr>
            <td>
                <h3>Email:</h3>
            </td>
            <td>
                <input type="text" name="email" value="${email}">
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Update">
            </td>
        </tr>
        </tbody>
    </table>
    </form>
</fieldset>
</body>
</html>