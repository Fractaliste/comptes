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
public enum ColonneMapping {

    DATE(0, "Date"),
    CATEGORIE(1, "Catégorie"),
    INTITULE(2, "Intitulé"),
    DEBIT(3, "Débit"),
    CREDIT(4, "Crédit"),
    TOTAL(5, "Total");

    private final int indexColonne;
    private final String nomColonne;

    private ColonneMapping(int indexColonne, String nomColonne) {
        this.indexColonne = indexColonne;
        this.nomColonne = nomColonne;
    }

    /**
     * @return the numColumn
     */
    public int getIndexColonne() {
        return indexColonne;
    }

    /**
     * @return the nomColonne
     */
    public String getNomColonne() {
        return nomColonne;
    }

    public static ColonneMapping getColonneByIndex(int index) {
        for (ColonneMapping colonne : values()) {
            if (colonne.getIndexColonne() == index) {
                return colonne;
            }
        }
        return null;
    }
}
