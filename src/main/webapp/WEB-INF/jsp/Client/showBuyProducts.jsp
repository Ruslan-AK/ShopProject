<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Buy</title>
</head>
<body>
<em>You can buy product here</em>
<br>
<em>Products:</em>
<br>
<fieldset>
    <table>
        <tbody>
        <tr>
            <td>
                <iframe srcdoc="${message}"></iframe>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<form action="/buyProduct" method="post">
    <fieldset>
        <em>Enter id to buy product:</em>
        <table>
                <tbody>
                <tr>
                    <td>
                        Id <input type="text" name="id"> <br>
                    </td>
                </tr>
                </tbody>
            </table>
        <br>
        <input type="submit" value="Buy Product">
        </fieldset>
    </form>
</body>
</html>