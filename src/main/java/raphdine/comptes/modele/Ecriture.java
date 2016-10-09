/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raphdine.comptes.modele;

import java.math.BigDecimal;
import java.util.Calendar;
import javax.persistence.*;
import raphdine.comptes.utils.DateUtils;

/**
 *
 * @author Raphiki
 */
@Entity
@Table
public class Ecriture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date_ecriture;

    @Column
    @Enumerated
    private CategorieEnum categorie;

    @Column
    private String intitule;

    @Column
    private BigDecimal debit;

    @Column
    private BigDecimal credit;

    @Column
    private String remboursement;

    @Column(name = "numero_cheque")
    private String numeroCheque;

    @Column(name = "passe_en_banque")
    private Boolean passeEnBanque;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the date_ecriture
     */
    public Calendar getDate() {
        return date_ecriture;
    }

    /**
     * @param date_ecriture the date to set
     */
    public void setDate(Calendar date_ecriture) {
        this.date_ecriture = date_ecriture;
    }

    /**
     * @return the categorie
     */
    public CategorieEnum getCategorie() {
        return categorie;
    }

    /**
     * @param categorie the categorie to set
     */
    public void setCategorie(CategorieEnum categorie) {
        this.categorie = categorie;
    }

    /**
     * @return the intitule
     */
    public String getIntitule() {
        return intitule;
    }

    /**
     * @param intitule the intitule to set
     */
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    /**
     * @return the debit
     */
    public BigDecimal getDebit() {
        return debit;
    }

    /**
     * @param debit the debit to set
     */
    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    /**
     * @return the credit
     */
    public BigDecimal getCredit() {
        return credit;
    }

    /**
     * @param credit the credit to set
     */
    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    /**
     * @return the passeEnBanque
     */
    public Boolean getPasseEnBanque() {
        return passeEnBanque;
    }

    /**
     * @param passeEnBanque the passeEnBanque to set
     */
    public void setPasseEnBanque(Boolean passeEnBanque) {
        this.passeEnBanque = passeEnBanque;
    }

    @Override
    public String toString() {
        return "ID : " + getId() + ", Date : " + DateUtils.format(getDate()) + ", Intitulé : " + getIntitule() + ", Catégorie : " + getCategorie() + ", Remboursement : " + getRemboursement() + ", Banque : " + getPasseEnBanque() + ", Débit : " + getDebit() + ", Crédit : " + getCredit();
    }

    /**
     * @return the remboursement
     */
    public String getRemboursement() {
        return remboursement;
    }

    /**
     * @param remboursement the remboursement to set
     */
    public void setRemboursement(String remboursement) {
        this.remboursement = remboursement;
    }

    /**
     * @return the numeroCheque
     */
    public String getNumeroCheque() {
        return numeroCheque;
    }

    /**
     * @param numeroCheque the numeroCheque to set
     */
    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

}
