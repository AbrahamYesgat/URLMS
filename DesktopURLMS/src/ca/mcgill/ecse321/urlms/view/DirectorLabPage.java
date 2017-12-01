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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

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



	public DirectorLabPage(URLMS urlms) {
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
	
	private void initComponents() {
		
		this.setTitle("University Lab Management System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(new java.awt.Rectangle(0, 0, screenSize.width/2, screenSize.height/2));
		
		// Initialization of all buttons and labels
		JLabel welcomeLbl = new JLabel("Welcome Director");
		welcomeLbl.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 24));
		welcomeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnStaff = new JButton("Manage Staff");
		btnStaff.setBackground(new Color(0, 191, 255));
		btnStaff.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		
		JButton btnEquipment = new JButton("Manage Equipment");
		btnEquipment.setBackground(new Color(0, 191, 255));
		btnEquipment.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		
		JButton btnSupplies = new JButton("Manage Supplies");
		btnSupplies.setBackground(new Color(0, 191, 255));
		btnSupplies.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		
		JButton btnFundAcc = new JButton("Manage Funding Accounts");
		btnFundAcc.setBackground(new Color(0, 191, 255));
		btnFundAcc.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		
		JButton btnProgReport = new JButton("View Weekly Progress Report");
		btnProgReport.setBackground(new Color(0, 191, 255));
		btnProgReport.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		
		JButton btneditLab = new JButton("Edit Lab");
		btneditLab.setBackground(new Color(0, 191, 255));
		btneditLab.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		
		JButton btnExpReport = new JButton("Generate Expense Report");
		btnExpReport.setBackground(new Color(0, 191, 255));
		btnExpReport.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnBack.setBackground(new Color(255, 255, 0));
		
		// Layout
	    GroupLayout layout = new GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
	    
	    layout.setHorizontalGroup(
	    	layout.createParallelGroup()
	    		.addGroup(layout.createSequentialGroup()
    				.addGap(screenSize.width/5)
    				.addComponent(welcomeLbl))
	    		.addGroup(layout.createSequentialGroup()
	    				.addGap((screenSize.width/2)/4)
	    				.addComponent(btnSupplies)
    					.addComponent(btnProgReport))
	    		.addGroup(layout.createSequentialGroup()
	    				.addGap((screenSize.width/2)/4)
	    				.addComponent(btnEquipment)
    					.addComponent(btneditLab))
	    		.addGroup(layout.createSequentialGroup()
	    				.addGap((screenSize.width/2)/4)
	    				.addComponent(btnStaff)
    					.addComponent(btnExpReport))
	    		.addGroup(layout.createSequentialGroup()
	    				.addGap((screenSize.width/2)/4)
	    				.addComponent(btnFundAcc))
	    );
	    layout.setVerticalGroup(
	    	layout.createSequentialGroup()
	    		.addComponent(welcomeLbl)
	    		.addGap((screenSize.height/2)/5)
	    		.addGroup(layout.createParallelGroup()
	    				.addComponent(btnSupplies)
    					.addComponent(btnProgReport))
	    		.addGroup(layout.createParallelGroup()
	    				.addComponent(btnEquipment)
    					.addComponent(btneditLab))
	    		.addGroup(layout.createParallelGroup()
	    				.addComponent(btnStaff)
    					.addComponent(btnExpReport)
    					)
	    		.addComponent(btnFundAcc)
	    );
	    
	    layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {btnProgReport, btneditLab, btnExpReport, btnSupplies, btnEquipment, btnStaff, btnFundAcc});
		
	    btnStaff.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		new ManageStaffPage(urlms).setVisible(true);;
	    		setVisible(false);
	    	}
	    });
	
	    btnEquipment.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		new ManageEquipmentPage(urlms).setVisible(true);
	    		setVisible(false);
	    	}
	    });
	
	    btnSupplies.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		new ManageSupplyPage(urlms).setVisible(true);
	    		setVisible(false);
	    	}
	    });
	
	    /*btnExpReport.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		new LabExpensesPage(urlms);
	    		LabExpensesPage.f.setVisible(true);
	    		setVisible(false);
	    	}
	    });*/
		// makes window appear in center of screen
		this.setLocationRelativeTo(null);
	}

}