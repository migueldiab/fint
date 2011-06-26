/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.ute.ejb;

import edu.ort.dcomp.ute.modelo.Cliente;
import edu.ort.dcomp.ute.modelo.Factura;
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
public class FacturaFacade extends AbstractFacade<Factura> {
  @PersistenceContext(unitName = "wsUTEPU")
  private EntityManager em;

  protected EntityManager getEntityManager() {
    return em;
  }

  public FacturaFacade() {
    super(Factura.class);
  }

  public List<Factura> findByCliente(Cliente unCliente, Factura.Estado estado) {
    String query = "SELECT f FROM Factura f WHERE f.cliente = :cliente AND f.estado = :estado";
    Query q = getEntityManager().createQuery(query);
    q.setParameter("cliente", unCliente);
    q.setParameter("estado", estado);
    final List<Factura> lista = q.getResultList();
    return lista;
  }

}
