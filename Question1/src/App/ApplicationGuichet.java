package App;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ApplicationGuichet {

	/**
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FileSystemXmlApplicationContext( "src/AuGuichet.xml" );

	}

}