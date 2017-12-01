package ca.mcgill.ecse321.urlms.view;

import javax.swing.JFrame;

import ca.mcgill.ecse321.urlms.model.URLMS;

public class ManageEquipmentPage extends JFrame{
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 8116576510197298967L;
	/**
	 * URLMS system
	 */
	private URLMS urlms;
	/**
	 * Constructor of ManageEquipmentPage frame
	 * @param urlms current URLMS system
	 */
	public ManageEquipmentPage(URLMS urlms){
		this.urlms = urlms;
	}

}
