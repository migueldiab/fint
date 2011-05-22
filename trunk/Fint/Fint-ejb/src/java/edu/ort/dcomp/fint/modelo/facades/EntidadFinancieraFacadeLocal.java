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
public interface EntidadFinancieraFacadeLocal {

  void create(EntidadFinanciera entidadFinanciera);

  void edit(EntidadFinanciera entidadFinanciera);

  void remove(EntidadFinanciera entidadFinanciera);

  EntidadFinanciera find(Object id);

  List<EntidadFinanciera> findAll();

  List<EntidadFinanciera> findRange(int[] range);

  int count();

}
