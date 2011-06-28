/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Servicio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author migueldiab
 */
@Local
public interface ServicioManagerLocal {

  void persist(Servicio servicio);

  Servicio merge(Servicio servicio);

  void remove(Servicio servicio);

  Servicio find(Object id);

  List<Servicio> findAll();

  List<Servicio> findRange(int[] range);

  int count();

  public Servicio buscarPorNombre(String SERVICIO_ASOCIADO);

}
