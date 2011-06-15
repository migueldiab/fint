/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Cuenta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author migueldiab
 */
@Local
public interface CuentaManagerLocal {

  void persist(Cuenta cuenta);

  Cuenta merge(Cuenta cuenta);

  void remove(Cuenta cuenta);

  Cuenta find(Object id);

  List<Cuenta> findAll();

  List<Cuenta> findRange(int[] range);

  int count();

}
