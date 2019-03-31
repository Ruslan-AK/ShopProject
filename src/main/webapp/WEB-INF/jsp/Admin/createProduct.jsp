<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Product</title>
</head>
<body>
<form action="/createProduct" method="post">
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
                    Firm
                </td>
                <td>
                    <input type="text" name="firm"/>
                </td>
            </tr>

            <tr>
                <td>
                    Model
                </td>
                <td>
                    <input type="text" name="model"> <br>
                </td>
            </tr>
            <tr>
                <td>
                    Price
                </td>
                <td>
                    <input type="text" name="price"> <br>
                </td>
            </tr>
                <td>
                    Type
                </td>
                <td>
                    Select Type from following:
                    <select name="type">
                        ${options}
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Create product">
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
</form>
</body>
</html>