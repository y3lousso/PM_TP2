package com.arnaud.zinflou.test.spring;

import org.springframework.context.support.FileSystemXmlApplicationContext;
public class ApplicationRMI {

	/**
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// static Logger log = Logger.getLogger(MyClass.class);
		// TODO Auto-generated method stub
		new FileSystemXmlApplicationContext( "src/ServeurRMI.xml" );

	}

}
