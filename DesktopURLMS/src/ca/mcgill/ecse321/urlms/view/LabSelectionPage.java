package ca.mcgill.ecse321.urlms.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.Staff;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.model.UserRole;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LabSelectionPage extends JFrame {

	/**
	 * Servial version ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Table containing all labs user is a part of
	 */
	private JTable labSelectionTable;
	private UserRole currentUser;
	private URLMS urlms;
	private URLMSController urlmsCont;
	/**
	 * Create the frame.
	 */
	public LabSelectionPage(URLMS urlms, String email) {
		try {
	           for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	               if ("Nimbus".equals(info.getName())) {
	                   javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                   break;
	               }
	           }
	       } catch (ClassNotFoundException ex) {
	           java.util.logging.Logger.getLogger(LabSelectionPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (InstantiationException ex) {
	           java.util.logging.Logger.getLogger(LabSelectionPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (IllegalAccessException ex) {
	           java.util.logging.Logger.getLogger(LabSelectionPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	           java.util.logging.Logger.getLogger(LabSelectionPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       }
		this.urlms = urlms;
		this.urlmsCont = new URLMSController(urlms);
		initialiseCurrentUser(email);
		// initialize page/frame
		initComponents();
		
	}
	/**
	 * Method used to initialize logged in user
	 * @param email Email of logged in user which is their unique identifier.
	 */
	private void initialiseCurrentUser(String email){
		Director dir = urlmsCont.getDirector(email);
		if(dir != null)
			currentUser = dir;
		else
			currentUser = urlmsCont.getStaffMember(email);
	}
	/**
	 * Method used to initialize list of labs of logged in user.
	 * @param table Table that will contain list of all labs of logged in user.
	 */
	private void initialiseTable(JTable table){
		List<Laboratory> labs = new ArrayList<Laboratory>();
		Director dir = urlmsCont.getDirector(currentUser.getEmail());
		
		if(dir != null)
			labs = ((Director) currentUser).getLaboratories();
		
		else{
			Staff staff = urlmsCont.getStaffMember(currentUser.getEmail());
			labs = ((Staff) currentUser).getLaboratories();
		}
		
		for(Laboratory lab : labs){
			Object[] o = new Object[4];
			  o[0] = lab.getName();
			  o[1] = lab.getFieldOfStudy();
			  o[2] = lab.getActive();
			  o[3] = lab.getStartDate();
			  ((DefaultTableModel) table.getModel()).addRow(o);
		}
	}
	/**
	 * Method used to initialize page
	 */
	private void initComponents(){
		
		for(Laboratory lab : ((Director)this.currentUser).getLaboratories()){
			
		}
		// Global settings and listeners
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Lab Selection Page");
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JLabel lblExistingLaboratories = new JLabel("Existing Laboratories :");
		lblExistingLaboratories.setFont(new Font("Modern No. 20", Font.PLAIN, 24));
		
		JButton createLabBtn = new JButton("Create Laboratory");
		createLabBtn.setBackground(new Color(0, 191, 255));
		createLabBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblExistingLaboratories, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
						.addComponent(createLabBtn, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblExistingLaboratories, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
					.addComponent(createLabBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(30))
		);
		
		labSelectionTable = new JTable();
		labSelectionTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		labSelectionTable.setCellSelectionEnabled(true);
		labSelectionTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Field", "Active", "Start Date"
			}
		));
		labSelectionTable.setRowHeight(35);
		initialiseTable(labSelectionTable);
		scrollPane.setViewportView(labSelectionTable);
		JTableHeader labSelectionHeader = labSelectionTable.getTableHeader();
		labSelectionHeader.setFont(new java.awt.Font("Lucida Grande", 1, 18));
		getContentPane().setLayout(groupLayout);
		
		// action listeners of all events here
		createLabBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			}
		});	
		
		// set window size to preferred size of components
		pack();
		
	}
}
