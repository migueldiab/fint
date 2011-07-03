package edu.ort.dcomp.fint.engine;

import edu.ort.dcomp.fint.modelo.Cuenta;
import edu.ort.dcomp.fint.modelo.EntidadFinanciera;
import edu.ort.dcomp.fint.modelo.Transaccion;
import edu.ort.dcomp.fint.modelo.Usuario;
import edu.ort.dcomp.fint.modelo.managers.CuentaManagerLocal;
import edu.ort.dcomp.fint.modelo.managers.EntidadFinancieraManagerLocal;
import edu.ort.dcomp.fint.monitor.GenericEntidadParser;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.InitialContext;

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

  @EJB
  CuentaManagerLocal ejbCuenta;

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

  public Cuenta merge(Cuenta cuenta) {
    return ejbCuenta.merge(cuenta);
  }

  public Cuenta actualizarSaldo(Cuenta unaCuenta, Usuario usuarioActual) throws Exception {
    Cuenta result = null;
    String jndi;
    GenericEntidadParser gep;
    switch (unaCuenta.getEntidadFinanciera().getParser()) {
      case wsBROU:
        jndi = "java:global/Fint/monitor-ejb/BROUParser";
        break;
      case wsITAU:
        jndi = "java:global/Fint/monitor-ejb/ITAUParser";
        break;
      case wsNBC:
        jndi = "java:global/Fint/monitor-ejb/NBCParser";
        break;
      default:
        throw new AssertionError();
    }
    InitialContext ic = new InitialContext();
    gep = (GenericEntidadParser) ic.lookup(jndi);
    result = gep.actualizarCuenta(unaCuenta, usuarioActual);
    return result;
  }

}
