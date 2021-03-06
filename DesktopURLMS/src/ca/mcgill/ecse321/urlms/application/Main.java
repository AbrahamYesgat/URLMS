package ca.mcgill.ecse321.urlms.application;

import java.io.File;

import ca.mcgill.ecse321.urlms.model.*;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;
import ca.mcgill.ecse321.urlms.view.LoginPage;

/**
 * This class contains the main method of the system. The application runs from here.
 * The database is loaded or created and the UI is initialized.  
 * @author Group 1
 *
 */
public class Main {
	// Initialization of data file and system
	private static String fileName = "output"+File.separator+"data.xml";
	private static URLMS urlms = URLMS.getInstance();
	
	/**
	 * The database is loaded or created and the UI is initialized and ran.
	 */
	public static void main(String[] args) {
		// Initialize data file or load data.
		PersistenceXStream.initializeURLMS(fileName);
		urlms = (URLMS) PersistenceXStream.loadFromXMLwithXStream();
		// Start UI
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
			new LoginPage(urlms).setVisible(true);
			}
		});
	}
}
