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
	 * Current lab whose equipment director is viewing/editing
	 */
	private Laboratory currentLab;
	/**
	 * Textfield specifying quantity of equipment of the lab
	 */
	private JTextField equipmentQuantity;
	/**
	 * Textfield specifying name of new equipment to be added
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
		JLabel lblEquipmentQuant = new JLabel("Total quantity of Equipment:");
		lblEquipmentQuant.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		JButton lgtBtn = new JButton("Logout");
		lgtBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lgtBtn.setBackground(Color.RED);
		equipmentQuantity = new JTextField();
		equipmentQuantity.setEditable(false);
		equipmentQuantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		equipmentQuantity.setColumns(10);
		JLabel lblAddEquipment = new JLabel("Add Equipment: ");
		lblAddEquipment.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		newEquipmentName = new JTextField();
		newEquipmentName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newEquipmentName.setColumns(10);
		PromptSupport.setPrompt("Equipment Name", newEquipmentName);
		JButton addEquipmentBtn = new JButton("Add Equipment");
		addEquipmentBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		addEquipmentBtn.setBackground(new Color(0, 255, 0));
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnBack.setBackground(new Color(255, 255, 13));
		JLabel lblRemoveEquipment = new JLabel("Remove Equipment: ");
		lblRemoveEquipment.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		oldEquipmentName = new JTextField();
		oldEquipmentName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		oldEquipmentName.setColumns(10);
		PromptSupport.setPrompt("Equipment Name", oldEquipmentName);
		JButton btnRemoveEquipment = new JButton("Remove Equipment");
		btnRemoveEquipment.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnRemoveEquipment.setBackground(Color.RED);
		
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblRemoveEquipment)
										.addComponent(lblAddEquipment)
										.addComponent(lblEquipmentQuant))
									.addGap(19)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(oldEquipmentName, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
										.addComponent(newEquipmentName, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
										.addComponent(equipmentQuantity, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))))
							.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnRemoveEquipment, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(addEquipmentBtn, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
									.addComponent(lgtBtn, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(192)
							.addComponent(lblManageEquipment, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblManageEquipment, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAddEquipment)
							.addGap(14))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(newEquipmentName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(addEquipmentBtn))
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblRemoveEquipment)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEquipmentQuant)
								.addComponent(equipmentQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
							.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(oldEquipmentName, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(13)
									.addComponent(btnRemoveEquipment, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
							.addComponent(lgtBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
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
		JTableHeader EquipmentHeader = equipmentTable.getTableHeader();
		EquipmentHeader.setFont(new java.awt.Font("Lucida Grande", 1, 18));
		//TODO: call initialize table method here
		equipmentQuantity.setText(String.valueOf(urlmsCont.getActiveLaboratory().numberOfEquipment()));
		getContentPane().setLayout(groupLayout);
		
		
		btnBack.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		dispose();
		new DirectorLabPage(urlms, currentLab, urlmsCont).setVisible(true);
			}
		});
		
		
		pack();
		// makes window appear in center of screen
		this.setLocationRelativeTo(null);
	}
}
