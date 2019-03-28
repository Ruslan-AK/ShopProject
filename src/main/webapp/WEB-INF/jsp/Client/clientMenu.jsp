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
                <form action="/Client/showBuyProducts.jsp">
                    <input type="submit" value="Show products">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/myOrder" method="post">
                    <input type="submit" value="My order">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="myAccount.jsp">
                    <input type="submit" value="My account">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/logOut" method="post">
                    <input type="submit" value="Log out">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/showOrdersByClient" method="post">
                    <input type="submit" value="Show order archive">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="../mainMenu.jsp">
                    <input type="submit" value="Back to menu">
                </form>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
</body>
</html>