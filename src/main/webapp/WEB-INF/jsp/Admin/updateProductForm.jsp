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
    <title>Update Product</title>
</head>
<body>
<fieldset>
    <form action="/updateProduct" method="put" >
    <table>
        <tbody>
        <tr>
            <td>
                <h3>Firm:</h3>
            </td>
            <td>
                <input type="text" name="firm" value="${firm}">
            </td>
        </tr>
        <tr>
            <td>
                <h3>Model:</h3>
            </td>
            <td>
                <input type="text" name="model" value="${model}">
            </td>
        </tr>
        <tr>
            <td>
                <h3>Price:</h3>
            </td>
            <td>
                <input type="text" name="price" value="${price}">
            </td>
        </tr>
        </tr>
        <td>
            Type
        </td>
        <td>
            Select Type from following:
            <select name="type">
                ${options}
            </select>
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