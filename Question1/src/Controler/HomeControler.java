package Controler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Compte;
import Model.Implementation_du_Compte;

public class HomeControler extends HttpServlet {
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		// Access to bdd ..
		Compte compte = new Implementation_du_Compte("a","a","a","a");
			
		request.setAttribute( "numero", compte.getNumero() );
		request.setAttribute( "nom", compte.getNom() );
		request.setAttribute( "telephone", compte.getNumeroTel() );
		request.setAttribute( "service", compte.getService() );
		request.setAttribute( "solde", Double.toString(compte.getSolde()) );
			
		this.getServletContext().getRequestDispatcher( "/Index.jsp" ).forward( request, response );
	}
    
public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		// Access to bdd ..
		Compte compte = new Implementation_du_Compte("b","a","a","a");
			
		request.setAttribute( "numero", compte.getNumero() );
		request.setAttribute( "nom", compte.getNom() );
		request.setAttribute( "telephone", compte.getNumeroTel() );
		request.setAttribute( "service", compte.getService() );
		request.setAttribute( "solde", Double.toString(compte.getSolde()) );
			
		this.getServletContext().getRequestDispatcher( "/Index.jsp" ).forward( request, response );
	}

}