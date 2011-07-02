package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.EntidadFinanciera;
import java.util.List;
import javax.ejb.LocalBean;

/**
 *
 * @author migueldiab
 */
@LocalBean
public interface EntidadFinancieraManagerLocal {

  void persist(EntidadFinanciera tipoCuenta);

  EntidadFinanciera merge(EntidadFinanciera tipoCuenta);

  void remove(EntidadFinanciera tipoCuenta);

  EntidadFinanciera find(Object id);

  List<EntidadFinanciera> findAll();

  List<EntidadFinanciera> findRange(int[] range);

  int count();

}
