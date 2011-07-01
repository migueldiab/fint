package edu.ort.dcomp.ute.managers;

import edu.ort.common.utils.DateTime;
import edu.ort.dcomp.ute.modelo.Cuenta;
import edu.ort.dcomp.ute.modelo.Factura;
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

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public FacturaFacade() {
    super(Factura.class);
  }
  
  public List<Factura> buscarPorCuentaEstado(Cuenta unaCuenta, Estado estado) {
    Query q = getEntityManager().createQuery("SELECT f FROM Factura f WHERE f.cuenta = :cuenta AND f.estado = :estado");
    q.setParameter("cuenta", unaCuenta);
    q.setParameter("estado", estado);
    final List<Factura> lista = q.getResultList();
    return lista;
  }

  public List<Factura> buscarPorCuentaDiasPasados(Cuenta unaCuenta, int dias) {
    Query q = getEntityManager().createQuery("SELECT f FROM Factura f WHERE f.cuenta = :cliente AND f.fechaEmision > :fechaInicio");
    q.setParameter("cuenta", unaCuenta);
    q.setParameter("fechaInicio", DateTime.addToNow(Calendar.DATE, -dias));
    final List<Factura> lista = q.getResultList();
    return lista;
  }


}
