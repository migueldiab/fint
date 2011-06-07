/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.fint.modelo.facades;

import edu.ort.dcomp.fint.modelo.EntidadFinanciera;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author migueldiab
 */
@Local
public interface EntidadFinancieraManagerLocal {

  void create(EntidadFinanciera tipoCuenta);

  void edit(EntidadFinanciera tipoCuenta);

  void remove(EntidadFinanciera tipoCuenta);

  EntidadFinanciera find(Object id);

  List<EntidadFinanciera> findAll();

  List<EntidadFinanciera> findRange(int[] range);

  int count();

}
