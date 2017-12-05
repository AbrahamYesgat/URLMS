package ca.mcgill.ecse321.urlms.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.URLMS;
/**
 * UI for the home page of a selected lab.
 * @author Group 1
 *
 */
public class DirectorLabPage extends JFrame{

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1020649756845890686L;
	/**
	 * URLMS System
	 */
	private URLMS urlms;
	/**
	 * Current lab that user is viewing/editing
	 */
	private Laboratory currentLab;
	/**
	 * URLMS controller
	 */
	private URLMSController urlmsCont;
	/**
	 * Constructor of DirectorLabPage frame
	 * @param urlms current URLMS system
	 * @param lab current lab user is viewing
	 */
	public DirectorLabPage(URLMS urlms, Laboratory lab, URLMSController urlmsCont) {
		setResizable(false);
		this.urlms = urlms;
		this.currentLab = lab;
		this.urlmsCont = urlmsCont;
		
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
	
	private void initComponents() {
		
		this.setTitle("University Lab Management System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Initialization of all buttons and labels
		JLabel welcomeLbl = new JLabel("Welcome Director");
		welcomeLbl.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 24));
		welcomeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnStaff = new JButton("Manage Staff");
		btnStaff.setBackground(new Color(0, 191, 255));
		btnStaff.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		
		JButton btnEquipment = new JButton("Manage Equipment");
		btnEquipment.setBackground(new Color(0, 191, 255));
		btnEquipment.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		
		JButton btnSupplies = new JButton("Manage Supplies");
		btnSupplies.setBackground(new Color(0, 191, 255));
		btnSupplies.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		
		JButton btnFundAcc = new JButton("Manage Funding Accounts");
		btnFundAcc.setBackground(new Color(0, 191, 255));
		btnFundAcc.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		
		JButton btnProgReport = new JButton("View Weekly Progress Report");
		btnProgReport.setBackground(new Color(0, 191, 255));
		btnProgReport.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		
		JButton updateProfileBtn = new JButton("Update Profile");
		updateProfileBtn.setBackground(new Color(0, 191, 255));
		updateProfileBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		
		JButton btnExpReport = new JButton("Generate Expense Report");
		btnExpReport.setBackground(new Color(0, 191, 255));
		btnExpReport.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
	    
	    JButton backBtn = new JButton("Back");
	    backBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
	    backBtn.setBackground(new Color(255, 255, 13));
	    
	    JButton lgtButn = new JButton("Logout");
	    lgtButn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
	    lgtButn.setBackground(Color.RED);
	    
	    JButton editButton = new JButton("Edit Lab");
	    editButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
	    editButton.setBackground(new Color(0, 191, 255));
		
	    getContentPane().setBackground(new Color(216, 247, 255));
	    
		// Layout
	    GroupLayout layout = new GroupLayout(getContentPane());
	    layout.setHorizontalGroup(
	    	layout.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(layout.createSequentialGroup()
	    			.addGroup(layout.createParallelGroup(Alignment.LEADING)
	    				.addGroup(layout.createSequentialGroup()
	    					.addContainerGap(217, Short.MAX_VALUE)
	    					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
	    						.addGroup(layout.createSequentialGroup()
	    							.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
	    								.addComponent(btnStaff, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    								.addComponent(btnEquipment, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    								.addComponent(btnSupplies, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    								.addComponent(editButton, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
	    							.addGap(122)
	    							.addGroup(layout.createParallelGroup(Alignment.TRAILING)
	    								.addGroup(layout.createParallelGroup(Alignment.LEADING)
	    									.addComponent(btnExpReport)
	    									.addComponent(btnFundAcc)
	    									.addComponent(btnProgReport))
	    								.addComponent(updateProfileBtn)))
	    						.addGroup(layout.createSequentialGroup()
	    							.addComponent(welcomeLbl)
	    							.addGap(280))))
	    				.addGroup(layout.createSequentialGroup()
	    					.addContainerGap()
	    					.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)))
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(lgtButn, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
	    			.addContainerGap())
	    );
	    layout.setVerticalGroup(
	    	layout.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(layout.createSequentialGroup()
	    			.addGap(27)
	    			.addComponent(welcomeLbl)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(btnSupplies, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(btnProgReport, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
	    			.addGap(29)
	    			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(btnEquipment, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(btnFundAcc, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
	    			.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
	    			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(btnStaff, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(btnExpReport, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
	    			.addGap(27)
	    			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(editButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(updateProfileBtn, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
	    			.addGap(85)
	    			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lgtButn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
	    );
	    layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnExpReport, btnFundAcc, btnProgReport, updateProfileBtn});
	    getContentPane().setLayout(layout);
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
		
	    btnStaff.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		new ManageStaffPage(urlms, currentLab, urlmsCont).setVisible(true);;
	    		setVisible(false);
	    	}
	    });
	
	    btnEquipment.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		new ManageEquipmentPage(urlms, currentLab, urlmsCont).setVisible(true);
	    		setVisible(false);
	    	}
	    });
	
	    btnSupplies.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		new ManageSupplyPage(urlms, currentLab, urlmsCont).setVisible(true);
	    		setVisible(false);
	    	}
	    });
	    
	    backBtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		new LabSelectionPage(urlms,urlmsCont.getActiveUser().getEmail(), urlmsCont).setVisible(true);
	    		setVisible(false);
	    	}
	    });
	
	    lgtButn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	urlmsCont.logout();
				new LoginPage(urlms).setVisible(true);
				setVisible(false);
	    	}
	    });
	    
	    editButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				new EditLabPage(urlms, currentLab, urlmsCont).setVisible(true);
				setVisible(false);
	    	}
	    });
	   
	    btnFundAcc.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
				new ManageFundingAccountPage(urlms, currentLab, urlmsCont).setVisible(true);
				setVisible(false);
	    	}
	    });
	    
	    updateProfileBtn.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
				new UpdateProfilePage(urlms, currentLab, urlmsCont).setVisible(true);
				setVisible(false);
	    	}
	    });
	    pack();
		// makes window appear in center of screen
		this.setLocationRelativeTo(null);
	}
	

}