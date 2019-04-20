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
                <form action="/adminClientMenu" method="get">
                    <input type="submit" value="Clients">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/adminProductMenu" method="get">
                    <input type="submit" value="Products">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/adminOrderMenu" method="get">
                    <input type="submit" value="Orders">
                </form>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
</body>
</html>