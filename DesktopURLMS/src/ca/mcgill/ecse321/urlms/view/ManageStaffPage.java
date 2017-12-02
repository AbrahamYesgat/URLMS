package ca.mcgill.ecse321.urlms.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.viewhelpers.ButtonEditor;
import ca.mcgill.ecse321.urlms.viewhelpers.ButtonRenderer;
import javax.swing.SwingConstants;

public class ManageStaffPage extends JFrame{
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -6286210470352765374L;
	/**
	 * URLMS system
	 */
	private URLMS urlms;
	/**
	 * Constructor of ManageStaffPage frame
	 * @param urlms current URLMS system
	 */
	public ManageStaffPage(URLMS urlms){
		this.urlms = urlms;
		setResizable(false);
		
		try {
	           for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	               if ("Nimbus".equals(info.getName())) {
	                   javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                   break;
	               }
	           }
	       } catch (ClassNotFoundException ex) {
	           java.util.logging.Logger.getLogger(DirectorLabPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (InstantiationException ex) {
	           java.util.logging.Logger.getLogger(DirectorLabPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (IllegalAccessException ex) {
	           java.util.logging.Logger.getLogger(DirectorLabPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	           java.util.logging.Logger.getLogger(DirectorLabPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       }
		initComponents();
	}
	/**
	 * Method used to initialize ManageStaggPage frame
	 */
	private void initComponents(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Manage Staff Page");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(new java.awt.Rectangle(0, 0, screenSize.width/2, screenSize.height/2));
		// makes window appear in center of screen
		this.setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		// Initialization of components
		JLabel lblManageStaff = new JLabel("Manage Staff");
		lblManageStaff.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageStaff.setFont(new Font("Modern No. 20", Font.PLAIN, 24));
		JButton createLabBtn = new JButton("Create Laboratory");
		createLabBtn.setBackground(new Color(0, 191, 255));
		createLabBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		logoutBtn.setBackground(new Color(255, 255, 13));
		
		// Initialization of layout
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 920, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(createLabBtn, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 516, Short.MAX_VALUE)
							.addComponent(logoutBtn, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblManageStaff, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblManageStaff, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(createLabBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(logoutBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addGap(30))
		);
		
		JTable staffTable = new JTable();
		staffTable.setShowGrid(true); // adds cell borders
		staffTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		staffTable.setCellSelectionEnabled(true);
		staffTable.setModel(new DefaultTableModel(
			new Object[][] { { "name", "email", "role", "Add", "Remove"}
			},
			new String[] {
				"Name", "Email", "Role", "Add Staff", "Remove Staff"
			}
			));
		
		staffTable.getColumn("Add Staff").setCellRenderer(new ButtonRenderer());
	    staffTable.getColumn("Add Staff").setCellEditor(new ButtonEditor(new JCheckBox()));
	    staffTable.getColumn("Remove Staff").setCellRenderer(new ButtonRenderer());
	    staffTable.getColumn("Remove Staff").setCellEditor(new ButtonEditor(new JCheckBox()));
		staffTable.setRowHeight(40);
		scrollPane.setViewportView(staffTable);
		JTableHeader staffHeader = staffTable.getTableHeader();
		staffHeader.setFont(new java.awt.Font("Lucida Grande", 1, 18));
		getContentPane().setLayout(groupLayout);
	}
}
