/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raphdine.comptes.service.impl;

import java.util.List;
import raphdine.comptes.modele.Ecriture;
import raphdine.comptes.service.ICompteService;
import raphdine.comptes.utils.DaoProxy;

/**
 *
 * @author Raphiki
 */
public class CompteService implements ICompteService {

    private static final EcritureDao ECRITURE_DAO = DaoProxy.forClass(EcritureDao.class);

    @Override
    public Ecriture getLine(int rowIndex, int columnIndex) {
        return ECRITURE_DAO.getAll().get(0);
    }

    @Override
    public List<Ecriture> getAllLines() {
        return ECRITURE_DAO.getAll();
    }

    @Override
    public void update(Ecriture ecriture) {
        ECRITURE_DAO.update(ecriture);
    }

}
