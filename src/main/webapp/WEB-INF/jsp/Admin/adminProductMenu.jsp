<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product Menu</title>
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
                <form action="/showProducts" method="get">
                    <input type="submit" value="Show all products">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/createProductBlank" method="get">
                    <input type="submit" value="Create product">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/enterProductIDtoUpdate" method="get">
                    <input type="submit" value="Modify product">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/enterProductIDtoDelete" method="get">
                    <input type="submit" value="Delete product"/>
                </form>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
</body>
</html>