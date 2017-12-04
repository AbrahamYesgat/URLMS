package ca.mcgill.ecse321.urlms.viewhelpers;

import javax.swing.DefaultCellEditor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.Staff;

import javax.swing.*;

public class ButtonEditor extends DefaultCellEditor {
	  /**
	 * Serialized ID
	 */
	private static final long serialVersionUID = -2643524895898397256L;

	protected JButton button;

	private String label;
	
	private JTable myTable;
	
	private List<Staff> labStaff;
	
	private boolean isPushed;

	  public ButtonEditor(JCheckBox checkBox, JTable table, List<Staff> labStaff, URLMSController urlmsCont) {
	    super(checkBox);
	    button = new JButton();
	    myTable = table;
	    //button.setOpaque(true);
	    button.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        fireEditingStopped();
	        
	       
	      }
	    });
	  }

	  public Component getTableCellEditorComponent(JTable table, Object value,
	      boolean isSelected, int row, int column) {
	    if (isSelected && value == "Add") {
	      button.setForeground(table.getSelectionForeground());
	      button.setBackground(new Color(76, 153, 0));
	      button.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
	    } else if(value == "Add"){
	      button.setForeground(table.getForeground());
	      button.setBackground(new Color(76, 153, 0));
	      button.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
	    } if (isSelected && value == "Remove") {
		  button.setForeground(table.getSelectionForeground());
		  button.setBackground(new Color(153, 0, 0));
		  button.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		} else if(value == "Remove"){
		  button.setForeground(table.getForeground());
		  button.setBackground(new Color(153, 0, 0));
		  button.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		}
	    label = (value == null) ? "" : value.toString();
	    button.setText(label);
	    isPushed = true;
	    return button;
	  }

	  public Object getCellEditorValue() {
        isPushed = false;
	    return new String(label);
	  }

	  public boolean stopCellEditing() {
	    isPushed = false;
	    return super.stopCellEditing();
	  }

	  protected void fireEditingStopped() {
	    super.fireEditingStopped();
	  }
	}
