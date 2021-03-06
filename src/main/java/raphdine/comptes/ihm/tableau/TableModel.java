/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raphdine.comptes.ihm.tableau;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import raphdine.comptes.modele.CategorieEnum;
import raphdine.comptes.modele.Ecriture;
import raphdine.comptes.service.ICompteService;
import raphdine.comptes.service.ServiceLocator;
import raphdine.comptes.utils.DateUtils;
import raphdine.comptes.utils.Logger;

/**
 *
 * @author Raphiki
 */
public class TableModel extends AbstractTableModel {

    private static final Logger LOGGER = Logger.getInstance();
    private final static ICompteService SERVICE = ServiceLocator.getInstance().getComptesService();

    private List<Ecriture> ecritures;

    @Override
    public int getRowCount() {
        return getEcritures().size();
    }

    @Override
    public int getColumnCount() {
        return ColonneMapping.values().length;
    }

    @Override
    public String getColumnName(int column) {
        return ColonneMapping.getColonneByIndex(column).getNomColonne();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Ecriture ecriture = getEcriture(rowIndex);
        switch (ColonneMapping.getColonneByIndex(columnIndex)) {
            case DATE:
                return ecriture.getDate();
            case CATEGORIE:
                return ecriture.getCategorie();
            case INTITULE:
                return ecriture.getIntitule();
            case DEBIT:
                return ecriture.getDebit();
            case CREDIT:
                return ecriture.getCredit();
            case TOTAL:
                return getTotal(ecriture, rowIndex);
        }

        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != ColonneMapping.TOTAL.getIndexColonne();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        Ecriture ecriture = getEcriture(rowIndex);
        switch (ColonneMapping.getColonneByIndex(columnIndex)) {
            case DATE:
                if (aValue == null) {
                    return;
                }
                ecriture.setDate(DateUtils.dateToCalendar((Date) aValue));
                break;
            case CATEGORIE:
                ecriture.setCategorie((CategorieEnum) aValue);
                break;
            case INTITULE:
                ecriture.setIntitule((String) aValue);
                break;
            case DEBIT:
                ecriture.setDebit((BigDecimal) aValue);
                break;
            case CREDIT:
                ecriture.setCredit((BigDecimal) aValue);
                break;
        }
        save(ecriture);
    }

    private BigDecimal getTotal(Ecriture ecriture, int row) {
        BigDecimal total = (row == 0) ? BigDecimal.ZERO : getTotal(getEcriture(row - 1), row - 1);
        if (ecriture.getCredit() != null) {
            total = total.add(ecriture.getCredit());
        }
        if (ecriture.getDebit() != null) {
            total = total.subtract(ecriture.getDebit());
        }
        return total;
    }

    private List<Ecriture> getEcritures() {
        if (ecritures == null) {
            ecritures = SERVICE.getAllLines();
        }
        return ecritures;
    }

    private Ecriture getEcriture(int rowIndex) {
        return getEcritures().get(rowIndex);
    }

    private void save(Ecriture ecriture) {
        SERVICE.update(ecriture);
    }
}
