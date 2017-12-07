package ca.mcgill.ecse321.urlms.view;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdesktop.swingx.prompt.PromptSupport;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.URLMS;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class CreateExpReportPage extends JFrame{
	/**
	 * Serial IDs
	 */
	private static final long serialVersionUID = -4170115196018744936L;
	/**
	 * Field displaying total expenditure
	 */
	private JTextField fieldTotal;
	/**
	 * URLMS system
	 */
	private URLMS urlms;
	/**
	 * Instance of URLMS controller
	 */
	private URLMSController urlmsCont;
	/**
	 * Current lab whose expense report director is creating
	 */
	private Laboratory currentLab;
	/**
	 * Field to enter description of expenses
	 */
	JTextArea expField;
	/**
	 * Constructor for frame to create expense report (performed by a director)
	 * @param urlms URLMS system
	 * @param currentLab Lab that director is creating an expense report for
	 * @param urlmsCont URLMS Controller
	 */
	public CreateExpReportPage(URLMS urlms, Laboratory currentLab, URLMSController urlmsCont) {
		this.urlms = urlms;
		this.urlmsCont = urlmsCont;
		this.currentLab = currentLab;
		setResizable(false);
		
		try {
	           for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	               if ("Nimbus".equals(info.getName())) {
	                   javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                   break;
	               }
	           }
	       } catch (ClassNotFoundException ex) {
	           java.util.logging.Logger.getLogger(CreateExpReportPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (InstantiationException ex) {
	           java.util.logging.Logger.getLogger(CreateExpReportPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (IllegalAccessException ex) {
	           java.util.logging.Logger.getLogger(CreateExpReportPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	           java.util.logging.Logger.getLogger(CreateExpReportPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       }
		initComponents();
	}

	private void initComponents() {
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(14, 96, 131));
		headerPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lbl = new JLabel("Total Balance:");
		lbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		
		fieldTotal = new JTextField();
		fieldTotal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldTotal.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnSave.setBackground(Color.BLUE);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnBack.setBackground(new Color(255, 255, 13));
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnLogout.setBackground(Color.RED);
		
		JLabel lblExp = new JLabel("Expense Description:");
		lblExp.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		
		expField = new JTextArea();
		expField.setFont(new Font("Monospaced", Font.PLAIN, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblExp, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
						.addComponent(expField, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lbl, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fieldTotal, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 386, Short.MAX_VALUE)
							.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addComponent(headerPanel, GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(headerPanel, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblExp, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(expField, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldTotal, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addGap(31))
		);

		
		JLabel headerLabel = new JLabel("Add Expense");
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabel.setForeground(Color.WHITE);
		headerLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 28));
		headerPanel.add(headerLabel, BorderLayout.CENTER);
		getContentPane().setLayout(groupLayout);
	    getContentPane().setBackground(new Color(216, 247, 255));
	    pack();
	    setLocationRelativeTo(null);
	    
	    btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DirectorLabPage(urlms, currentLab, urlmsCont).setVisible(true);
				setVisible(false);
			}
		});
	    
	    btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				urlmsCont.logout();
				new LoginPage(urlms).setVisible(true);
				setVisible(false);
			}
		});
	    
	    btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				saveReport();
			}
		});
	}
	
	private void saveReport(){
		if(expField.getText().isEmpty() || !(expField.getText().matches(".*\\d+.*") || expField.getText().matches(".*[a-zA-Z].*") )){
			JOptionPane.showMessageDialog(this, "Please enter a valid description!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if(fieldTotal.getText().isEmpty() || !(fieldTotal.getText().matches("[0-9.]+"))){
			JOptionPane.showMessageDialog(this, "Please enter a valid balance!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else{
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			urlmsCont.createExpenseReport(expField.getText(), Double.parseDouble(fieldTotal.getText()), day, month, year);
			JOptionPane.showMessageDialog(this, "Expense added!");
		}
	}
}
