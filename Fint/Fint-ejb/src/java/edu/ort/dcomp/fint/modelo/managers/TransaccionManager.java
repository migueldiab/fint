package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Servicio;
import edu.ort.dcomp.fint.modelo.Transaccion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author migueldiab
 */
@Stateless
public class TransaccionManager extends AbstractManager<Transaccion> implements TransaccionManagerLocal {

  public TransaccionManager() {
    super(Transaccion.class);
  }

  @Override
  public boolean existe(Servicio servicioAsociado, String numero) {
    Query q = getEntityManager().createQuery("SELECT t FROM Transaccion t WHERE t.servicio = :servicio AND t.numero = :numero");
    q.setParameter("servicio", servicioAsociado);
    q.setParameter("numero", numero);
    q.setMaxResults(1);
    return 1 == q.getResultList().size();
  }

  @Override
  public Transaccion buscarPorServicioNumero(Servicio servicioAsociado, String numero) {
    Query q = getEntityManager().createQuery("SELECT t FROM Transaccion t WHERE t.servicio = :servicio AND t.numero = :numero");
    q.setParameter("servicio", servicioAsociado);
    q.setParameter("numero", numero);
    q.setMaxResults(1);
    final List<Transaccion> lista = q.getResultList();
    Transaccion result = null;
    if (1 == lista.size()) {
      result = lista.get(0);
    }
    return result;
  }

}
