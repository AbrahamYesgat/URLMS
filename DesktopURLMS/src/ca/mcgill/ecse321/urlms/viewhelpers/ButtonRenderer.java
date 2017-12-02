package ca.mcgill.ecse321.urlms.viewhelpers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer {

	  /**
	 * Serial ID
	 */
	private static final long serialVersionUID = -353451468846667999L;

	public ButtonRenderer() {
	    setOpaque(false);
	  }

	  public Component getTableCellRendererComponent(JTable table, Object value,
	      boolean isSelected, boolean hasFocus, int row, int column) {
	    if (isSelected && value == "Add") {
	      setForeground(table.getSelectionForeground());
	      setBackground(new Color(0, 255, 0));
	      setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
	    } else if(value == "Add"){
	      setForeground(table.getForeground());
	      setBackground(new Color(0, 255, 0));
	      setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
	    } else if(isSelected && value == "Remove"){
	      setForeground(table.getSelectionForeground());
		  setBackground(new Color(255, 0, 0));
		  setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
	    } else if(value == "Remove"){
	      setForeground(table.getSelectionForeground());
		  setBackground(new Color(255, 0, 0));
		  setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
	    }
	    setText((value == null) ? "" : value.toString());
	    return this;
	  }
	}
