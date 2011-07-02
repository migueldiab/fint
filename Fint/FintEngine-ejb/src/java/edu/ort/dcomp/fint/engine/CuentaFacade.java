package edu.ort.dcomp.fint.engine;

import edu.ort.dcomp.fint.modelo.Cuenta;
import edu.ort.dcomp.fint.modelo.Transaccion;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author migueldiab
 */
@Stateless
@LocalBean
public class CuentaFacade {

  public void estadoRealCuenta(Cuenta unaCuenta) {
    for (Transaccion transaccion : unaCuenta.getTransacciones()) {
      System.out.println("Una Cuenta " + transaccion);
    }
    
  }

  public void estadoProyectadoCuenta(Cuenta unaCuenta) {
    throw new UnsupportedOperationException("Not yet implemented");
  }
    
 
}
