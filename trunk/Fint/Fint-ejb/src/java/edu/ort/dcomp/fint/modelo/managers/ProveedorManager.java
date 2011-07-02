package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Proveedor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author migueldiab
 */
@Stateless
public class ProveedorManager extends AbstractManager<Proveedor> implements ProveedorManagerLocal {
  public ProveedorManager() {
    super(Proveedor.class);
  }

  @Override
  public Proveedor buscarPorNombre(String nombre) {
    Query q = getEntityManager().createQuery("SELECT p FROM Proveedor p WHERE p.nombre = :nombre");
    q.setParameter("nombre", nombre);
    q.setMaxResults(1);
    final List<Proveedor> lista = q.getResultList();
    Proveedor result = null;
    if (1 == lista.size()) {
      result = lista.get(0);
    }
    return result;
  }


}
