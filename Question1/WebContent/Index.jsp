<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Bank</title>
    </head>
    <body>
        <h1>Bank via Internet</h1>
		<p>
        	Numéro :
            <% 
            String numero = (String) request.getAttribute("numero");
            out.println( numero );
            %> 
		</p>
		<p>
        	Name :
            <% 
            String nom = (String) request.getAttribute("nom");
            out.println( nom );%>
		</p>
		<p>
        	Telephone :
            <% 
            String telephone = (String) request.getAttribute("telephone");
            out.println( telephone );%>
		</p>
		<p>
        	Service :
            <% 
            String service = (String) request.getAttribute("service");
            out.println( service );%>
		</p>
		<p>
        	Solde :
            <% 
            String solde = (String) request.getAttribute("solde");
            out.println( solde );%>
		</p>
		
		<form action="Home" method="post">
			<select name=option >
				<option value="deposer" selected="selected">Deposer</option>
				<option value="retirer">Retirer</option>
			</select>
			<input type="text" name="textBoxSum" value = "0">
			<input type="submit" name="btnSubmit" value="Submit" />
		</form>
        
    </body>
</html>