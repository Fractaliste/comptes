/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raphdine.comptes.service;

import java.util.List;
import raphdine.comptes.modele.Ecriture;

/**
 *
 * @author Raphiki
 */
public interface ICompteService {

    public Ecriture getLine(int rowIndex, int columnIndex);

    public List<Ecriture> getAllLines();

    public void update(Ecriture ecriture);

}
