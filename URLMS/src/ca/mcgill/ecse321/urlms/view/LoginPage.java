package ca.mcgill.ecse321.urlms.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.*;

/**
 * UI for the login page.
 * @author Group 1
 *
 */
public class LoginPage extends JFrame{
	private static final long serialVersionUID = 7324018789116682077L;
	private JTextField emailLoginTextField;
	private JLabel emailLoginLabel;
	private JTextField passwordLoginTextField;
	private JLabel passwordLoginLabel;
	private JButton loginButton;
	private URLMS urlms;
	private List<Director> dirs;
	
	/**
	 * "constructor" for the UI.
	 * @param urlms Gets the instance of the system. 
	 */
	public LoginPage(URLMS urlms) {
		this.urlms = urlms;
	    initComponents();
	}
	
	/**
	 * Clears the text fields based on the button click response.
	 */
	private void refreshData() {
	    emailLoginTextField.setText("");
	    passwordLoginTextField.setText("");
	    pack();
	}
	
	/**
	 *  This is performed when the user clicks on the login button.
	 *  Calls the login method from the controller. 
	 *  Updates the UI accordingly to the result of the login method. 
	 */
	private void loginButtonActionPerformed() {
		// Create and call the controller
		URLMSController urlmsController = new URLMSController(urlms);
		boolean isValidUser = urlmsController.login(emailLoginTextField.getText(), passwordLoginTextField.getText());
		if(!isValidUser){
			// Unsuccessful login
			JOptionPane.showMessageDialog(this, "Invalid email address or password!", "Incorrect credentials", JOptionPane.WARNING_MESSAGE);
		}
		else {
			// Successful login
			JOptionPane.showMessageDialog(this, "Successful login as "+ emailLoginTextField.getText()+ ". Rest coming soon.", "Logged In", JOptionPane.INFORMATION_MESSAGE);
		}
		refreshData();
		}
	
	/**
	 * The layout of the UI. 
	 */
	private void initComponents() {
	    // Elements for login page
	    emailLoginTextField = new JTextField();
	    emailLoginLabel = new JLabel();
	    passwordLoginTextField = new JPasswordField();
	    passwordLoginLabel = new JLabel();
	    loginButton = new JButton();

	    // Global settings and listeners
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setTitle("Log in URLMS");

	    emailLoginLabel.setText("Email:");
	    passwordLoginLabel.setText("Password:");
	    loginButton.setText("Sign in");

	    // Layout
	    GroupLayout layout = new GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
	    
	    layout.setVerticalGroup(
		        layout.createSequentialGroup()
		        .addGroup(layout.createParallelGroup()
		    	        .addComponent(emailLoginLabel)
		    	        .addComponent(emailLoginTextField, 30, 30, 100))
		    	.addGroup(layout.createParallelGroup()
		    	    	.addComponent(passwordLoginLabel)
		    	    	.addGroup(layout.createSequentialGroup()
		    	    			.addComponent(passwordLoginTextField, 30,30, 100)
		    	    			.addComponent(loginButton)))
		        );
		    
	    layout.setHorizontalGroup(
	    	layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
	    	.addGroup(layout.createSequentialGroup()
	    			.addComponent(emailLoginLabel)
	    			.addComponent(emailLoginTextField, 200, 200, 400))
	    	.addGroup(layout.createSequentialGroup()
	    			.addComponent(passwordLoginLabel)
	    			.addGroup(layout.createParallelGroup()
	    					.addComponent(passwordLoginTextField, 200, 200, 400)
	    					.addComponent(loginButton)))
	        );
	    
	    layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {emailLoginLabel, passwordLoginLabel});
	    layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {loginButton, emailLoginTextField, passwordLoginTextField});

	    pack();
	    
	    // Action listener for login button
	    loginButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            loginButtonActionPerformed();
	        }
	    });
	}

}
