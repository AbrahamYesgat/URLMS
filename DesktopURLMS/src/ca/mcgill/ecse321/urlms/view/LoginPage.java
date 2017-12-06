package ca.mcgill.ecse321.urlms.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

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
	private JButton registerButton;
	private URLMS urlms;
	private List<Director> dirs;
	private URLMSController urlmsCont;
	/**
	 * Content pane of frame
	 */
	private JPanel contentPane;
	/**
	 * Textfield where new user enters email
	 */
	private JTextField fieldEmail;
	/**
	 * Textfield where new user enters password
	 */
	private JPasswordField passwordField;
	
	/**
	 * "constructor" for the UI.
	 * @param urlms Gets the instance of the system. 
	 */
	public LoginPage(URLMS urlms) {
		this.urlms = urlms;
		urlmsCont = new URLMSController(urlms);
		setResizable(false);
		 try {
	           for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	               if ("Nimbus".equals(info.getName())) {
	                   javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                   break;
	               }
	           }
	       } catch (ClassNotFoundException ex) {
	           java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (InstantiationException ex) {
	           java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (IllegalAccessException ex) {
	           java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	           java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       }
		// initialize Login Page
	    initComponents();
	}
	
	/**
	 * Clears the text fields based on the button click response.
	 */
	private void refreshData() {
	    fieldEmail.setText("");
	    passwordField.setText("");
	}
	
	/**
	 *  This is performed when the user clicks on the login button.
	 *  Calls the login method from the controller. 
	 *  Updates the UI accordingly to the result of the login method. 
	 */
	private void loginButtonActionPerformed() {
		boolean isValidUser = urlmsCont.login(fieldEmail.getText(), String.copyValueOf(passwordField.getPassword()));
		if(!isValidUser){
			// Unsuccessful login
			JOptionPane.showMessageDialog(this, "Invalid email address or password!", "Incorrect credentials", JOptionPane.WARNING_MESSAGE);
			refreshData();
		}
		else {
			// Successful login
			LabSelectionPage labSelection = new LabSelectionPage(urlms, fieldEmail.getText(), urlmsCont);
			labSelection.setVisible(true);
			this.setVisible(false);
		}
	}
	
	/**
	 * The layout of the UI. 
	 */
	private void initComponents() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Login Page");
		setBounds(100, 100, 891, 572);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 405, 533);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblBGImg = new JLabel(""); // background image contained within this label
		lblBGImg.setIcon(new ImageIcon(LoginPage.class.getResource("/ca/mcgill/ecse321/urlms/viewimages/signupBG.png")));
		lblBGImg.setBounds(102, 40, 303, 316);
		panel.add(lblBGImg);
		
		JLabel lblUndergraduateResearchAnd = new JLabel("Undergraduate Research and Lab");
		lblUndergraduateResearchAnd.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblUndergraduateResearchAnd.setForeground(Color.WHITE);
		lblUndergraduateResearchAnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblUndergraduateResearchAnd.setBounds(0, 370, 405, 52);
		panel.add(lblUndergraduateResearchAnd);
		
		JLabel lblManagementSystem = new JLabel("Management System");
		lblManagementSystem.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		lblManagementSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblManagementSystem.setForeground(Color.WHITE);
		lblManagementSystem.setBounds(25, 402, 375, 52);
		panel.add(lblManagementSystem);
		
		Button btnSignUp = new Button("Sign Up");
		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setBackground(new Color(241, 57, 83));
		btnSignUp.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		btnSignUp.setBounds(441, 468, 189, 55);
		contentPane.add(btnSignUp);
		
		fieldEmail = new JTextField();
		fieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldEmail.setColumns(10);
		fieldEmail.setBounds(441, 111, 405, 55);
		contentPane.add(fieldEmail);
		
		JSeparator separateEmail = new JSeparator();
		separateEmail.setBounds(441, 121, 405, 2);
		contentPane.add(separateEmail);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblEmail.setBounds(441, 67, 157, 35);
		contentPane.add(lblEmail);
		
		JSeparator separatePass = new JSeparator();
		separatePass.setBounds(441, 308, 405, 2);
		contentPane.add(separatePass);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblPassword.setBounds(441, 204, 157, 35);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBounds(441, 255, 405, 55);
		contentPane.add(passwordField);
		
		Button btnLogIn = new Button("Log in");
		btnLogIn.setForeground(Color.WHITE);
		btnLogIn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		btnLogIn.setBackground(new Color(93, 112, 230));
		btnLogIn.setBounds(650, 468, 195, 55);
		contentPane.add(btnLogIn);
		
		// makes window appear in center of screen
		this.setLocationRelativeTo(null);
	    
	    // Action listener for login button
	    btnLogIn.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	java.util.Calendar sqlDate = java.util.Calendar.getInstance();
	        	java.util.Date utilDate = sqlDate.getTime();
	        	loginButtonActionPerformed();
	        }
	    });
	    
	    btnSignUp.addActionListener(new ActionListener(){
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	RegisterPage registerPage = new RegisterPage(urlms, urlmsCont);
	        	registerPage.setVisible(true);
	        	setVisible(false);
	        }
	    });
	}

}
