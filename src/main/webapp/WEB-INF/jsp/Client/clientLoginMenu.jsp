<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <spring:url value="/resources/css/bootstrap.min.css"
                var="bootstrapCss" />
    <link href="${bootstrapCss}" rel="stylesheet" />
</head>
<body>
<form action="/clientLogin" method="get">
    <div class="form-group form-group-lg">
    <fieldset>
        <table>
            <tbody>
            <tr>
                <td>
                    <h4 id="formatz">Format phone 0671234567</h4>
                    <h4>Fill form, please:</h4>
                </td>
            </tr>
            <tr>
                <td>
                    Phone <input id="form" type="text" name="phone" class="form-control" id="username">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Login">
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
    </div>
</form>
</body>
</html>