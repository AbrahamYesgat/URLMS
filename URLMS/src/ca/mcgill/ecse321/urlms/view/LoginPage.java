package ca.mcgill.ecse321.urlms.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.urlms.model.URLMS;

public class LoginPage extends JFrame{
	private static final long serialVersionUID = 7324018789116682077L;
	private JTextField emailLoginTextField;
	private JLabel emailLoginLabel;
	private JTextField passwordLoginTextField;
	private JLabel passwordLoginLabel;
	private JButton loginButton;
	private URLMS urlms;
	
	//Constructor for login page
	public LoginPage(URLMS urlms) {
		this.urlms = urlms;
	    initComponents();
	}
	
	//Clears input when page is refreshed
	private void refreshData() {
	    emailLoginTextField.setText("");
	    passwordLoginTextField.setText("");
	    pack();
	}
	
	//Initializes the display
	private void initComponents() {
	    // elements for login page
	    emailLoginTextField = new JTextField();
	    emailLoginLabel = new JLabel();
	    passwordLoginTextField = new JPasswordField();
	    passwordLoginLabel = new JLabel();
	    loginButton = new JButton();

	    // global settings and listeners
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setTitle("Log in URLMS");

	    emailLoginLabel.setText("Email:");
	    passwordLoginLabel.setText("Password:");
	    loginButton.setText("Sign in");

	    // layout
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
	}

}
