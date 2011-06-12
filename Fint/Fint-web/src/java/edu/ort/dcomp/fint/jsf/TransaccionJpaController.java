/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.fint.jsf;

import edu.ort.dcomp.fint.jsf.exceptions.NonexistentEntityException;
import edu.ort.dcomp.fint.modelo.Transaccion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author migueldiab
 */
public class TransaccionJpaController {

  public TransaccionJpaController() {
    emf = Persistence.createEntityManagerFactory("FintPU");
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Transaccion transaccion) {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      em.persist(transaccion);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Transaccion transaccion) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      transaccion = em.merge(transaccion);
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = transaccion.getId();
        if (findTransaccion(id) == null) {
          throw new NonexistentEntityException("The transaccion with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void destroy(Long id) throws NonexistentEntityException {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Transaccion transaccion;
      try {
        transaccion = em.getReference(Transaccion.class, id);
        transaccion.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The transaccion with id " + id + " no longer exists.", enfe);
      }
      em.remove(transaccion);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Transaccion> findTransaccionEntities() {
    return findTransaccionEntities(true, -1, -1);
  }

  public List<Transaccion> findTransaccionEntities(int maxResults, int firstResult) {
    return findTransaccionEntities(false, maxResults, firstResult);
  }

  private List<Transaccion> findTransaccionEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Transaccion.class));
      Query q = em.createQuery(cq);
      if (!all) {
        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
      }
      return q.getResultList();
    } finally {
      em.close();
    }
  }

  public Transaccion findTransaccion(Long id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Transaccion.class, id);
    } finally {
      em.close();
    }
  }

  public int getTransaccionCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Transaccion> rt = cq.from(Transaccion.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }

}
