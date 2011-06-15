/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Proveedor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author migueldiab
 */
@Local
public interface ProveedorManagerLocal {

  void persist(Proveedor tipoServicio);

  Proveedor merge(Proveedor tipoServicio);

  void remove(Proveedor tipoServicio);

  Proveedor find(Object id);

  List<Proveedor> findAll();

  List<Proveedor> findRange(int[] range);

  int count();

}
