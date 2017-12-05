package ca.mcgill.ecse321.urlms.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class SelectProgressRepPage extends JFrame{
	private JTextField textField;
	public SelectProgressRepPage() {
		
		this.setTitle("Select Progress Report");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(14, 96, 131));
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(14, 96, 131));
		
		JButton btnViewReport = new JButton("View Report");
		btnViewReport.setForeground(Color.WHITE);
		btnViewReport.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		btnViewReport.setBackground(new Color(14, 96, 131));
		
		JButton button = new JButton("Back");
		button.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		button.setBackground(new Color(255, 255, 13));
		
		JButton button_1 = new JButton("Logout");
		button_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		button_1.setBackground(Color.RED);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(headerPanel, GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 452, Short.MAX_VALUE)
					.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(269)
					.addComponent(btnViewReport, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(336, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(123)
					.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 531, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(168, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(headerPanel, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addGap(88)
					.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnViewReport, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblP = new JLabel("Please enter the ID of the report you wish to view:");
		lblP.setHorizontalAlignment(SwingConstants.CENTER);
		lblP.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblP.setForeground(Color.WHITE);
		centerPanel.add(lblP, BorderLayout.NORTH);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		headerPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Weekly Progress Reports");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 26));
		headerPanel.add(lblNewLabel, BorderLayout.CENTER);
		
		getContentPane().setLayout(groupLayout);
		getContentPane().setBackground(new Color(216, 247, 255));
		
		pack();
		setLocationRelativeTo(null);
	}

}
