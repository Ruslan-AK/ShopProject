<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order Menu</title>
</head>
<body>
<br>
<fieldset>
    <table>
        <tbody>
        <tr>
            <td>
                <h4>Select action:</h4>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/showOrders" method="post">
                    <input type="submit" value="Show all orders">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/adminDeleteOrder" method="post">
                    <input type="submit" value="Delete order">
                </form>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
</body>
</html>