package edu.ort.dcomp.fint.actions;

import edu.ort.dcomp.fint.engine.CuentaFacade;
import edu.ort.dcomp.fint.engine.UsuarioFacade;
import edu.ort.dcomp.fint.engine.Engine;
import edu.ort.dcomp.fint.jsf.JsfUtil;
import edu.ort.dcomp.fint.modelo.Cuenta;
import edu.ort.dcomp.fint.modelo.EntidadFinanciera;
import edu.ort.dcomp.fint.modelo.Transaccion;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author migueldiab
 */
@ManagedBean
@RequestScoped
public class CuentaActions {

  private Cuenta cuenta;
  private static String PATH = "/cuentas/";

  public CuentaActions() {
  }

  @EJB
  private UsuarioFacade usuarioController;

  @EJB
  private CuentaFacade cuentaController;

  @EJB
  Engine engine;

  public Cuenta getCuenta() {
    if (null == cuenta) {
      cuenta = new Cuenta();
    }
    return cuenta;
  }

  public Transaccion[] getTransacciones() {
    cuenta = cuentaController.merge(cuenta);
    return cuenta.getTransacciones().toArray(new Transaccion[0]);
  }

  public String guardar() {
    engine.infoLog("CuentaActions - guardar" + cuenta.getTipo());
    String response;
    try {
      usuarioController.guardarCuenta(cuenta);
      response = PATH + "lista";
    } catch (Exception e) {
      engine.errorLog("CuentaActions - No se pudo crear la cuenta", e.getLocalizedMessage());
      JsfUtil.addErrorMessage("No se pudo crear la cuenta");
      response = PATH + "crear";
    }
    return response;
    
  }

  public String borrarCuenta(Cuenta unaCuenta) {
    System.out.println("Borrando");
    usuarioController.borrarCuenta(unaCuenta);
    return PATH + "borrada";
  }

  public String estadoRealCuenta(Cuenta unaCuenta) {
    cuenta = unaCuenta;
    return PATH + "estado";
  }

  public String estadoProyectadoCuenta(Cuenta unaCuenta) {
    System.out.println("Estado Proyectado");
    cuentaController.estadoProyectadoCuenta(unaCuenta);
    return PATH + "estado";
  }


  public List<EntidadFinanciera> getEntidadesFinancieras() {
    return cuentaController.getEntidadesFinancieras();
  }

  public Cuenta.Tipo[] getTiposCuenta() {
    return Cuenta.Tipo.values();
  }
  
}
