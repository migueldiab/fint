package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.EntidadFinanciera;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author migueldiab
 */
@Stateless
public class EntidadFinancieraManager extends AbstractManager<EntidadFinanciera> implements EntidadFinancieraManagerLocal {

  public EntidadFinancieraManager() {
    super(EntidadFinanciera.class);
  }

  @Override
  public EntidadFinanciera buscarPorNombre(String nombre) {
    Query q = getEntityManager().createQuery("SELECT e FROM EntidadFinanciera e WHERE e.nombre = :nombre");
    q.setParameter("nombre", nombre);
    q.setMaxResults(1);
    final List<EntidadFinanciera> lista = q.getResultList();
    EntidadFinanciera result = null;
    if (1 == lista.size()) {
      result = lista.get(0);
    }
    return result;
  }
}
