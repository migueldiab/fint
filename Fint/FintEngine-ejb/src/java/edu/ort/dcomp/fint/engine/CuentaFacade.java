package edu.ort.dcomp.fint.engine;

import edu.ort.dcomp.fint.modelo.Cuenta;
import edu.ort.dcomp.fint.modelo.EntidadFinanciera;
import edu.ort.dcomp.fint.modelo.Transaccion;
import edu.ort.dcomp.fint.modelo.managers.EntidadFinancieraManagerLocal;
import java.util.List;
import java.util.Set;
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

  public Set<Transaccion> estadoRealCuenta(Cuenta unaCuenta) {
    return unaCuenta.getTransacciones();
  }

  public void estadoProyectadoCuenta(Cuenta unaCuenta) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public List<EntidadFinanciera> getEntidadesFinancieras() {
    engine.infoLog("getEntidadesFinancieras()");
    return ejbEntidadFinanciera.findAll();
  }
}
