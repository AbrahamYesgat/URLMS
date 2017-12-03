package ca.mcgill.ecse321.urlms.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import org.jdesktop.swingx.prompt.PromptSupport;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.Staff;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.viewhelpers.ButtonEditor;
import ca.mcgill.ecse321.urlms.viewhelpers.ButtonRenderer;
import javax.swing.SwingConstants;
import java.awt.TextField;
import javax.swing.JTextField;

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
	 * Instance of URLMS controller
	 */
	private URLMSController urlmsCont;
	/**
	 * Current lab whose staff director is viewing/editing
	 */
	private Laboratory currentLab;
	/**
	 * Textfield specifying how many staff members are part of the lab
	 */
	private JTextField staffQuantity;
	/**
	 * Textfield specifying email of new staff member to be added
	 */
	private JTextField newStaffEmail;
	/**
	 * Textfield specifying name of new staff member to be added
	 */
	private JTextField newStaffName;
	/**
	 * Constructor of ManageStaffPage frame
	 * @param urlms current URLMS system
	 */
	public ManageStaffPage(URLMS urlms, Laboratory lab, URLMSController urlmsCont){
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
	           java.util.logging.Logger.getLogger(DirectorLabPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (InstantiationException ex) {
	           java.util.logging.Logger.getLogger(DirectorLabPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (IllegalAccessException ex) {
	           java.util.logging.Logger.getLogger(DirectorLabPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	           java.util.logging.Logger.getLogger(DirectorLabPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       }
		this.urlmsCont = urlmsCont;
		initComponents();
	}
	
	/**
	 * Method used to initialize list of staff members within a particular lab.
	 * @param table Table that will contain list of all staff members in lab.
	 */
	private void initialiseTable(JTable table){
		List<Staff> labStaff = new ArrayList<Staff>();
		if(currentLab.hasStaffs()){
			labStaff = currentLab.getStaffs();
			for(Staff staff : labStaff){
				Object[] o = new Object[4];
				  o[0] = staff.getName();
				  o[1] = staff.getEmail();
				  o[2] = staff.getStaffRole();
				  o[3] = "Remove";
				  ((DefaultTableModel) table.getModel()).addRow(o);
			}
			
		}
	}
	/**
	 * Method used to initialize ManageStaggPage frame
	 */
	private void initComponents(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Manage Staff Page");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(new java.awt.Rectangle(0, 0, screenSize.width/2, screenSize.height/2));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		// Initialization of components
		JLabel lblManageStaff = new JLabel("Manage Staff");
		lblManageStaff.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageStaff.setFont(new Font("Modern No. 20", Font.PLAIN, 28));
		JLabel lblStaffQuant = new JLabel("Number of staff members:");
		lblStaffQuant.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 24));
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		logoutBtn.setBackground(new Color(255, 255, 13));
		staffQuantity = new JTextField();
		staffQuantity.setEditable(false);
		staffQuantity.setFont(new Font("Tahoma", Font.PLAIN, 24));
		staffQuantity.setColumns(10);
		JLabel lblAddStaff = new JLabel("Add staff member: ");
		lblAddStaff.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 24));
		newStaffEmail = new JTextField();
		newStaffEmail.setFont(new Font("Tahoma", Font.PLAIN, 24));
		newStaffEmail.setColumns(10);
		PromptSupport.setPrompt("Email", newStaffEmail);
		newStaffName = new JTextField();
		newStaffName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		newStaffName.setColumns(10);
		PromptSupport.setPrompt("Name", newStaffName);
		JButton addStaffBtn = new JButton("Add Staff");
		addStaffBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		addStaffBtn.setBackground(new Color(0, 255, 0));
		
		
		// Initialization of layout
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblStaffQuant)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(staffQuantity, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblAddStaff)
									.addGap(18)
									.addComponent(newStaffName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(37)
									.addComponent(newStaffEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(addStaffBtn)
										.addComponent(logoutBtn, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(333)
							.addComponent(lblManageStaff, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblManageStaff, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAddStaff)
								.addComponent(newStaffName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(newStaffEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(26)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStaffQuant)
								.addComponent(staffQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(addStaffBtn))
					.addGap(42)
					.addComponent(logoutBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JTable staffTable = new JTable();
		staffTable.setShowGrid(true); // adds cell borders
		staffTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		staffTable.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		staffTable.setCellSelectionEnabled(true);
		staffTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Email", "Role", "Remove Member"
			}
			));
		
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {logoutBtn, addStaffBtn});
		groupLayout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {newStaffEmail, newStaffName, addStaffBtn});
		
	    staffTable.getColumn("Remove Member").setCellRenderer(new ButtonRenderer());
	    staffTable.getColumn("Remove Member").setCellEditor(new ButtonEditor(new JCheckBox()));
		staffTable.setRowHeight(40);
		scrollPane.setViewportView(staffTable);
		JTableHeader staffHeader = staffTable.getTableHeader();
		staffHeader.setFont(new java.awt.Font("Lucida Grande", 1, 18));
		initialiseTable(staffTable);
		getContentPane().setLayout(groupLayout);
		
		
		
		// logout button action listener
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				urlmsCont.logout();
				dispose();
				new LoginPage(urlms).setVisible(true);
			}
		});
		
		
		
		
		
		
		pack();
		// makes window appear in center of screen
		this.setLocationRelativeTo(null);
		
		// addStaff button action listener
		addStaffBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				staffAddButtonActionPerformed();
			}
		});
	}
	/**
	 * Method to add staff member to lab through director's input.
	 */
	private void staffAddButtonActionPerformed() {
		if(urlmsCont.addStaff(newStaffEmail.getText(), "password123", newStaffName.getText(),  Staff.StaffRole.ResearchAssistant)){
			JOptionPane.showMessageDialog(this, newStaffName.getText() + " was successfully added to the lab!");
		}
		else{
			JOptionPane.showMessageDialog(this, newStaffName.getText() + " is already part of the lab!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
