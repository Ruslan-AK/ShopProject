<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin menu</title>
</head>
<body>
<fieldset>
    <table>
        <tbody>
        <tr>
            <td>
                <h4>Select menu:</h4>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/adminClientMenu" method="post">
                    <input type="submit" value="Clients">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/adminProductMenu" method="post">
                    <input type="submit" value="Products">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/adminOrderMenu" method="post">
                    <input type="submit" value="Orders">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/initDB" method="post">
                    <input type="submit" value="Init DB">
                </form>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
</body>
</html>