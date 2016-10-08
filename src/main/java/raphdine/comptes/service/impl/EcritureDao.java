/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raphdine.comptes.service.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import raphdine.comptes.modele.Ecriture;
import raphdine.comptes.service.ServiceLocator;
import raphdine.comptes.utils.Logger;
import raphdine.comptes.utils.Transactional;

/**
 *
 * @author Raphiki
 */
public class EcritureDao extends AbstractDao {

    private static final Logger LOGGER = Logger.getInstance();
    private final EntityManager em;

    public EcritureDao() {
        em = ServiceLocator.getInstance().getSession().getEntityManagerFactory().createEntityManager();
    }

    public List<Ecriture> getAll() {
        CriteriaQuery<Ecriture> q = createQuery();
        q.select(q.from(Ecriture.class));
        TypedQuery<Ecriture> r = em.createQuery(q);
        LOGGER.debug("Nb écritures actuellement : {}", r.getResultList().size());
        return r.getResultList();
    }

    @Transactional
    public void update(Ecriture ecriture) {

        LOGGER.debug("Ecriture {} attached ? {}", ecriture, em.contains(ecriture));
        em.persist(ecriture);

    }

    private CriteriaQuery<Ecriture> createQuery() {
        CriteriaBuilder c = em.getCriteriaBuilder();
        CriteriaQuery<Ecriture> q = c.createQuery(Ecriture.class);
        return q;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
