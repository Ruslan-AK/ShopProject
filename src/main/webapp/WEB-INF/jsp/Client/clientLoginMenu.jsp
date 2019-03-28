<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<form action="/clientLogin" method="post">
    <fieldset>
        <table>
            <tbody>
            <tr>
                <td>
                    <h4>Format phone 0671234567</h4>
                    <h4>Fill form, please:</h4>
                </td>
            </tr>
            <tr>
                <td>
                    Phone <input type="text" name="phone">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Login">
                </td>
            </tr>
            <tr>
                <td>
                    <form action="../mainMenu.html">
                        <input type="submit" value="Back to menu"/>
                    </form>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
</form>
</body>
</html>