package ca.mcgill.ecse321.urlms.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.URLMS;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class SelectProgressRepPage extends JFrame{
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 2088529096348585529L;
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
	 * Constructor for SelectProgressRepPage which allows user to select the progress report they wish to view
	 * @param urlms URLMS system
	 * @param currentLab Lab that user is currently viewing 
	 * @param urlmsCont URLMS controller
	 */
	public SelectProgressRepPage(URLMS urlms, Laboratory currentLab, URLMSController urlmsCont) {
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
	           java.util.logging.Logger.getLogger(SelectProgressRepPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (InstantiationException ex) {
	           java.util.logging.Logger.getLogger(SelectProgressRepPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (IllegalAccessException ex) {
	           java.util.logging.Logger.getLogger(SelectProgressRepPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	           java.util.logging.Logger.getLogger(SelectProgressRepPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       }
		initComponents();
	}
	/**
	 * Method used to initialise frame of SelectProgressRetPage
	 */
	private void initComponents(){
		this.setTitle("Select Progress Report");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(14, 96, 131));
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(14, 96, 131));
		
		JButton btnViewReport = new JButton("View Report");
		btnViewReport.setForeground(Color.WHITE);
		btnViewReport.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		btnViewReport.setBackground(new Color(14, 96, 131));
		
		JButton backBtn = new JButton("Back");
		backBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		backBtn.setBackground(new Color(255, 255, 13));
		
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		logoutBtn.setBackground(Color.RED);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(headerPanel, GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 452, Short.MAX_VALUE)
					.addComponent(logoutBtn, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(269)
					.addComponent(btnViewReport, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(336, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(123)
					.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 531, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(168, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(headerPanel, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addGap(88)
					.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnViewReport, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(logoutBtn, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblP = new JLabel("Please enter the ID of the report you wish to view:");
		lblP.setHorizontalAlignment(SwingConstants.CENTER);
		lblP.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblP.setForeground(Color.WHITE);
		centerPanel.add(lblP, BorderLayout.NORTH);
		
		idField = new JTextField();
		idField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		idField.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(idField, BorderLayout.CENTER);
		idField.setColumns(10);
		headerPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Weekly Progress Reports");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 26));
		headerPanel.add(lblNewLabel, BorderLayout.CENTER);
		
		getContentPane().setLayout(groupLayout);
		getContentPane().setBackground(new Color(216, 247, 255));
		
		pack();
		setLocationRelativeTo(null);
		
		btnViewReport.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				viewProgReport();
			}
		});
	}
	
	/**
	 * Method responsible for fetching progress report based on ID number of report inputted by user
	 */
	private void viewProgReport() {
		if(idField.getText().isEmpty()){
			JOptionPane.showMessageDialog(this, "ID number cannot be left empty!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (!(idField.getText().matches("[0-9]+"))) {
			JOptionPane.showMessageDialog(this, "ID must only contain numbers!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if(urlmsCont.viewWeeklyProgressReport(Integer.parseInt(idField.getText())).equals("Requested Weekly Progress Report cannot be found!")){
			JOptionPane.showMessageDialog(this, "No report associated with this ID!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else{
			String reportContent = urlmsCont.viewWeeklyProgressReport(Integer.parseInt(idField.getText()));
			new WeeklyProgressReportPage(urlms, currentLab, urlmsCont, Integer.parseInt(idField.getText()), reportContent).setVisible(true);
			this.setVisible(false);
		}
		
	}

}
