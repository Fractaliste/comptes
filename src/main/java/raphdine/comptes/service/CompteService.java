/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raphdine.comptes.service;

import java.util.List;
import raphdine.comptes.modele.Ecriture;
import raphdine.comptes.service.dao.EcritureDao;

/**
 *
 * @author Raphiki
 */
public class CompteService implements ICompteService {

    private static final EcritureDao ECRITURE_DAO = new EcritureDao();

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
