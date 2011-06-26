package edu.ort.dcomp.fint.controller;

import edu.ort.dcomp.fint.modelo.Cuenta;
import edu.ort.dcomp.fint.modelo.Transaccion;
import edu.ort.dcomp.fint.modelo.managers.CuentaManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author migueldiab
 */
@Stateless
public class CuentaController {

  @EJB
  private CuentaManagerLocal cuentaManager;
  
  public void estadoRealCuenta(Cuenta unaCuenta) {
    for (Transaccion transaccion : unaCuenta.getTransacciones()) {
      System.out.println("Una Cuenta " + transaccion);
    }
    
  }

  public void estadoProyectadoCuenta(Cuenta unaCuenta) {
    throw new UnsupportedOperationException("Not yet implemented");
  }
    
 
}
