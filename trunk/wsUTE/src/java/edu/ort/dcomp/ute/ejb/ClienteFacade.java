/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.ute.ejb;

import edu.ort.dcomp.ute.modelo.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author migueldiab
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {
  @PersistenceContext(unitName = "wsUTEPU")
  private EntityManager em;

  protected EntityManager getEntityManager() {
    return em;
  }

  public ClienteFacade() {
    super(Cliente.class);
  }

  public Cliente findByCi(long ciCliente) {
    Query q = getEntityManager().createQuery("SELECT c FROM Cliente c WHERE c.ci = :ci");
    q.setParameter("ci", ciCliente);
    q.setMaxResults(2);
    final List<Cliente> lista = q.getResultList();
    Cliente resultUsuario = null;
    if (1 == lista.size()) {
      resultUsuario = lista.get(0);
    }
    return resultUsuario;
  }

}
