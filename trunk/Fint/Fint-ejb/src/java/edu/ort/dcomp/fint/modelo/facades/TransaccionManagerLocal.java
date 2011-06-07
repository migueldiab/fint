/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.fint.modelo.facades;

import edu.ort.dcomp.fint.modelo.Transaccion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author migueldiab
 */
@Local
public interface TransaccionManagerLocal {

  void persist(Transaccion transaccion);

  void merge(Transaccion transaccion);

  void remove(Transaccion transaccion);

  Transaccion find(Object id);

  List<Transaccion> findAll();

  List<Transaccion> findRange(int[] range);

  int count();

}
