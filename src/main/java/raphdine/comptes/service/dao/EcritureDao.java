/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raphdine.comptes.service.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import raphdine.comptes.modele.Ecriture;
import raphdine.comptes.service.ServiceLocator;
import raphdine.comptes.utils.Logger;

/**
 *
 * @author Raphiki
 */
public class EcritureDao {

    private static final Session SESSION = ServiceLocator.getInstance().getSession();
    private static final Logger LOGGER = Logger.getInstance();

    public List<Ecriture> getAll() {
        List<Ecriture> l = new ArrayList<>();
        EntityManager em = SESSION.getEntityManagerFactory().createEntityManager();
        CriteriaBuilder c = em.getCriteriaBuilder();
        CriteriaQuery<Ecriture> q = c.createQuery(Ecriture.class);
        q.select(q.from(Ecriture.class));
        TypedQuery<Ecriture> r = em.createQuery(q);
        LOGGER.debug("Nb écritures actuellement : {}", r.getResultList().size());
        return r.getResultList();
    }

}
