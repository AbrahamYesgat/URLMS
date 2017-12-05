package ca.mcgill.ecse321.urlms.view;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.URLMS;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

public class EditLabPage extends JFrame {
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 8559244263535506364L;
	/**
	 * URLMS system
	 */
	private URLMS urlms;
	/**
	 * Current lab that director is editing
	 */
	private Laboratory currentLab;
	/**
	 * URLMS Controller
	 */
	private URLMSController urlmsCont;
	/**
	 * Field containing updated lab name
	 */
	private JTextField nameField;
	
	/**
	 * Constructor of EditLabPage frame
	 * @param urlms current URLMS system
	 * @param lab current lab user is viewing
	 */
	public EditLabPage(URLMS urlms, Laboratory lab, URLMSController urlmsCont) {
		setResizable(false);
		this.urlms = urlms;
		this.currentLab = lab;
		this.urlmsCont = urlmsCont;
		
		try {
	           for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	               if ("Nimbus".equals(info.getName())) {
	                   javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                   break;
	               }
	           }
	       } catch (ClassNotFoundException ex) {
	           java.util.logging.Logger.getLogger(EditLabPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (InstantiationException ex) {
	           java.util.logging.Logger.getLogger(EditLabPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (IllegalAccessException ex) {
	           java.util.logging.Logger.getLogger(EditLabPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	           java.util.logging.Logger.getLogger(EditLabPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       }
		initComponents();
	}

	private void initComponents() {
		this.setBackground(Color.WHITE);
		this.setTitle("University Lab Management System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setBackground(new Color(216, 247, 255));
		JLabel lblEditLab = new JLabel("Edit Lab");
		lblEditLab.setFont(new Font("Segoe UI Semibold", Font.BOLD, 25));
		
		nameField = new JTextField();
		nameField.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		nameField.setColumns(10);
		PromptSupport.setPrompt(currentLab.getName(), nameField);
		JLabel lblActiveLab = new JLabel("Active Lab?");
		lblActiveLab.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		
		JLabel lblLabName = new JLabel("Lab Name");
		lblLabName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		
		JSeparator titleSeparator = new JSeparator();
		
		JCheckBox chckbxYes = new JCheckBox("Yes");
		chckbxYes.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		
		JCheckBox chckbxNo = new JCheckBox("No");
		chckbxNo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		
		chckbxNo.getModel().isPressed();
		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		
		JButton backBtn = new JButton("Back");
		backBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
	    backBtn.setBackground(new Color(255, 255, 13));
		
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		logoutBtn.setBackground(Color.RED);
		
		JDateChooser dateChooser = new JDateChooser();
		
		JButton saveBtn = new JButton("Save Changes");
		saveBtn.setForeground(Color.WHITE);
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		saveBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		saveBtn.setBackground(new Color(23, 52, 240));
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(titleSeparator, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblEditLab, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(597, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblActiveLab, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxYes)
							.addGap(34)
							.addComponent(chckbxNo, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblLabName, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(575, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 412, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblStartDate, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
										.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
									.addGap(144)))
							.addGap(41)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(logoutBtn, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEditLab, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(titleSeparator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(26)
							.addComponent(lblLabName, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblActiveLab, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addComponent(chckbxYes)
								.addComponent(chckbxNo, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
							.addGap(42)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblStartDate, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(logoutBtn, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				urlmsCont.logout();
				
				new LoginPage(urlms).setVisible(true);
				dispose();
			}
		});
		
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if(urlmsCont.getActiveUser() instanceof Director) {
					new DirectorLabPage(urlms, currentLab, urlmsCont).setVisible(true);
					dispose();}
				else
					new StaffLabPage(urlms, currentLab, urlmsCont).setVisible(true);
					dispose();
			}
		});
		
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				java.sql.Date date = currentLab.getStartDate();;
				Boolean active = currentLab.getActive();;
				String labName = currentLab.getName();;
				int i = 0;
				
				String s = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				if(s.equals("")) {
					date = currentLab.getStartDate();
				}
				else {
					date = new java.sql.Date(dateChooser.getDate().getTime());
				}
				
				if(chckbxNo.isSelected() && chckbxYes.isSelected()) {
					chckbxNo.setSelected(false);
					chckbxYes.setSelected(false);
					activeLabError();
					i = 1;
				}
				else if(chckbxYes.isSelected())
					active = true;
				else if(chckbxNo.isSelected())
					active = false;
				else
					active = currentLab.getActive();
				
				
				if(nameField.getText().equals(""))
					labName = currentLab.getName();
				else
					labName = nameField.getText();
				
				
				if(urlmsCont.updateLab(labName, date, active) && i == 0)
					labSucess();
				else if(i ==1)
					i = 0;
				else
					labError();

			}
		});
	
		pack();
		this.setLocationRelativeTo(null);
	}
	
	private void activeLabError() {
		JOptionPane.showMessageDialog(this, "You are only allowed to check one box", "Error", JOptionPane.ERROR_MESSAGE);
	}
	private void labSucess() {
		JOptionPane.showMessageDialog(this, "Lab information was updated!");
	}
	private void labError() {
		JOptionPane.showMessageDialog(this, "Lab name entered already exists, please use another one", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
}
