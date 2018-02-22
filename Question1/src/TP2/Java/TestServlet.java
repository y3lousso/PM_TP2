package TP2.Java;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
			
		String paramAuteur = request.getParameter( "auteur" );
		String message = "Transmission de variables : OK ! " + paramAuteur;
			
		Compte compte = new Implementation_du_Compte("a","a","a","a");
			
		request.setAttribute( "name", compte.getNom() );
			
		this.getServletContext().getRequestDispatcher( "/test.jsp" ).forward( request, response );
	}
    
}