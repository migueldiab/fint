package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Servicio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author migueldiab
 */
@Stateless
public class ServicioManager extends AbstractManager<Servicio> implements ServicioManagerLocal {

  public ServicioManager() {
    super(Servicio.class);
  }

  @Override
  public Servicio buscarPorNombre(String nombre) {
    Query q = getEntityManager().createQuery("SELECT s FROM Servicio s WHERE s.nombre = :nombre");
    q.setParameter("nombre", nombre);
    q.setMaxResults(1);
    final List<Servicio> lista = q.getResultList();
    Servicio result = null;
    if (1 == lista.size()) {
      result = lista.get(0);
    }
    return result;
  }

  @Override
  public List<Servicio> buscarServiciosConectados() {
    Query q = getEntityManager().createQuery("SELECT s FROM Servicio s");
    final List<Servicio> result = q.getResultList();
    return result;
  }

}
