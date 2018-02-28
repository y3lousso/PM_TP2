package Model;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class Dal {
	
	String fileName = "db.xml";
	Document doc;
	
	public String getItem(String itemName, String numero) {
	
		NodeList clients = getClients();				
		for(int i = 0; i<clients.getLength();i++) 
		{
			// Remove all parser errors ...
			if(clients.item(i).getNodeType() == Node.ELEMENT_NODE && clients.item(i).getAttributes().getNamedItem("numero").getNodeValue().equals(numero))
			{			
				NodeList clientSpec = clients.item(i).getChildNodes();			
				for(int j = 0; j<clientSpec.getLength();j++) 
				{
					// Remove all parser errors ...
					if(clientSpec.item(j).getNodeType() == Node.ELEMENT_NODE && clientSpec.item(j).getNodeName().equals(itemName))
					{
							return clientSpec.item(j).getTextContent();
					}
				}
			}
		}		
		return null;
	}
	
	public void deposer(String numero, float amount) throws TransformerException
	{
		setSolde(numero, amount);		
	}
	
	public void retirer(String numero, float amount) throws TransformerException
	{
		setSolde(numero, -amount);		
	}
	
	private void setSolde(String numero, float amount) throws TransformerException
	{
		NodeList clients = getClients();				
		for(int i = 0; i<clients.getLength();i++) 
		{
			// Remove all parser errors ...
			if(clients.item(i).getNodeType() == Node.ELEMENT_NODE && clients.item(i).getAttributes().getNamedItem("numero").getNodeValue().equals(numero))
			{			
				NodeList clientSpec = clients.item(i).getChildNodes();			
				for(int j = 0; j<clientSpec.getLength();j++) 
				{
					// Remove all parser errors ...
					if(clientSpec.item(j).getNodeType() == Node.ELEMENT_NODE && clientSpec.item(j).getNodeName().equals("solde"))
					{
							float newSolde = Float.parseFloat(clientSpec.item(j).getTextContent() ) + amount;
							clientSpec.item(j).setTextContent( Float.toString(newSolde) );
							
							TransformerFactory transformerFactory = TransformerFactory.newInstance();
							Transformer transformer = transformerFactory.newTransformer();
							DOMSource source = new DOMSource(doc);
							
							ClassLoader classLoader = getClass().getClassLoader();
							StreamResult result = new StreamResult(new File(classLoader.getResource(fileName).getFile()));

							transformer.transform(source, result);
							
							System.out.println(clientSpec.item(j).getTextContent() );
					}
				}
			}
		}	
	}
	
	private NodeList getClients() 
	{
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
        
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(file);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
	        
			return doc.getDocumentElement().getChildNodes();
		}
		catch (Exception e) 
		{
		    System.out.println(e.getMessage());
		}
		return null;
	}

}
