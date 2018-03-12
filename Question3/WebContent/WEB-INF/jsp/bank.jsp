<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Your bank</title>
    <style type="text/css">
        body {
            background-image: url('https://crunchify.com/bg.png');
        }
    </style>
</head>
<body>
    <h1>Your bank via Internet</h1>
    <p>
        Numéro :
        ${account.getNumero()}
    </p>
    <p>
        Name :
        ${account.getNom()}
    </p>
    <p>
        Telephone :
        ${account.getNumeroTel()}
    </p>
    <p>
        Service :
        ${account.getService()}
    </p>
    <p>
        Solde :
        ${account.getSolde()}
    </p>

    <form:form  method="POST" action="/accountAction" modelAttribute="accountAction" >
        <table>
                <tr>
                    <td><form:label path="action">Action</form:label></td>
                    <td><form:input path="action"/></td>
                </tr>
                <tr>
                    <td><form:label path="value">Value</form:label></td>
                    <td><form:input path="value"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
    </form:form>


</body>
</html>