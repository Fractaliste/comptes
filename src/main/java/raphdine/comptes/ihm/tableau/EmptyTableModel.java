/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raphdine.comptes.ihm.tableau;

/**
 *
 * @author Raphiki
 */
public class EmptyTableModel extends TableModel {

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

}
