/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Servicio;
import edu.ort.dcomp.fint.modelo.Usuario;
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

}
