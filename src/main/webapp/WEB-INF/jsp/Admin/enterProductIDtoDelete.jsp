<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Enter ID</title>
</head>
<body>
<form action="/deleteProduct" method="delete">
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
                    Id <input type="text" name="id">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Enter">
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
</form>
</body>
</html>