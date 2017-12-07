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

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.URLMS;
import javax.swing.JButton;

public class WeeklyProgressReportPage extends JFrame {
	/**
	 * ID of progress report user wishes to view
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
	 * Serial ID
	 */
	private static final long serialVersionUID = 521020024393179708L;
	/**
	 * Field that displays the title of the report
	 */
	private JTextField titleField;
	/**
	 * Actual content of report as written by staff
	 */
	private String reportContent;
	
	private String title;
	/**
	 * ID of progress report that user is viewing
	 */
	private int reportID;
	
	/**
	 * Constructor for WeeklyProgressReportPage which shows users the contents of the progress report they selected to view.
	 * @param urlms URLMS system
	 * @param currentLab Lab that user is currently viewing 
	 * @param urlmsCont URLMS controller
	 * @param reportContent  Actual content of report as written by staff
	 * @param reportID ID of the report user wishes to view
	 */
	public WeeklyProgressReportPage(URLMS urlms, Laboratory currentLab, URLMSController urlmsCont, String title, String reportContent) {
		
		setResizable(false);
		this.urlms = urlms;
		this.currentLab = currentLab;
		this.urlmsCont = urlmsCont;
		this.reportContent = reportContent;
		this.title = title;
		try {
	           for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	               if ("Nimbus".equals(info.getName())) {
	                   javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                   break;
	               }
	           }
	       } catch (ClassNotFoundException ex) {
	           java.util.logging.Logger.getLogger(WeeklyProgressReportPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (InstantiationException ex) {
	           java.util.logging.Logger.getLogger(WeeklyProgressReportPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (IllegalAccessException ex) {
	           java.util.logging.Logger.getLogger(WeeklyProgressReportPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	           java.util.logging.Logger.getLogger(WeeklyProgressReportPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       }
		
		initComponents();

	}

	private void initComponents() {
		JPanel headerPanel = new JPanel();
		
		JTextArea reportTxtArea = new JTextArea();
		reportTxtArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		reportTxtArea.setEditable(false);
		reportTxtArea.setBackground(new Color(216, 247, 255));
		reportTxtArea.setText(reportContent);
		
		JLabel lblTitle = new JLabel("Title / Date:");
		lblTitle.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		
		titleField = new JTextField();
		titleField.setEditable(false);
		titleField.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		titleField.setColumns(10);
		titleField.setBackground(new Color(216, 247, 255));
		titleField.setText(title);
		
		JLabel lblContent = new JLabel("Content:");
		lblContent.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		
		JButton backBtn = new JButton("Back");
		backBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		backBtn.setBackground(new Color(255, 255, 13));
		
		JButton logOutBtn = new JButton("Logout");
		logOutBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		logOutBtn.setBackground(Color.RED);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(headerPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(titleField, GroupLayout.PREFERRED_SIZE, 484, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(256, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblContent, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(651, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(reportTxtArea, GroupLayout.DEFAULT_SIZE, 863, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 523, Short.MAX_VALUE)
					.addComponent(logOutBtn, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(headerPanel, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(titleField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblContent, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(reportTxtArea, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(logOutBtn, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		headerPanel.setLayout(new BorderLayout(0, 0));
		headerPanel.setBackground(new Color(14, 96, 131));
		
		JLabel lblWeeklyProgressReport = new JLabel("Weekly Progress Report");
		lblWeeklyProgressReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeeklyProgressReport.setForeground(Color.WHITE);
		lblWeeklyProgressReport.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 28));
		headerPanel.add(lblWeeklyProgressReport, BorderLayout.CENTER);

		getContentPane().setLayout(groupLayout);
		getContentPane().setBackground(new Color(216, 247, 255));
		
		pack();
		setLocationRelativeTo(null);
		
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if(urlmsCont.getActiveUser() instanceof Director)
					new DirectorLabPage(urlms, currentLab, urlmsCont).setVisible(true);
				else
					new StaffLabPage(urlms, currentLab, urlmsCont).setVisible(true);
				dispose();
			}
		});
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				urlmsCont.logout();
				//setVisible(false);
				new LoginPage(urlms).setVisible(true);
				dispose();
			}
		});
	}

}
