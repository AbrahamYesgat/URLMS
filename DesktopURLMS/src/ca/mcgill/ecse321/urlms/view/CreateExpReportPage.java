package ca.mcgill.ecse321.urlms.view;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class CreateExpReportPage extends JFrame{
	/**
	 * Serial IDs
	 */
	private static final long serialVersionUID = -4170115196018744936L;
	/**
	 * Table showing all added expenses
	 */
	private JTable expTable;
	/**
	 * Field to enter balance of newly added expense
	 */
	private JTextField fieldAddAmt;
	/**
	 * Field to enter name of newly added expense
	 */
	private JTextField fieldAddExp;
	/**
	 * Field to enter name of previously added expense
	 */
	private JTextField fieldModExp;
	/**
	 * Field to enter new balance of previously added expense
	 */
	private JTextField fieldModAmt;
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JLabel lblAddExp = new JLabel("Add Expense: ");
		lblAddExp.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		
		fieldAddAmt = new JTextField();
		fieldAddAmt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldAddAmt.setColumns(10);
		
		fieldAddExp = new JTextField();
		fieldAddExp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldAddExp.setColumns(10);
		fieldAddExp.setBackground(Color.WHITE);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnAdd.setBackground(Color.BLUE);
		
		JLabel lblModifyExpense = new JLabel("Modify Expense:");
		lblModifyExpense.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		
		fieldModExp = new JTextField();
		fieldModExp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldModExp.setColumns(10);
		fieldModExp.setBackground(Color.WHITE);
		
		fieldModAmt = new JTextField();
		fieldModAmt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldModAmt.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnUpdate.setBackground(Color.GREEN);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setForeground(Color.WHITE);
		btnRemove.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnRemove.setBackground(Color.RED);
		
		JLabel lbl = new JLabel("Total Balance:");
		lbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		
		fieldTotal = new JTextField();
		fieldTotal.setEditable(false);
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
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(headerPanel, GroupLayout.DEFAULT_SIZE, 905, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 905, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAddExp, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fieldAddExp, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(fieldAddAmt, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblModifyExpense, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(fieldTotal, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(fieldModExp, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(fieldModAmt, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))))
					.addGap(106)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 467, Short.MAX_VALUE)
					.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(headerPanel, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddExp, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldAddExp, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldAddAmt, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblModifyExpense, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldModExp, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldModAmt, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lbl, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(fieldTotal, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		expTable = new JTable();
		expTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Expense", "Amount"
			})
		{public boolean isCellEditable(int row, int column){return false;}}//This causes all cells to be not editable)
		);
		expTable.setShowGrid(true);
		expTable.setRowHeight(40);
		expTable.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		expTable.setCellSelectionEnabled(true);
		expTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		JTableHeader expHeader = expTable.getTableHeader();
		expHeader.setFont(new java.awt.Font("Lucida Grande", 1, 18));
		scrollPane.setViewportView(expTable);
		scrollPane.getViewport().setBackground(new Color(216, 247, 255));

		
		JLabel headerLabel = new JLabel("Create Expense Report");
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabel.setForeground(Color.WHITE);
		headerLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 28));
		headerPanel.add(headerLabel, BorderLayout.CENTER);
		getContentPane().setLayout(groupLayout);
	    getContentPane().setBackground(new Color(216, 247, 255));
	    PromptSupport.setPrompt("Expense Name", fieldAddExp);
	    PromptSupport.setPrompt("Balance", fieldAddAmt);
	    PromptSupport.setPrompt("Expense Name", fieldModExp);
	    PromptSupport.setPrompt("Balance", fieldModAmt);
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
	    
	    btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addExpense();
			}
		});
	}
	/*
	 * Method used to dynamically add expense to expense table
	 */
	private void addExpense(){
		
	}

}
