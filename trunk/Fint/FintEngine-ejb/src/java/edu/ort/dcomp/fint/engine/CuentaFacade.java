package edu.ort.dcomp.fint.engine;

import edu.ort.dcomp.fint.modelo.Cuenta;
import edu.ort.dcomp.fint.modelo.EntidadFinanciera;
import edu.ort.dcomp.fint.modelo.Transaccion;
import edu.ort.dcomp.fint.modelo.managers.EntidadFinancieraManagerLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author migueldiab
 */
@Stateless
@LocalBean
public class CuentaFacade {
  @EJB
  Engine engine;

  @EJB
  EntidadFinancieraManagerLocal ejbEntidadFinanciera;

  public void estadoRealCuenta(Cuenta unaCuenta) {
    for (Transaccion transaccion : unaCuenta.getTransacciones()) {
      System.out.println("Una Cuenta " + transaccion);
    }    
  }

  public void estadoProyectadoCuenta(Cuenta unaCuenta) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public List<EntidadFinanciera> getEntidadesFinancieras() {
    engine.infoLog("getEntidadesFinancieras()");
    return ejbEntidadFinanciera.findAll();
  }
}
