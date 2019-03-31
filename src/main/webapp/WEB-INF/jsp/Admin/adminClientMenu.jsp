<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Client Menu</title>
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
                <form action="/showClients" method="post">
                    <input type="submit" value="Show all clients">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/adminCreateClient" method="post">
                    <input type="submit" value="Create client">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/adminClientLoginMenu" method="post">
                    <input type="submit" value="Modify client">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/adminDeleteClientEnterPhone" method="post">
                    <input type="submit" value="Delete client">
                </form>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
</body>
</html>