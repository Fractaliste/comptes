/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raphdine.comptes.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import raphdine.comptes.service.IDao;

/**
 *
 * @author Raphiki
 */
public abstract class AbstractDao implements IDao {

    private EntityTransaction tx;

    public void openTransaction() {
        tx = getEntityManager().getTransaction();
        tx.begin();
    }

    public void closeTransaction() {
        tx.commit();
    }

    public abstract EntityManager getEntityManager();

}
