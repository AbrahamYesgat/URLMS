package ca.mcgill.ecse321.urlms.view;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Calendar;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.URLMS;
import javax.swing.JButton;

public class CreateProgReportPage extends JFrame {
	/**
	 * ID of progress report user wishes to create
	 */
	private JTextField idField;
	/**
	 * URLMS system
	 */
	private URLMS urlms;
	/**
	 * URLMS controller
	 */
	private URLMSController urlmsCont;
	/**
	 * Lab that user has currently clicked on
	 */
	private Laboratory currentLab;
	/**
	 * Field that displays the title of the report
	 */
	private JTextField titleField;
	/**
	 * Actual content of report as written by staff
	 */
	private String reportContent;
	/**
	 * ID of progress report that user is creating
	 */
	
	private JTextArea reportTxtArea;
	
	private int reportID;
	
	private Calendar cal;
	
	/**
	 * Constructor for WeeklyProgressReportPage which allows staff members to create progress report.
	 * @param urlms URLMS system
	 * @param currentLab Lab that user is currently creating 
	 * @param urlmsCont URLMS controller
	 * @param reportContent  Actual content of report as written by staff
	 * @param reportID ID of the report user wishes to create
	 */
	public CreateProgReportPage(URLMS urlms, Laboratory currentLab, URLMSController urlmsCont) {
		
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
	           java.util.logging.Logger.getLogger(CreateProgReportPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (InstantiationException ex) {
	           java.util.logging.Logger.getLogger(CreateProgReportPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (IllegalAccessException ex) {
	           java.util.logging.Logger.getLogger(CreateProgReportPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	           java.util.logging.Logger.getLogger(CreateProgReportPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       }
		
		initComponents();

	}
	/**
	 * Method used to initialise frame for creating progress report
	 */
	private void initComponents() {
		JPanel headerPanel = new JPanel();
		
		reportTxtArea = new JTextArea();
		reportTxtArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		
		titleField = new JTextField();
		titleField.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		titleField.setColumns(10);
		
		JLabel lblContent = new JLabel("Content:");
		lblContent.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		
		JButton backBtn = new JButton("Back");
		backBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		backBtn.setBackground(new Color(255, 255, 13));
		
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		logoutBtn.setBackground(Color.RED);
		
		JButton saveBtn = new JButton("Save Report");
		saveBtn.setForeground(Color.WHITE);
		saveBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		saveBtn.setBackground(new Color(14, 96, 131));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(headerPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(titleField, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(524, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(reportTxtArea, GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addGap(112)
					.addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
					.addComponent(logoutBtn, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblContent, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(619, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(headerPanel, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(titleField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addComponent(lblContent, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(reportTxtArea, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(saveBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(backBtn, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(logoutBtn, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
					.addContainerGap())
		);
		headerPanel.setLayout(new BorderLayout(0, 0));
		headerPanel.setBackground(new Color(14, 96, 131));
		
		JLabel lblWeeklyProgressReport = new JLabel("Create Weekly Progress Report");
		lblWeeklyProgressReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeeklyProgressReport.setForeground(Color.WHITE);
		lblWeeklyProgressReport.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 28));
		headerPanel.add(lblWeeklyProgressReport, BorderLayout.CENTER);

		getContentPane().setLayout(groupLayout);
		getContentPane().setBackground(new Color(216, 247, 255));
		
		pack();
		setLocationRelativeTo(null);
		
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new StaffLabPage(urlms, currentLab, urlmsCont).setVisible(true);
				//setVisible(false);
				saveNewReport();
			}
		});
		
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StaffLabPage(urlms, currentLab, urlmsCont).setVisible(true);
				setVisible(false);
			}
		});
		
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				urlmsCont.logout();
				setVisible(false);
				new LoginPage(urlms).setVisible(true);
			}
		});
		
		
	}
	private void saveNewReport() {
		
		
		java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	
	
		if(titleField.getText().equals("") || reportTxtArea.getText().equals(""))
			JOptionPane.showMessageDialog(this, "Please fill out all feilds, or else report cannot be saved", "Error", JOptionPane.ERROR_MESSAGE);
		else if(urlmsCont.createWeeklyProgressReport(titleField.getText(), reportTxtArea.getText(), sqlDate))
			JOptionPane.showMessageDialog(this, "Your progress report was sucesfully saved");
		else
			JOptionPane.showMessageDialog(this, "User is not a staff", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	
	

}
