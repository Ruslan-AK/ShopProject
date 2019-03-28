<%--
  Created by IntelliJ IDEA.
  User: Reneuby
  Date: 26.03.2019
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang=\"en\">
<head>
    <meta charset=\"UTF-8\">
    <title>Purchase</title>
</head>
<body>
<fieldset>
    <table>
        <tbody>
        <tr>
            <td>
                <h2>Current Order:</h2>
            </td>
        </tr>
        <tr>
            <td>
                ${currentOrder}
            </td>
        </tr>
        <tr>
            <td>
                <h2>Total price:</h2>
            </td>
        </tr>
        <tr>
            <td>
                ${summaryPrice}
            </td>
        </tr>
        </tbody>
    </table>
    </fieldset>
    <form action="/Client/clientMenu.jsp">
        <input type="submit" value="Back to menu"/>
    </form>
</body>
</html>