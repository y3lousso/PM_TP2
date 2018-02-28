package Controler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;

import Model.Compte;
import Model.Dal;
import Model.Implementation_du_Compte;

public class HomeControler extends HttpServlet {
	
	private String id = "007";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
					
		Dal dal = new Dal();
		
		request.setAttribute( "numero", id );		
		request.setAttribute( "nom", dal.getItem("nom", id) );
		request.setAttribute( "telephone", dal.getItem("telephone", id) );
		request.setAttribute( "service", dal.getItem("service", id) );
		request.setAttribute( "solde", dal.getItem("solde", id) );
			
		this.getServletContext().getRequestDispatcher( "/Index.jsp" ).forward( request, response );
	}
    
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
				
		Dal dal = new Dal();
		
		float value = Float.parseFloat(request.getParameter("textBoxSum"));
		
		if( request.getParameter("option").equals("deposer") ) 
		{
			try {
				dal.deposer(id, value);
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if( request.getParameter("option").equals("retirer") )
		{
			try {
				dal.retirer(id, value);
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println(request.getParameter("option"));
		}
	
		request.setAttribute( "numero", id );		
		request.setAttribute( "nom", dal.getItem("nom", id) );
		request.setAttribute( "telephone", dal.getItem("telephone", id) );
		request.setAttribute( "service", dal.getItem("service", id) );
		request.setAttribute( "solde", dal.getItem("solde", id) );
			
		this.getServletContext().getRequestDispatcher( "/Index.jsp" ).forward( request, response );
	}

}