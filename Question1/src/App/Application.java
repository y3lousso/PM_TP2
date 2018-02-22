package App;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Application {

	/**
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FileSystemXmlApplicationContext( "src/ViaRMI.xml" );

	}

}
