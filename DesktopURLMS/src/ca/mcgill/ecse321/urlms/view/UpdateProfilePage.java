package ca.mcgill.ecse321.urlms.view;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.URLMS;

import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class UpdateProfilePage extends JFrame {
	/**
	 *  Serial ID
	 */
	private static final long serialVersionUID = -5942963500936014767L;
	/**
	 * URLMS system
	 */
	private URLMS urlms;
	/**
	 * URLMS controller
	 */
	private URLMSController urlmsCont;
	/**
	 * Lab user has currently clicked on
	 */
	private Laboratory currentLab;
	/**
	 * Name field to edit
	 */
	private JTextField nameField;
	/**
	 * Email field to edit
	 */
	private JTextField emailField;
	/**
	 * Password field to enter new password
	 */
	private JPasswordField passwordField;
	/**
	 * Constructor of update profile page
	 * @param urlms urlms system
	 * @param currentLab lab user is currently viewing
	 * @param urlmsCont urlms controller
	 */
	public UpdateProfilePage(URLMS urlms, Laboratory currentLab, URLMSController urlmsCont) {
		setResizable(false);
		this.urlms = urlms;
		this.currentLab = currentLab;
		this.urlmsCont = urlmsCont;
		try {
	           for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	               if ("Nimbus".equals(info.getName())) {
	                   javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                   break;
	               }
	           }
	       } catch (ClassNotFoundException ex) {
	           java.util.logging.Logger.getLogger(UpdateProfilePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (InstantiationException ex) {
	           java.util.logging.Logger.getLogger(UpdateProfilePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (IllegalAccessException ex) {
	           java.util.logging.Logger.getLogger(UpdateProfilePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	           java.util.logging.Logger.getLogger(UpdateProfilePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       }
		initComponents();
	}
	
	private void initComponents(){
		this.setTitle("Update Profile Page");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(14, 96, 131));
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		
		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		
		emailField = new JTextField();
		emailField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		emailField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton saveBtn = new JButton("Save Changes");
		saveBtn.setForeground(Color.WHITE);
		saveBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		saveBtn.setBackground(new Color(14, 96, 131));
		
		JButton backBtn = new JButton("Back");
		backBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		backBtn.setBackground(new Color(255, 255, 13));
		
		JButton logOutBtn = new JButton("Logout");
		logOutBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		logOutBtn.setBackground(Color.RED);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(740, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(601, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(740, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(740, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(saveBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(backBtn, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED, 496, Short.MAX_VALUE)
									.addComponent(logOutBtn, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(passwordField, Alignment.LEADING)
									.addComponent(emailField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
							.addContainerGap())))
				.addComponent(headerPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(headerPanel, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(emailField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(saveBtn)
					.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(logOutBtn, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		headerPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel updateProfLabel = new JLabel("Update Profile  ");
		updateProfLabel.setForeground(Color.WHITE);
		updateProfLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 26));
		headerPanel.add(updateProfLabel, BorderLayout.WEST);
		
		JLabel imgLbl = new JLabel("");
		imgLbl.setBackground(new Color(14,96,31));
		imgLbl.setIcon(new ImageIcon(UpdateProfilePage.class.getResource("/ca/mcgill/ecse321/urlms/viewimages/profile.png")));
		headerPanel.add(imgLbl, BorderLayout.CENTER);
		getContentPane().setLayout(groupLayout);
	    getContentPane().setBackground(new Color(216, 247, 255));
	    
	    pack();
		// makes window appear in center of screen
		this.setLocationRelativeTo(null);
		
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(urlmsCont.getActiveUser() instanceof Director){
					new DirectorLabPage(urlms, currentLab, urlmsCont).setVisible(true);
				}
				else
					new StaffLabPage(urlms, currentLab, urlmsCont).setVisible(true);
				setVisible(false);
			}
		});
	}
}
