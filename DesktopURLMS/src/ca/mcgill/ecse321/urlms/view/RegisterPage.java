package ca.mcgill.ecse321.urlms.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.URLMS;

import java.awt.Color;
import java.awt.Button;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;

public class RegisterPage extends JFrame {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 6896468319894140310L;
	/**
	 * Content pane of frame
	 */
	private JPanel contentPane;
	/**
	 * Textfield where new user enters name
	 */
	private JTextField fieldName;
	/**
	 * Textfield where new user enters email
	 */
	private JTextField fieldEmail;
	/**
	 * Textfield where new user enters password
	 */
	private JPasswordField passwordField;
	/**
	 * Textfield where new user repeats password
	 */
	private JPasswordField repPasswordField;
	/**
	 * URLMS system
	 */
	private URLMS urlms;
	/**
	 * URLMS Controller
	 */
	private URLMSController urlmsCont;
	
	/**
	 * "constructor" for the UI.
	 * @param urlms Gets the instance of the system. 
	 * @param urlmsCont 
	 */
	public RegisterPage(URLMS urlms, URLMSController urlmsCont) {
		this.urlms = urlms;
		this.urlmsCont = urlmsCont;
		setResizable(false);
		 try {
	           for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	               if ("Nimbus".equals(info.getName())) {
	                   javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                   break;
	               }
	           }
	       } catch (ClassNotFoundException ex) {
	           java.util.logging.Logger.getLogger(RegisterPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (InstantiationException ex) {
	           java.util.logging.Logger.getLogger(RegisterPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (IllegalAccessException ex) {
	           java.util.logging.Logger.getLogger(RegisterPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	           java.util.logging.Logger.getLogger(RegisterPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       }
		// initialize Register Page
	    initComponents();
	}

	/**
	 * Create the RegisterPAge frame.
	 */
	private void initComponents() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Register Page");
		setBounds(100, 100, 898, 589);
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
		lblBGImg.setIcon(new ImageIcon(RegisterPage.class.getResource("/ca/mcgill/ecse321/urlms/viewimages/signupBG.png")));
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
		fieldName = new JTextField();
		fieldName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldName.setBounds(441, 58, 405, 55);
		contentPane.add(fieldName);
		fieldName.setColumns(10);
		
		JSeparator separateName = new JSeparator();
		separateName.setBounds(441, 111, 405, 2);
		contentPane.add(separateName);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblName.setBounds(442, 16, 157, 35);
		contentPane.add(lblName);
		
		fieldEmail = new JTextField();
		fieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldEmail.setColumns(10);
		fieldEmail.setBounds(441, 171, 405, 55);
		contentPane.add(fieldEmail);
		
		JSeparator separateEmail = new JSeparator();
		separateEmail.setBounds(441, 224, 405, 2);
		contentPane.add(separateEmail);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblEmail.setBounds(441, 129, 157, 35);
		contentPane.add(lblEmail);
		
		JSeparator separatePass = new JSeparator();
		separatePass.setBounds(441, 337, 405, 2);
		contentPane.add(separatePass);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblPassword.setBounds(441, 242, 157, 35);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBounds(441, 284, 405, 55);
		contentPane.add(passwordField);
		
		JLabel lblRepeatPassword = new JLabel("Repeat Password");
		lblRepeatPassword.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblRepeatPassword.setBounds(441, 355, 220, 26);
		contentPane.add(lblRepeatPassword);
		
		repPasswordField = new JPasswordField();
		repPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		repPasswordField.setBounds(441, 394, 405, 55);
		contentPane.add(repPasswordField);
		
		Button btnBack = new Button("Back");
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		btnBack.setBackground(Color.YELLOW);
		btnBack.setBounds(650, 468, 195, 55);
		contentPane.add(btnBack);
		
		// makes window appear in center of screen
		this.setLocationRelativeTo(null);
		
		// sign up button action listener
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}
}
