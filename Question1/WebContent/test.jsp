<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Bank</title>
    </head>
    <body>
        <p>Bank via Internet</p>
        <p>
            <% 
            String name = (String) request.getAttribute("name");
            out.println( name );
            %>
        </p>
    </body>
</html>