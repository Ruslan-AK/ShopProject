 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main Menu</title>
</head>
<body>
<fieldset>
    <table>
        <tbody>
        <tr>
            <td>
                <h3><em>Hi! Welcome</em></h3>
            </td>
        </tr>
        <tr>
            <td>
                <h4>Select your role:</h4>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/enterAsAdmin" method="post">
                    <input type="submit" value="Admin">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/enterAsClient" method="post">
                    <input type="submit" value="Client">
                </form>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
</body>
</html>