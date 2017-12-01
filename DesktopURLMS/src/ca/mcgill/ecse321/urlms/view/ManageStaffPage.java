package ca.mcgill.ecse321.urlms.view;

import javax.swing.JFrame;

import ca.mcgill.ecse321.urlms.model.URLMS;

public class ManageStaffPage extends JFrame{
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -6286210470352765374L;
	/**
	 * URLMS system
	 */
	private URLMS urlms;
	/**
	 * Constructor of ManageStaffPage frame
	 * @param urlms current URLMS system
	 */
	public ManageStaffPage(URLMS urlms){
		this.urlms = urlms;
	}

}
