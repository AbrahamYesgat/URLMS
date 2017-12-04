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
import ca.mcgill.ecse321.urlms.model.Equipment;
import ca.mcgill.ecse321.urlms.model.URLMS;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ManageEquipmentPage extends JFrame{
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -5301184125170629276L;
	/**
	 * URLMS system
	 */
	private URLMS urlms;
	/**
	 * Instance of URLMS controller
	 */
	private URLMSController urlmsCont;
	/**
	 * Current lab whose Equipment director is viewing/editing
	 */
	private Laboratory currentLab;
	/**
	 * Textfield specifying quantity of Equipment of the lab
	 */
	private JTextField equipmentQuantity;
	/**
	 * Textfield specifying name of new Equipment to be added
	 */
	private JTextField newEquipmentName;
	/**
	 * Table containing all Equipment of active lab
	 */
	private JTable equipmentTable;
	/**
	 * Textfield specifying name of old Equipment to remove
	 */
	private JTextField oldEquipmentName;
	/**
	 * Textfield specifying how much quantity of new Equipment to create
	 */
	private JTextField txtQuantityCreate;
	/**
	 * Textfield specifying how much quantity of existing Equipment to modify
	 */
	private JTextField txtQuantityModify;
	/**
	 *  List containing all Equipment of active lab
	 */
	private List<Equipment> labEquipment;
	/**
	 * Constructor of ManageStaffPage frame
	 * @param urlms current URLMS system
	 */
	public ManageEquipmentPage(URLMS urlms, Laboratory lab, URLMSController urlmsCont){
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
	           java.util.logging.Logger.getLogger(ManageEquipmentPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (InstantiationException ex) {
	           java.util.logging.Logger.getLogger(ManageEquipmentPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (IllegalAccessException ex) {
	           java.util.logging.Logger.getLogger(ManageEquipmentPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	           java.util.logging.Logger.getLogger(ManageEquipmentPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       }
		initComponents();
	}
	
	/**
	 * Method used to initialize list of Equipment within active lab.
	 * @param table Table that will contain list of all Equipment in active lab.
	 */
	private void initialiseTable(JTable table){
		labEquipment = new ArrayList<Equipment>();
		if(currentLab.hasEquipment()){
			labEquipment = currentLab.getEquipment();
			for(Equipment equipment : labEquipment){
				Object[] o = new Object[4];
				  o[0] = equipment.getName();
				  o[1] = equipment.getQuantity();
				  ((DefaultTableModel) table.getModel()).addRow(o);
			}
			
		}
	}
	/**
	 * Method used to initialize ManageEquipmentPage frame
	 */
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Manage Equipment Page");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		// initialization of components
		JLabel lblManageEquipment = new JLabel("Manage Equipment");
		lblManageEquipment.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageEquipment.setFont(new Font("Modern No. 20", Font.PLAIN, 28));
		JLabel lblEquipmentQuant = new JLabel("Total quantity of equipment:");
		lblEquipmentQuant.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		JButton lgtBtn = new JButton("Logout");
		lgtBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lgtBtn.setBackground(Color.RED);
		equipmentQuantity = new JTextField();
		equipmentQuantity.setEditable(false);
		equipmentQuantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		equipmentQuantity.setColumns(10);
		JLabel lblCreateEquipment = new JLabel("Create Equipment: ");
		lblCreateEquipment.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		newEquipmentName = new JTextField();
		newEquipmentName.setBackground(Color.WHITE);
		newEquipmentName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newEquipmentName.setColumns(10);
		PromptSupport.setPrompt("Equipment Name", newEquipmentName);
		JButton createEquipmentBtn = new JButton("Create");
		createEquipmentBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		createEquipmentBtn.setBackground(Color.BLUE);
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnBack.setBackground(new Color(255, 255, 13));
		JLabel lblModifyEquipment = new JLabel("Modify Equipment: ");
		lblModifyEquipment.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		oldEquipmentName = new JTextField();
		oldEquipmentName.setBackground(Color.WHITE);
		oldEquipmentName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		oldEquipmentName.setColumns(10);
		PromptSupport.setPrompt("Equipment Name", oldEquipmentName);
		JButton btnUpdateEquipment = new JButton("Update");
		btnUpdateEquipment.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnUpdateEquipment.setBackground(Color.GREEN);
		
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
		
	    getContentPane().setBackground(new Color(216, 247, 255));

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
											.addComponent(lblCreateEquipment)
											.addGap(18)
											.addComponent(newEquipmentName, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblModifyEquipment)
											.addGap(18)
											.addComponent(oldEquipmentName, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)))
									.addGap(47)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtQuantityCreate, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtQuantityModify, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lgtBtn, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(btnUpdateEquipment)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
											.addComponent(createEquipmentBtn, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblEquipmentQuant)
									.addGap(19)
									.addComponent(equipmentQuantity, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(220, Short.MAX_VALUE)
					.addComponent(lblManageEquipment, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)
					.addGap(203))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblManageEquipment, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCreateEquipment)
						.addComponent(newEquipmentName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(createEquipmentBtn)
						.addComponent(txtQuantityCreate, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(14)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(4)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblModifyEquipment)
										.addComponent(oldEquipmentName, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnUpdateEquipment, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblEquipmentQuant)
										.addComponent(equipmentQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
									.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
								.addComponent(lgtBtn, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtQuantityModify, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
					.addGap(18))
		);
		
		equipmentTable = new JTable();
		equipmentTable.setShowGrid(true); // adds cell borders
		equipmentTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		equipmentTable.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		equipmentTable.setCellSelectionEnabled(true);
		equipmentTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Quantity"
			}
			){public boolean isCellEditable(int row, int column){return false;}}//This causes all cells to be not editable)
		);
		
		equipmentTable.setRowHeight(40);
		scrollPane.setViewportView(equipmentTable);
		JTableHeader equipmentHeader = equipmentTable.getTableHeader();
		equipmentHeader.setFont(new java.awt.Font("Lucida Grande", 1, 18));
		initialiseTable(equipmentTable);
		equipmentQuantity.setText(String.valueOf(urlmsCont.getActiveLaboratory().numberOfEquipment()));
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
		createEquipmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				createEquipment();
			}
		});
		
		pack();
		// makes window appear in center of screen
		this.setLocationRelativeTo(null);
	}
	/**
	 * Method to add new type of Equipment to lab
	 */
	private void createEquipment() {
		
		if(newEquipmentName.getText().isEmpty() || txtQuantityCreate.getText().isEmpty()){
			JOptionPane.showMessageDialog(this, "Equipment Name and Quantity fields cannot be left empty!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if(Integer.parseInt(txtQuantityCreate.getText()) <= 0){
			JOptionPane.showMessageDialog(this, "Please enter a valid quantity!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if(urlmsCont.createEquipment(newEquipmentName.getText(), Integer.parseInt(txtQuantityCreate.getText()))){
			JOptionPane.showMessageDialog(this, txtQuantityCreate.getText() + " " + newEquipmentName.getText() + " have been successfully added to the lab!");
			Object[] o = {newEquipmentName.getText(), txtQuantityCreate.getText()};
			((DefaultTableModel) equipmentTable.getModel()).addRow(o);
			equipmentQuantity.setText(String.valueOf(urlmsCont.getActiveLaboratory().numberOfEquipment()));
		}
			else
			JOptionPane.showMessageDialog(this, newEquipmentName.getText() + " already exists!", "Error", JOptionPane.ERROR_MESSAGE);
	}
}
