<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Client</title>
</head>
<body>
<form action="/createClient" method="post">
    <fieldset>
        <table>
            <tbody>
            <tr>
                <td>
                    <h4>Fill form, please:</h4>
                </td>
            </tr>
            <tr>
                <td>
                    Name
                </td>
                <td>
                    <input type="text" name="name"/>
                </td>
            </tr>

            <tr>
                <td>
                    Surname
                </td>
                <td>
                    <input type="text" name="surname"> <br>
                </td>
            </tr>
            <tr>
                <td>
                    Age
                </td>
                <td>
                    <input type="text" name="age"> <br>
                </td>
            </tr>
            <tr>
                <td>
                    Phone
                </td>
                <td>
                    <input type="text" name="phone"> <br>
                </td>
            </tr>
            <tr>
                <td>
                    Email
                </td>
                <td>
                    <input type="text" name="email"> <br>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Create client">
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
</form>
</body>
</html>