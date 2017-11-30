package ca.mcgill.ecse321.urlms.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
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
	
	private JPanel contentPane;
	/**
	 * URLMS System
	 */
	private URLMS urlms;
	



	public DirectorLabPage(URLMS urlms) {
		this.urlms = urlms;
		
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
		
		JButton btnTrackLab = new JButton("Track Lab");
		btnTrackLab.setBackground(new Color(0, 191, 255));
		btnTrackLab.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		
		JButton btnExpReport = new JButton("Generate Expense Report");
		btnExpReport.setBackground(new Color(0, 191, 255));
		btnExpReport.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		

		/*btnStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StaffPage(urlms);
				StaffPage.f.setVisible(true);
				DirectorMainPage.f.setVisible(false);
			}
		});*/
		
		/*btnEquipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EquipmentPage(urlms);
				EquipmentPage.f.setVisible(true);
				DirectorMainPage.f.setVisible(false);
			}
		});*/
		
		/*btnSupplies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SupplyPage(urlms);
				SupplyPage.f.setVisible(true);
				DirectorMainPage.f.setVisible(false);
			}
		});*/
		
		/*btnLabExpsenses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LabExpensesPage(urlms);
				LabExpensesPage.f.setVisible(true);
				DirectorMainPage.f.setVisible(false);
			}
		});*/
		
		// Layout
		getContentPane().setLayout(new GridBagLayout());
	    GroupLayout layout = new GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
	    
	    layout.setHorizontalGroup(
	    	layout.createParallelGroup()
	    		.addGroup(layout.createSequentialGroup()
	    				.addGap((screenSize.width/2)/5)
	    				.addComponent(btnSupplies)
    					.addComponent(btnProgReport))
	    		.addGroup(layout.createSequentialGroup()
	    				.addGap((screenSize.width/2)/5)
	    				.addComponent(btnEquipment)
    					.addComponent(btnTrackLab))
	    		.addGroup(layout.createSequentialGroup()
	    				.addGap((screenSize.width/2)/5)
	    				.addComponent(btnStaff)
    					.addComponent(btnExpReport))
	    		.addGroup(layout.createSequentialGroup()
	    				.addGap((screenSize.width/2)/5)
	    				.addComponent(btnFundAcc))
	    );
	    layout.setVerticalGroup(
	    	layout.createSequentialGroup()
	    		.addGap((screenSize.height/2)/5)
	    		.addGroup(layout.createParallelGroup()
	    				.addComponent(btnSupplies)
    					.addComponent(btnProgReport))
	    		.addGroup(layout.createParallelGroup()
	    				.addComponent(btnEquipment)
    					.addComponent(btnTrackLab))
	    		.addGroup(layout.createParallelGroup()
	    				.addComponent(btnStaff)
    					.addComponent(btnExpReport)
    					)
	    		.addComponent(btnFundAcc)
	    );
	    
	    layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {btnProgReport, btnTrackLab, btnExpReport, btnSupplies, btnEquipment, btnStaff, btnFundAcc});
	    /*GroupLayout groupLayout = new GroupLayout(getContentPane());
		JButton btnHome = new JButton("Home");
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnHome)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnStaff)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEquipment)
							.addGap(12)
							.addComponent(btnSupplies)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExpReport))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(215)
							.addComponent(welcomeLbl)))
					.addContainerGap(9, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(welcomeLbl)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSupplies)
						.addComponent(btnHome)
						.addComponent(btnStaff)
						.addComponent(btnExpReport)
						.addComponent(btnEquipment))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);*/
		
		// makes window appear in center of screen
		this.setLocationRelativeTo(null);
	}

}
