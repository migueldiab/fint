package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Agenda;
import edu.ort.dcomp.fint.modelo.Cuenta;
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

  @Override
  public List<Transaccion> obtenerPorCuentaOrdenadoPorFecha(Cuenta cuenta) {
    Query q = getEntityManager().createQuery("SELECT t FROM Transaccion t WHERE t.cuenta = :cuenta ORDER BY t.fechaIngreso DESC");
    q.setParameter("cuenta", cuenta);
    final List<Transaccion> result = q.getResultList();
    return result;
  }

  @Override
  public List<Transaccion> obtenerPorServicioOrdenadoPorFecha(Servicio servicio) {
    Query q = getEntityManager().createQuery("SELECT t FROM Transaccion t WHERE t.servicio = :servicio ORDER BY t.fechaIngreso DESC");
    q.setParameter("servicio", servicio);
    final List<Transaccion> result = q.getResultList();
    return result;
  }

  @Override
  public List<Transaccion> buscarFacturasPendientes(Agenda agenda) {
    Query q = getEntityManager().createQuery("SELECT t FROM Transaccion t "
            + "WHERE t.servicio = :servicio "
            + "AND t.estado = :estado "
            + "AND t.usuario = :usuario");
    q.setParameter("servicio", agenda.getServicio());
    q.setParameter("estado", Transaccion.Estado.PENDIENTE);
    q.setParameter("usuario", agenda.getUsuario());
    final List<Transaccion> lista = q.getResultList();
    return lista;
  }
}
