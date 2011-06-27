/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.ute.modelo;

import edu.ort.common.utils.DateTime;
import edu.ort.dcomp.ute.modelo.Factura.Estado;
import java.util.Calendar;
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

  public List<Factura> buscarPorClienteEstado(Cliente unCliente, Estado estado) {
    Query q = getEntityManager().createQuery("SELECT f FROM Factura f WHERE f.cliente = :cliente AND f.estado = :estado");
    q.setParameter("cliente", unCliente);
    q.setParameter("estado", estado);
    final List<Factura> lista = q.getResultList();
    return lista;
  }

  public List<Factura> buscarPorClienteDiasPasados(Cliente unCliente, int dias) {
    Query q = getEntityManager().createQuery("SELECT f FROM Factura f WHERE f.cliente = :cliente AND f.fechaEmision > :fechaInicio");
    q.setParameter("cliente", unCliente);
    q.setParameter("fechaInicio", DateTime.addToNow(Calendar.DATE, -dias));
    final List<Factura> lista = q.getResultList();
    return lista;
  }

}
