package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Cuenta;
import edu.ort.dcomp.fint.modelo.Cuenta.Tipo;
import javax.ejb.Stateless;

/**
 *
 * @author migueldiab
 */
@Stateless
public class CuentaManager extends AbstractManager<Cuenta> implements CuentaManagerLocal {
  public CuentaManager() {
    super(Cuenta.class);
  }

}
