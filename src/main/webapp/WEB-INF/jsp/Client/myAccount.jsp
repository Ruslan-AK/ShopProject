<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Account</title>
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
                <form action="/myAccountInfo" method="get">
                    <input type="submit" value="Show Account Info">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/getCurrentClientBlank" method="get">
                    <input type="submit" value="Modify">
                </form>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
</body>
</html>