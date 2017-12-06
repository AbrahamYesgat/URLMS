package ca.mcgill.ecse321.urlms.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdesktop.swingx.prompt.PromptSupport;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.Staff;
import ca.mcgill.ecse321.urlms.model.FundingAccount;
import ca.mcgill.ecse321.urlms.model.URLMS;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ManageFundingAccountPage extends JFrame{
	
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1845274714071764088L;
	/**
	 * URLMS system
	 */
	private URLMS urlms;
	/**
	 * Instance of URLMS controller
	 */
	private URLMSController urlmsCont;
	/**
	 * Current lab whose fundAcc director is viewing/editing
	 */
	private Laboratory currentLab;
	/**
	 * Textfield specifying quantity of fundAcc of the lab
	 */
	private JTextField fundAccQuantity;
	/**
	 * Textfield specifying name of new fundAcc to be added
	 */
	private JTextField newFundNumber;
	/**
	 * Table containing all fundAcc of active lab
	 */
	private JTable fundAccTable;
	/**
	 * Textfield specifying name of old fundAcc to remove
	 */
	private JTextField oldfundAccName;
	/**
	 * Textfield specifying how much quantity of new fundAcc to create
	 */
	private JTextField txtQuantityCreate;
	/**
	 * Textfield specifying how much quantity of existing fundAcc to modify
	 */
	private JTextField txtQuantityModify;
	/**
	 *  List containing all fundAcc of active lab
	 */
	private List<FundingAccount> labfundAcc;
	/**
	 * Constructor of ManageStaffPage frame
	 * @param urlms current URLMS system
	 */
	public ManageFundingAccountPage(URLMS urlms, Laboratory lab, URLMSController urlmsCont){
		this.urlms = urlms;
		this.currentLab = lab;
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
	           java.util.logging.Logger.getLogger(ManageFundingAccountPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (InstantiationException ex) {
	           java.util.logging.Logger.getLogger(ManageFundingAccountPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (IllegalAccessException ex) {
	           java.util.logging.Logger.getLogger(ManageFundingAccountPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	           java.util.logging.Logger.getLogger(ManageFundingAccountPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       }
		initComponents();
	}
	
	/**
	 * Method used to initialize list of fundAcc within active lab.
	 * @param table Table that will contain list of all fundAcc in active lab.
	 */
	private void initialiseTable(JTable table){
		labfundAcc = new ArrayList<FundingAccount>();
		if(currentLab.hasFundingAccounts()){
			labfundAcc = currentLab.getFundingAccounts();
			for(FundingAccount fundAcc : labfundAcc){
				Object[] o = new Object[2];
				  o[0] = fundAcc.getAccountNumber();
				  o[1] = fundAcc.getCurrentBalance();
				  ((DefaultTableModel) table.getModel()).addRow(o);
			}
			
		}
	}
	
	private void removeTable(JTable table) {
		((DefaultTableModel) table.getModel()).setRowCount(0);
	}
	/**
	 * Method used to initialize ManageFundingAccountPage frame
	 */
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Manage Funding Account Page");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		// initialization of components
		JLabel lblManagefundAcc = new JLabel("Manage Funding Accounts");
		lblManagefundAcc.setHorizontalAlignment(SwingConstants.CENTER);
		lblManagefundAcc.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 28));
		JLabel lblfundAccQuant = new JLabel("Number of Accounts:");
		lblfundAccQuant.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		JButton lgtBtn = new JButton("Logout");
		lgtBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lgtBtn.setBackground(Color.RED);
		fundAccQuantity = new JTextField();
		fundAccQuantity.setEditable(false);
		fundAccQuantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fundAccQuantity.setColumns(10);
		JLabel lblCreatefundAcc = new JLabel("Create Account:");
		lblCreatefundAcc.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		newFundNumber = new JTextField();
		newFundNumber.setBackground(Color.WHITE);
		newFundNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newFundNumber.setColumns(10);
		PromptSupport.setPrompt("Account Number", newFundNumber);
		JButton createfundAccBtn = new JButton("Create");
		createfundAccBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		createfundAccBtn.setBackground(Color.BLUE);
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnBack.setBackground(new Color(255, 255, 13));
		JLabel lblModifyfundAcc = new JLabel("Modify Account:");
		lblModifyfundAcc.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		oldfundAccName = new JTextField();
		oldfundAccName.setBackground(Color.WHITE);
		oldfundAccName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		oldfundAccName.setColumns(10);
		PromptSupport.setPrompt("Account Number", oldfundAccName);
		JButton btnUpdatefundAcc = new JButton("Update");
		btnUpdatefundAcc.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnUpdatefundAcc.setBackground(Color.GREEN);
		
		txtQuantityCreate = new JTextField();
		txtQuantityCreate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtQuantityCreate.setColumns(10);
		
		txtQuantityModify = new JTextField();
		txtQuantityModify.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtQuantityModify.setColumns(10);
		PromptSupport.setPrompt("Balance", txtQuantityCreate);
		PromptSupport.setPrompt("Balance", txtQuantityModify);
		
	    getContentPane().setBackground(new Color(216, 247, 255));

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblCreatefundAcc)
											.addGap(18)
											.addComponent(newFundNumber, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblModifyfundAcc)
											.addGap(18)
											.addComponent(oldfundAccName, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)))
									.addGap(47)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtQuantityCreate, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtQuantityModify, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lgtBtn, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(btnUpdatefundAcc, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(createfundAccBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(lblfundAccQuant)
									.addGap(19)
									.addComponent(fundAccQuantity, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(212)
							.addComponent(lblManagefundAcc, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblManagefundAcc, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCreatefundAcc)
						.addComponent(newFundNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(createfundAccBtn)
						.addComponent(txtQuantityCreate, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(14)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(4)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblModifyfundAcc)
										.addComponent(oldfundAccName, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnUpdatefundAcc, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblfundAccQuant)
										.addComponent(fundAccQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
									.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
								.addComponent(lgtBtn, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtQuantityModify, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
					.addGap(18))
		);
		
		fundAccTable = new JTable();
		fundAccTable.setShowGrid(true); // adds cell borders
		fundAccTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		fundAccTable.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		fundAccTable.setCellSelectionEnabled(true);
		fundAccTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Account Number", "Balance"
			}
			){public boolean isCellEditable(int row, int column){return false;}}//This causes all cells to be not editable)
		);
		
		fundAccTable.setRowHeight(40);
		scrollPane.setViewportView(fundAccTable);
		JTableHeader fundAccHeader = fundAccTable.getTableHeader();
		fundAccHeader.setFont(new java.awt.Font("Lucida Grande", 1, 18));
		initialiseTable(fundAccTable);
		fundAccQuantity.setText(String.valueOf(urlmsCont.getActiveLaboratory().numberOfFundingAccounts()));
		getContentPane().setLayout(groupLayout);
		scrollPane.getViewport().setBackground(new Color(216, 247, 255));
		
		//Add all action listners here
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new DirectorLabPage(urlms, currentLab, urlmsCont).setVisible(true);
			}
		});
		
		lgtBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				urlmsCont.logout();
				setVisible(false);
				new LoginPage(urlms).setVisible(true);
			}
		});
		createfundAccBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				createfundAcc();
			}
		});
		btnUpdatefundAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				modifyfundAcc() ;
			}
		});
		
		pack();
		// makes window appear in center of screen
		this.setLocationRelativeTo(null);
	}
	/**
	 * Method to add new type of fundAcc to lab
	 */
	private void createfundAcc() {
		
		if(newFundNumber.getText().isEmpty() || txtQuantityCreate.getText().isEmpty()){
			JOptionPane.showMessageDialog(this, "Account Number and Balance fields cannot be left empty!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (!(newFundNumber.getText().matches("[0-9]+") && newFundNumber.getText().length() > 2)) {
			JOptionPane.showMessageDialog(this, "Account number must only contain numbers!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if(!(txtQuantityCreate.getText().matches("[0-9.]*"))){
			JOptionPane.showMessageDialog(this, "Balance must only contain numbers/periods and cannot be negative!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if(urlmsCont.createFundingAccount(Double.parseDouble(txtQuantityCreate.getText()), Integer.parseInt(newFundNumber.getText()))){
			JOptionPane.showMessageDialog(this, "Account has been successfully added to the lab!");
			Object[] o = {newFundNumber.getText(), txtQuantityCreate.getText()};
			((DefaultTableModel) fundAccTable.getModel()).addRow(o);
			fundAccQuantity.setText(String.valueOf(urlmsCont.getActiveLaboratory().numberOfFundingAccounts()));
			emptyTextField();
		}
			else
			JOptionPane.showMessageDialog(this, "This account already exists!", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private void modifyfundAcc() {
		if(oldfundAccName.getText().isEmpty() || txtQuantityModify.getText().isEmpty()){
			JOptionPane.showMessageDialog(this, "Account Number and Balance fields cannot be left empty!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (!(oldfundAccName.getText().matches("[0-9]+") && oldfundAccName.getText().length() > 2)) {
			JOptionPane.showMessageDialog(this, "Account number does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if(!(txtQuantityModify.getText().matches("-?[0-9.]*"))){
			JOptionPane.showMessageDialog(this, "Balance must only contain numbers/periods", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if(urlmsCont.modifyFunds(Double.parseDouble(txtQuantityModify.getText()), Integer.parseInt(oldfundAccName.getText()))){
			JOptionPane.showMessageDialog(this, "Account balance has been successfully updated!");
			//Object[] o = {newFundNumber.getText(), txtQuantityCreate.getText()};
			//((DefaultTableModel) fundAccTable.getModel()).addRow(o);
			//fundAccQuantity.setText(String.valueOf(urlmsCont.getActiveLaboratory().numberOfFundingAccounts()));
			removeTable(fundAccTable);
			initialiseTable(fundAccTable);
			emptyTextField();
		}
		else
			JOptionPane.showMessageDialog(this, "Account number does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private void emptyTextField() {
		txtQuantityModify.setText("");
		oldfundAccName.setText("");
		newFundNumber.setText("");
		txtQuantityCreate.setText("");
	}
}
