package edu.ort.dcomp.fint.actions;

import edu.ort.dcomp.fint.engine.CuentaFacade;
import edu.ort.dcomp.fint.engine.UsuarioFacade;
import edu.ort.dcomp.fint.engine.Engine;
import edu.ort.dcomp.fint.jsf.JsfUtil;
import edu.ort.dcomp.fint.modelo.Cuenta;
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

  public String guardar() {
    engine.infoLog("CuentaActions - guardar");
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
    System.out.println("Estado Real");
    cuentaController.estadoRealCuenta(unaCuenta);
    return PATH + "estado";
  }

  public String estadoProyectadoCuenta(Cuenta unaCuenta) {
    System.out.println("Estado Proyectado");
    cuentaController.estadoProyectadoCuenta(unaCuenta);
    return PATH + "estado";
  }


  
}
