package ca.mcgill.ecse321.urlms.view;

import java.util.List;

import javax.swing.JFrame;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.ExpenseReport;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.ProgressUpdate;
import ca.mcgill.ecse321.urlms.model.URLMS;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class ProgressReportPage extends JFrame{
	
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 5985668010917130375L;
	/**
	 * URLMS system
	 */
	private URLMS urlms;
	/**
	 * URLMS controller
	 */
	private URLMSController urlmsCont;
	/**
	 * Lab whose expense reports director is viewing
	 */
	private Laboratory currentLab;
	/**
	 * List containing all progress updates of lab
	 */
	private List<ProgressUpdate> progressList;
	/**
	 * Table containing all progresses
	 */
	private JTable progTable;
	/**
	 * Text area responsible for displaying selected progress report that director wants to view in UI.
	 */
	private JTextArea selectedProg;
	
	/**
	 *Constructor for page to view all progress reports related to a lab.
	 * @param urlms URLMS system
	 * @param currentLab Lab whose progress reports director is viewing
	 * @param urlmsCont URLMS controller
	 */
	public ProgressReportPage(URLMS urlms, Laboratory currentLab, URLMSController urlmsCont) {
		try {
	           for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	               if ("Nimbus".equals(info.getName())) {
	                   javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                   break;
	               }
	           }
	       } catch (ClassNotFoundException ex) {
	           java.util.logging.Logger.getLogger(ProgressReportPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (InstantiationException ex) {
	           java.util.logging.Logger.getLogger(ProgressReportPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (IllegalAccessException ex) {
	           java.util.logging.Logger.getLogger(ProgressReportPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	           java.util.logging.Logger.getLogger(ProgressReportPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	       }
		this.urlms = urlms;
		this.urlmsCont = urlmsCont;
		this.currentLab = currentLab;
		if(urlmsCont.getActiveLaboratory().hasProgressUpdates()){
			progressList = urlmsCont.getActiveLaboratory().getProgressUpdates();
		}
		initComponents();
	}
	/**
	 * Method used to intialise table containing all progress reports for a labs
	 */
	private void initialiseTable(){
		if(progressList != null && progressList.size() > 0){
			String reportArray[] = new String[progressList.size()];
			int i = 0;
			for(ProgressUpdate report : progressList){
				reportArray[i] = "Progress Report " + Integer.toString(i + 1);
				Object[] o = new Object[2];
				o[0] = reportArray[i];
				o[1] = report.getTitle();
				((DefaultTableModel) progTable.getModel()).addRow(o);
				i++;
			}
		}
	}
	/**
	 * Method used to initialise frame for ProgressReportPage
	 */
	private void initComponents() {
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(14, 96, 131));
		headerPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnBack.setBackground(new Color(255, 255, 13));
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnLogout.setBackground(Color.RED);
		
		selectedProg = new JTextArea();
		selectedProg.setFont(new Font("Monospaced", Font.PLAIN, 18));
		selectedProg.setEditable(false);
		
		JLabel lblSelectedProg = new JLabel("Contents of Selected Report:");
		lblSelectedProg.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 510, Short.MAX_VALUE)
					.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
				.addComponent(headerPanel, GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSelectedProg)
					.addContainerGap(693, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(selectedProg, GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(headerPanel, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblSelectedProg)
					.addGap(18)
					.addComponent(selectedProg, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		progTable = new JTable();
		progTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Progress Report", "Report Title and Date"
			}
		)
		{public boolean isCellEditable(int row, int column){return false;}}//This causes all cells to be not editable)
		);
		progTable.setShowGrid(true);
		progTable.setRowHeight(40);
		progTable.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		progTable.setCellSelectionEnabled(true);
		progTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(progTable);
		JTableHeader expHeader = progTable.getTableHeader();
		expHeader.setFont(new java.awt.Font("Lucida Grande", 1, 18));
		initialiseTable();
		JLabel lblProgRep = new JLabel("Progress Reports");
		lblProgRep.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgRep.setForeground(Color.WHITE);
		lblProgRep.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 27));
		headerPanel.add(lblProgRep, BorderLayout.CENTER);
		getContentPane().setLayout(groupLayout);
	    getContentPane().setBackground(new Color(216, 247, 255));
		scrollPane.getViewport().setBackground(new Color(216, 247, 255));
		
		pack();
		setLocationRelativeTo(null);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DirectorLabPage(urlms, urlmsCont.getActiveLaboratory(), urlmsCont).setVisible(true);
				setVisible(false);
			}
		});
		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				urlmsCont.logout();
				new LoginPage(urlms).setVisible(true);
				setVisible(false);
			}
		});
		
		progTable.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
			    if (e.getClickCount() == 2) {
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();
			      selectedProg.setText(progressList.get(row).getReport());
			     }
			  }
			});
	}
}
