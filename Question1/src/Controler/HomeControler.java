package Controler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import Model.Compte;
import Model.Implementation_du_Compte;

public class HomeControler extends HttpServlet {
	
	private Compte compte;
	
	public HomeControler() 
	{
		//ApplicationContext ctx = new Clas("../AuGuichet.xml");
		//compte = (Compte)ctx.getBean("compt007");
		
		compte = new Implementation_du_Compte("a","a","a","a");
	}
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
					
		request.setAttribute( "numero", compte.getNumero() );
		request.setAttribute( "nom", compte.getNom() );
		request.setAttribute( "telephone", compte.getNumeroTel() );
		request.setAttribute( "service", compte.getService() );
		request.setAttribute( "solde", Double.toString(compte.getSolde()) );
			
		this.getServletContext().getRequestDispatcher( "/Index.jsp" ).forward( request, response );
	}
    
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
				
		float value = Float.parseFloat(request.getParameter("textBoxSum"));
		
		if( request.getParameter("option").equals("deposer") ) 
		{
			compte.deposer(value);
		}else if( request.getParameter("option").equals("retirer") )
		{
			compte.retirer(value);
		}else {
			System.out.println(request.getParameter("option"));
		}
	
		request.setAttribute( "numero", compte.getNumero() );
		request.setAttribute( "nom", compte.getNom() );
		request.setAttribute( "telephone", compte.getNumeroTel() );
		request.setAttribute( "service", compte.getService() );
		request.setAttribute( "solde", Double.toString(compte.getSolde()) );
			
		this.getServletContext().getRequestDispatcher( "/Index.jsp" ).forward( request, response );
	}

}