/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raphdine.comptes.ihm.tableau;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Raphiki
 */
public class EuroCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String text = "";

        if (value instanceof Float) {
            text = (Float) value + " €";
        }
        return super.getTableCellRendererComponent(table, text, isSelected, hasFocus, row, column);
    }

}
