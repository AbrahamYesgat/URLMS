package ca.mcgill.ecse321.urlms.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Calendar;
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
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.Staff;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.model.UserRole;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
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
	/**
	 * Currently logged in user
	 */
	private UserRole currentUser;
	/**
	 * URLMS system
	 */
	private URLMS urlms;
	/**
	 * Instance of URLMS controller
	 */
	private URLMSController urlmsCont;
	/**
	 * List of labs of active user
	 */
	private List<Laboratory> labs;
	/**
	 * Create the frame.
	 */
	public LabSelectionPage(URLMS urlms, String email, URLMSController urlmsCont) {
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
		this.urlmsCont = urlmsCont;
			
		//this.email = email;
		initialiseCurrentUser(email);
		setResizable(false);
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
		if( !(currentUser instanceof Director)) {
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	        String strDate = sdf.format(cal.getTime());
	        urlmsCont.getStaffMember(currentUser.getEmail()).setLastLogin(strDate);
;		}
	}
	/**
	 * Method used to initialize list of labs of logged in user.
	 * @param table Table that will contain list of all labs of logged in user.
	 */
	private void initialiseTable(JTable table){
		labs = new ArrayList<Laboratory>();
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
		// Initialization of scroll pane for table
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Lab Selection Page");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(new java.awt.Rectangle(0, 0, screenSize.width/2, screenSize.height/2));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		// Initialization of components
		JLabel lblExistingLaboratories = new JLabel("Existing Laboratories :");
		lblExistingLaboratories.setFont(new Font("Modern No. 20", Font.PLAIN, 28));
		JButton createLabBtn = new JButton("Create Laboratory");
		createLabBtn.setBackground(new Color(0, 191, 255));
		createLabBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		logoutBtn.setBackground(Color.RED);
		
	    getContentPane().setBackground(new Color(216, 247, 255));
	    
	    if(urlmsCont.getActiveUser() instanceof Staff){ // disable create lab button if user is a staff member
	    	createLabBtn.setEnabled(false);
	    }
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(createLabBtn, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 570, Short.MAX_VALUE)
							.addComponent(logoutBtn, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblExistingLaboratories, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE))
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
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(createLabBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(logoutBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addGap(30))
		);
		
		labSelectionTable = new JTable(){
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (column == 0 && c instanceof JComponent) {
					JComponent jc = (JComponent) c;
					 String hovermssg ="<html><p><font color=\"#800080\" " +"size=\"4\" face=\"Segoe UI Semibold\">Double click to view lab " +
					"</font></p></html>";
					jc.setToolTipText(hovermssg);
				}
				return c;
			}
		};
		labSelectionTable.setShowGrid(true); // adds cell borders
		labSelectionTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		labSelectionTable.setCellSelectionEnabled(true);
		labSelectionTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Field", "Active", "Start Date"
			}
			)
			{public boolean isCellEditable(int row, int column){return false;}}//This causes all cells to be not editable
		);
		labSelectionTable.setRowHeight(40);
		labSelectionTable.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		initialiseTable(labSelectionTable);
		scrollPane.setViewportView(labSelectionTable);
		JTableHeader labSelectionHeader = labSelectionTable.getTableHeader();
		labSelectionHeader.setFont(new java.awt.Font("Lucida Grande", 1, 18));
		getContentPane().setLayout(groupLayout);
		scrollPane.getViewport().setBackground(new Color(216, 247, 255));
		
		
		
		// action listeners of all events here
		
		// create lab button action listener
		createLabBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			}
		});	
		// logout button action listener
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				urlmsCont.logout();
				setVisible(false);
				new LoginPage(urlms).setVisible(true);
			}
		});
		
		createLabBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				new CreateLabPage(urlms, urlmsCont).setVisible(true);
				setVisible(false);
			}
		});
		// lab selection table mouse listener used to redirect to selected lab's home page
		labSelectionTable.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
			    if (e.getClickCount() == 2) {
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();
			      int column = target.getSelectedColumn();
			      	if(0 <= column && column <= 3){
			    	  urlmsCont.setActiveLaboratory(labs.get(row));
			    	  if(urlmsCont.getActiveUser() instanceof Director){
			    		  DirectorLabPage labHomePage = new DirectorLabPage(urlms, labs.get(row), urlmsCont);
				    	  labHomePage.setVisible(true);
				    	  setVisible(false);
			    	  }
			    	  else{
			    		  if(labs.get(row).getActive() == true){
			    			  StaffLabPage labHomePage = new StaffLabPage(urlms, labs.get(row), urlmsCont);
			    			  labHomePage.setVisible(true);
			    			  setVisible(false);
			    		  }
			    		  else{
			    			  inactiveError(labs.get(row).getName());
			    		  }
			    	  }
			      }
			     }
			  }
			});
		
		
		pack();
		// makes window appear in center of screen
		this.setLocationRelativeTo(null);
		
	}
	
	private void inactiveError(String labName) {
		JOptionPane.showMessageDialog(this, labName + " is inactive!", "Error", JOptionPane.ERROR_MESSAGE);
		
	}
}
