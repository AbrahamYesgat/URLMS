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

// UI for the login page
public class LoginPage extends JFrame{
	private static final long serialVersionUID = 7324018789116682077L;
	private JTextField emailLoginTextField;
	private JLabel emailLoginLabel;
	private JTextField passwordLoginTextField;
	private JLabel passwordLoginLabel;
	private JButton loginButton;
	private URLMS urlms;
	private List<Director> dirs;
	
	// Constructor for login page
	public LoginPage(URLMS urlms) {
		this.urlms = urlms;
	    initComponents();
	}
	
	// Clears input when page is refreshed
	private void refreshData() {
	    emailLoginTextField.setText("");
	    passwordLoginTextField.setText("");
	    pack();
	}
	
	// The is performed when the user clicks on the login button
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
	
	//Initializes the display
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
