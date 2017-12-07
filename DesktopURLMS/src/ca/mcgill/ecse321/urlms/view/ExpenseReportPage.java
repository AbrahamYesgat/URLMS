package ca.mcgill.ecse321.urlms.view;

import java.util.List;

import javax.swing.JFrame;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.ExpenseReport;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.URLMS;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class ExpenseReportPage extends JFrame{
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 3299329176266880458L;
	/**
	 * URLMS system
	 */
	private URLMS urlms;
	/**
	 * URLMS controller
	 */
	private URLMSController urlmsCont;
	/**
	 * Lab whose expense reports director is viewing
	 */
	private Laboratory currentLab;
	/**
	 * List containing all expense of lab
	 */
	private List<ExpenseReport> expensesList;
	/**
	 * Table containing all expenses
	 */
	private JTable expTable;
	/**
	 * Text area responsible for displlaying selected expense that director wants to view in UI.
	 */
	private JTextArea selectedExp;
	
	/**
	 *Constructor for page to view all expenses related to a lab.
	 * @param urlms URLMS system
	 * @param currentLab Lab whose expense reports director is viewing
	 * @param urlmsCont URLMS controller
	 */
	public ExpenseReportPage(URLMS urlms, Laboratory currentLab, URLMSController urlmsCont) {
		try {
	           for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	               if ("Nimbus".equals(info.getName())) {
	                   javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                   break;
	               }
	           }
	       } catch (ClassNotFoundException ex) {
	           java.util.logging.Logger.getLogger(ExpenseReportPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (InstantiationException ex) {
	           java.util.logging.Logger.getLogger(ExpenseReportPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (IllegalAccessException ex) {
	           java.util.logging.Logger.getLogger(ExpenseReportPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	           java.util.logging.Logger.getLogger(ExpenseReportPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       }
		this.urlms = urlms;
		this.urlmsCont = urlmsCont;
		this.currentLab = currentLab;
		if(urlmsCont.getActiveLaboratory().hasExpenseReports()){
			expensesList = urlmsCont.getActiveLaboratory().getExpenseReports();
		}
		initComponents();
	}
	/**
	 * Method used to intialise table containing all expenses for a labs
	 */
	private void initialiseTable(){
		if(expensesList != null && expensesList.size() > 0){
			String reportArray[] = new String[expensesList.size()];
			int i = 0;
			for(ExpenseReport report : expensesList){
				reportArray[i] = "Expense report " + Integer.toString(i + 1);
				Object[] o = new Object[2];
				o[0] = reportArray[i];
				o[1] = report.getAmount();
				((DefaultTableModel) expTable.getModel()).addRow(o);
				i++;
			}
		}
	}
	/**
	 * Method used to initialise frame for ExpenseReportPage
	 */
	private void initComponents() {
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(14, 96, 131));
		headerPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnBack.setBackground(new Color(255, 255, 13));
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnLogout.setBackground(Color.RED);
		
		selectedExp = new JTextArea();
		selectedExp.setEditable(false);
		
		JLabel lblSelectedRep = new JLabel("Contents of Selected Report:");
		lblSelectedRep.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 338, Short.MAX_VALUE)
					.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
				.addComponent(headerPanel, GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(selectedExp, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(327, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSelectedRep)
					.addContainerGap(693, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(headerPanel, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblSelectedRep)
					.addGap(18)
					.addComponent(selectedExp, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		expTable = new JTable();
		expTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Expense", "Balance"
			}
		)
		{public boolean isCellEditable(int row, int column){return false;}}//This causes all cells to be not editable)
		);
		expTable.setShowGrid(true);
		expTable.setRowHeight(40);
		expTable.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		expTable.setCellSelectionEnabled(true);
		expTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(expTable);
		JTableHeader expHeader = expTable.getTableHeader();
		expHeader.setFont(new java.awt.Font("Lucida Grande", 1, 18));
		initialiseTable();
		JLabel lblExpRep = new JLabel("Expense Report");
		lblExpRep.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpRep.setForeground(Color.WHITE);
		lblExpRep.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 27));
		headerPanel.add(lblExpRep, BorderLayout.CENTER);
		getContentPane().setLayout(groupLayout);
	    getContentPane().setBackground(new Color(216, 247, 255));
		scrollPane.getViewport().setBackground(new Color(216, 247, 255));
		
		pack();
		setLocationRelativeTo(null);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DirectorLabPage(urlms, urlmsCont.getActiveLaboratory(), urlmsCont).setVisible(true);
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
		
		expTable.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
			    if (e.getClickCount() == 2) {
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();
			      selectedExp.setText(expensesList.get(row).getExpense());
			     }
			  }
			});
	}
}
