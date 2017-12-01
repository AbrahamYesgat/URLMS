package ca.mcgill.ecse321.urlms.view;

import javax.swing.JFrame;

import ca.mcgill.ecse321.urlms.model.URLMS;

public class ManageSupplyPage extends JFrame{
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -5490159581461131362L;
	/**
	 * URLMS system
	 */
	private URLMS urlms;
	/**
	 * Constructor of ManageStaffPage frame
	 * @param urlms current URLMS system
	 */
	public ManageSupplyPage(URLMS urlms){
		this.urlms = urlms;
	}

}
