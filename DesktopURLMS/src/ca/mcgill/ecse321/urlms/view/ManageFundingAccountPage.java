package ca.mcgill.ecse321.urlms.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.URLMS;

public class ManageFundingAccountPage extends JFrame{

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -3895535642337920548L;
	/**
	 * URLMS system
	 */
	private URLMS urlms;
	/**
	 * Instance of URLMS controller
	 */
	private URLMSController urlmsCont;
	/**
	 * Current lab whose funding account director is viewing/editing
	 */
	private Laboratory currentLab;
	/**
	 * Textfield specifying number of funding accounts of the lab
	 */
	private JTextField fundAccQuantity;
	/**
	 * Textfield specifying account number of new funding account to be added
	 */
	private JTextField newFundNum;
	/**
	 * Textfield specifying balance of new funding account to be added
	 */
	private JTextField newFundBalance;
	/**
	 * Dropdown specifying which funding account to edit
	 */
	private JComboBox<Integer> modifyFundAcc;
	/**
	 * Textfield specifying balance of which funding account to edit
	 */
	private JTextField modifyBalance;
	/**
	 * Button used to add a new funding account
	 */
	private JButton addFundAccBtn;
	/**
	 * Button used to modify a funding account
	 */
	private JButton modifyFundAccBtn;
	/**
	 * Table containing all funding accounts of active lab
	 */
	private JTable fundAccTable;
	/**
	 * Constructor of manage funding account frame
	 * @param urlms current URLMS system
	 * @param lab current lab user is viewing
	 * @param urlmsCont instance of URLMS controller
	 */
	public ManageFundingAccountPage(URLMS urlms, Laboratory lab, URLMSController urlmsCont){
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
	           java.util.logging.Logger.getLogger(ManageFundingAccountPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (InstantiationException ex) {
	           java.util.logging.Logger.getLogger(ManageFundingAccountPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (IllegalAccessException ex) {
	           java.util.logging.Logger.getLogger(ManageFundingAccountPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	           java.util.logging.Logger.getLogger(ManageFundingAccountPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       }
		initComponents();
	}
	
	/**
	 * Method used to initialize ManageFundingAccountPage frame
	 */
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Manage Funding Account Page");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		// initialization of components
		JLabel lblManageFundingAccount = new JLabel("Manage FundingAccount");
		lblManageFundingAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageFundingAccount.setFont(new Font("Modern No. 20", Font.PLAIN, 28));
		JLabel lblFundingAccountQuant = new JLabel("Total quantity of FundingAccount:");
		lblFundingAccountQuant.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		JButton lgtBtn = new JButton("Logout");
		lgtBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lgtBtn.setBackground(Color.RED);
		fundAccQuantity = new JTextField();
		fundAccQuantity.setEditable(false);
		fundAccQuantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fundAccQuantity.setColumns(10);
		
	    getContentPane().setBackground(new Color(216, 247, 255));
	    scrollPane.getViewport().setBackground(new Color(216, 247, 255));
		
	}

}
