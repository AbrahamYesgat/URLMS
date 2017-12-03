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
import ca.mcgill.ecse321.urlms.model.URLMS;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManageSupplyPage extends JFrame{
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -5490159581461131362L;
	/**
	 * URLMS system
	 */
	private URLMS urlms;
	/**
	 * Instance of URLMS controller
	 */
	private URLMSController urlmsCont;
	/**
	 * Current lab whose supply director is viewing/editing
	 */
	private Laboratory currentLab;
	/**
	 * Textfield specifying quantity of supply of the lab
	 */
	private JTextField supplyQuantity;
	/**
	 * Textfield specifying name of new supply to be added
	 */
	private JTextField newSupplyName;
	/**
	 * Table containing all supply of active lab
	 */
	private JTable supplyTable;
	/**
	 * Textfield specifying name of old supply to remove
	 */
	private JTextField oldSupplyName;
	private JTextField txtQuantityCreate;
	private JTextField txtQuantityModify;
	/**
	 * Constructor of ManageStaffPage frame
	 * @param urlms current URLMS system
	 */
	public ManageSupplyPage(URLMS urlms, Laboratory lab, URLMSController urlmsCont){
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
	           java.util.logging.Logger.getLogger(ManageSupplyPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (InstantiationException ex) {
	           java.util.logging.Logger.getLogger(ManageSupplyPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (IllegalAccessException ex) {
	           java.util.logging.Logger.getLogger(ManageSupplyPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	           java.util.logging.Logger.getLogger(ManageSupplyPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       }
		initComponents();
	}
	/**
	 * Method used to initialize ManageSupplyPage frame
	 */
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Manage Staff Page");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		// initialization of components
		JLabel lblManageSupply = new JLabel("Manage Supplies");
		lblManageSupply.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageSupply.setFont(new Font("Modern No. 20", Font.PLAIN, 28));
		JLabel lblSupplyQuant = new JLabel("Total quantity of supply:");
		lblSupplyQuant.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		JButton lgtBtn = new JButton("Logout");
		lgtBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lgtBtn.setBackground(Color.RED);
		supplyQuantity = new JTextField();
		supplyQuantity.setEditable(false);
		supplyQuantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		supplyQuantity.setColumns(10);
		JLabel lblCreateSupply = new JLabel("Create supply: ");
		lblCreateSupply.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		newSupplyName = new JTextField();
		newSupplyName.setBackground(Color.WHITE);
		newSupplyName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newSupplyName.setColumns(10);
		PromptSupport.setPrompt("Supply Name", newSupplyName);
		JButton createSupplyBtn = new JButton("Create");
		createSupplyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		createSupplyBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		createSupplyBtn.setBackground(Color.BLUE);
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnBack.setBackground(new Color(255, 255, 13));
		JLabel lblModifySupply = new JLabel("Modify supply: ");
		lblModifySupply.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		oldSupplyName = new JTextField();
		oldSupplyName.setBackground(Color.WHITE);
		oldSupplyName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		oldSupplyName.setColumns(10);
		PromptSupport.setPrompt("Supply Name", oldSupplyName);
		JButton btnUpdateSupply = new JButton("Update");
		btnUpdateSupply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdateSupply.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnUpdateSupply.setBackground(Color.GREEN);
		
		JButton btnDelete = new JButton("Remove");
		btnDelete.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnDelete.setBackground(Color.RED);
		
		txtQuantityCreate = new JTextField();
		txtQuantityCreate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtQuantityCreate.setColumns(10);
		
		txtQuantityModify = new JTextField();
		txtQuantityModify.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtQuantityModify.setColumns(10);
		PromptSupport.setPrompt("Quantity", txtQuantityCreate);
		PromptSupport.setPrompt("Quantity", txtQuantityModify);
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblCreateSupply)
											.addGap(18)
											.addComponent(newSupplyName, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblModifySupply)
											.addGap(18)
											.addComponent(oldSupplyName, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)))
									.addGap(47)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtQuantityCreate, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtQuantityModify, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lgtBtn, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(btnUpdateSupply)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
											.addComponent(createSupplyBtn, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblSupplyQuant)
									.addGap(19)
									.addComponent(supplyQuantity, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(220, Short.MAX_VALUE)
					.addComponent(lblManageSupply, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)
					.addGap(203))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblManageSupply, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCreateSupply)
						.addComponent(newSupplyName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(createSupplyBtn)
						.addComponent(txtQuantityCreate, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(14)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(4)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblModifySupply)
										.addComponent(oldSupplyName, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnUpdateSupply, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblSupplyQuant)
										.addComponent(supplyQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
									.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
								.addComponent(lgtBtn, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtQuantityModify, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
					.addGap(18))
		);
		
		//groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {lgtBtn, addStaffBtn});
		//groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {lgtBtn, addStaffBtn});
		supplyTable = new JTable();
		supplyTable.setShowGrid(true); // adds cell borders
		supplyTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		supplyTable.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		supplyTable.setCellSelectionEnabled(true);
		supplyTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Quantity"
			}
			){public boolean isCellEditable(int row, int column){return false;}}//This causes all cells to be not editable)
		);
		
		supplyTable.setRowHeight(40);
		scrollPane.setViewportView(supplyTable);
		JTableHeader supplyHeader = supplyTable.getTableHeader();
		supplyHeader.setFont(new java.awt.Font("Lucida Grande", 1, 18));
		//TODO: call initialize table method here
		supplyQuantity.setText(String.valueOf(urlmsCont.getActiveLaboratory().numberOfSupplies()));
		getContentPane().setLayout(groupLayout);
		
		
		//Add all action listners here
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DirectorLabPage(urlms, currentLab, urlmsCont).setVisible(true);
			}
		});
		
		lgtBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				urlmsCont.logout();
				dispose();
				new LoginPage(urlms).setVisible(true);
			}
		});
		createSupplyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				createSupply();
			}
		});
		
		pack();
		// makes window appear in center of screen
		this.setLocationRelativeTo(null);
	}
	private void createSupply() {
		
		if(newSupplyName.getText().isEmpty() || txtQuantityCreate.getText().isEmpty()){
			JOptionPane.showMessageDialog(this, "Supply Name and Quantity fields cannot be left empty!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if(urlmsCont.createSupplies(newSupplyName.getText(), Integer.parseInt(txtQuantityCreate.getText())))
			JOptionPane.showMessageDialog(this, "Supply Name and Quantity have been updated");
		else
			JOptionPane.showMessageDialog(this, "Supply Name already exists");
	}
}
