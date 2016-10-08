/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raphdine.comptes.ihm.tableau;

import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jdesktop.swingx.table.DatePickerCellEditor;
import raphdine.comptes.utils.DateUtils;
import raphdine.comptes.utils.Logger;
import raphdine.comptes.utils.SwingUtils;

/**
 *
 * @author Raphiki
 */
public class TableauPanel extends javax.swing.JPanel {

    private static final Logger LOGGER = Logger.getInstance();

    JTable table;
    private JTable newLineTable;

    /**
     * Creates new form TableauPanel
     */
    public TableauPanel() {
        initComponents();
        initMyComponents();
    }

    private void initMyComponents() {
        initTable();

        JScrollPane scollPanel = new JScrollPane(getTable());

        final javax.swing.GroupLayout layout = SwingUtils.extractGroupLayout(this);
        layout.setHorizontalGroup(layout.createParallelGroup().addComponent(scollPanel, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE).addComponent(newLineTable));
        layout.setVerticalGroup(layout.createSequentialGroup().addComponent(scollPanel).addComponent(newLineTable));
    }

    private Component getTable() {
        return table;
    }

    private void initTable() {
        // Table avec les données
        table = new JTable(new TableModel());

        DatePickerCellEditor dateEditor = new DatePickerCellEditor(DateUtils.getDefaultDateFormat());
//        dateEditor.setClickCountToStart(1);

        table.getColumnModel().getColumn(0).setCellRenderer(new DateCellRenderer());
        table.getColumnModel().getColumn(0).setCellEditor(dateEditor);
        table.getColumnModel().getColumn(1).setCellEditor(CategorieCellEditor.create());
        table.getColumnModel().getColumn(4).setCellRenderer(new EuroCellRenderer());
        table.getColumnModel().getColumn(5).setCellRenderer(new EuroCellRenderer());

        // Table de création d'une nouvelle ligne
        newLineTable = new JTable(new EmptyTableModel());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}