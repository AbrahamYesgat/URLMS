package ca.mcgill.ecse321.urlms.application;

import java.io.File;

import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;
import ca.mcgill.ecse321.urlms.view.LoginPage;

public class Main {
	private static String fileName = "output"+File.separator+"data.xml";
	private static URLMS urlms = new URLMS();
	
	public static void main(String[] args) {
		// load model
		PersistenceXStream.initializeURLMS(fileName);
		urlms = (URLMS) PersistenceXStream.loadFromXMLwithXStream();

		// start UI
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
			new LoginPage(urlms).setVisible(true);
			}
		});
	}
}
