<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Client Menu</title>
</head>
<body>
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
                <form action="showBuyProducts" method="get">
                    <input type="submit" value="Show products">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/myOrder" method="get">
                    <input type="submit" value="My order">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/myAccount" method="get">
                    <input type="submit" value="My account">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/logOut" method="get">
                    <input type="submit" value="Log out">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/showOrdersByClient" method="get">
                    <input type="submit" value="Show order archive">
                </form>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
</body>
</html>