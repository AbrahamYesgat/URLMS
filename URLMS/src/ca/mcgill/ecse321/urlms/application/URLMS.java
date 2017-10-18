package ca.mcgill.ecse321.urlms.application;

import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;
import ca.mcgill.ecse321.urlms.view.LoginPage;

public class URLMS {
	private static String fileName = "Output/urlms.xml";

	public static void main(String[] args) {
		// load model
		final ca.mcgill.ecse321.urlms.model.URLMS urlms = PersistenceXStream.initializeURLMS(fileName);

		// start UI
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new LoginPage(urlms).setVisible(true);
			}
		});
	}
}
