/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raphdine.comptes.modele;

import java.util.Arrays;

/**
 *
 * @author Raphiki
 */
public enum CategorieEnum {

    /**
     *
     *//**
     *
     */
    ALIMENTATION(SuperCategorieEnum.COURANT), //
    IMPOT(SuperCategorieEnum.FIXE), //
    COMMUNICATION(SuperCategorieEnum.FIXE), //
    DIVERS(SuperCategorieEnum.COURANT), //
    ELECTROMENAGER_AMEUBLEMENT(SuperCategorieEnum.COURANT), //
    EMPRUNT(SuperCategorieEnum.FIXE), //
    ENSEIGNEMENT(SuperCategorieEnum.FIXE), //
    EPARGNE(SuperCategorieEnum.FIXE),//
    FRAIS_DE_GESTION(SuperCategorieEnum.FIXE), //
    HABILLEMENT(SuperCategorieEnum.COURANT), //
    LOGEMENT(SuperCategorieEnum.COURANT), //
    LOISIR_CULTURE(SuperCategorieEnum.EXCEPTIONNEL), //
    OPERATION_BANCAIRE(SuperCategorieEnum.COURANT), //
    RESTAURANT_HOTEL_SORTIE(SuperCategorieEnum.EXCEPTIONNEL), //
    SALAIRE(SuperCategorieEnum.FIXE), //
    SANTE(SuperCategorieEnum.COURANT), //
    TRANSPORT(SuperCategorieEnum.FIXE), //
    VACANCES(SuperCategorieEnum.EXCEPTIONNEL), //
    VOITURE(SuperCategorieEnum.COURANT);

    private final SuperCategorieEnum categorie;

    private CategorieEnum(SuperCategorieEnum c) {
        this.categorie = c;
    }

    /**
     * @return the categorie
     */
    public SuperCategorieEnum getCategorie() {
        return categorie;
    }

    public static CategorieEnum[] orderedValues() {
        CategorieEnum[] values = values();
        Arrays.sort(values);
        return values;
    }
}
