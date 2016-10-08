/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raphdine.comptes.ihm.tableau;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import raphdine.comptes.modele.CategorieEnum;

/**
 *
 * @author Raphiki
 */
class CategorieCellEditor extends DefaultCellEditor {

    public static CategorieCellEditor create() {
        JComboBox jComboBox = new JComboBox(CategorieEnum.values());
        return new CategorieCellEditor(jComboBox);
    }

    public CategorieCellEditor(JComboBox comboBox) {
        super(comboBox);
    }

}
