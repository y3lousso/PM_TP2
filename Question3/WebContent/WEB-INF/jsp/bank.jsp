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
        ${account.numero}
    </p>
    <p>
        Name :
        ${account.nom}
    </p>
    <p>
        Telephone :
        ${account.numeroTel}
    </p>
    <p>
        Service :
        ${account.service}
    </p>
    <p>
        Solde :
        ${account.solde}
    </p>

    <%--<form:form method="POST" action="/bank">--%>
        <%--<form:input path="action"/>--%>
        <%--<form:input path="value" value = "0"/>--%>
        <%--<input type="submit" value="Submit"/>--%>
    <%--</form:form>--%>


</body>
</html>