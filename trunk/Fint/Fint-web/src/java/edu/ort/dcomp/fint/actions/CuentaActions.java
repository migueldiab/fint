package edu.ort.dcomp.fint.actions;

import edu.ort.dcomp.fint.controller.UsuarioController;
import edu.ort.dcomp.fint.jsf.JsfUtil;
import edu.ort.dcomp.fint.modelo.Cuenta;
import edu.ort.dcomp.fint.modelo.facades.EntidadFinancieraManagerLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.SessionScoped;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author migueldiab
 */
@ManagedBean
@SessionScoped
public class CuentaActions {

  private Cuenta cuenta;
  private static String PATH = "/cuentas/";

  public CuentaActions() {
  }

  @EJB
  private UsuarioController usuarioController;

  @EJB
  private EntidadFinancieraManagerLocal entidadFinancieraManagerLocal;

  public Cuenta getCuenta() {
    if (null == cuenta) {
      cuenta = new Cuenta();
    }
    return cuenta;
  }

  public String guardar() {
    System.out.println("Test");
    String response;
    try {
      usuarioController.guardarCuenta(cuenta);
      response = PATH + "lista";
    } catch (Exception e) {
      Logger.getLogger("CuentaActions").log(Level.WARNING, "No se pudo crear la cuenta");
      JsfUtil.addErrorMessage("No se pudo crear la cuenta");
      response = PATH + "crear";
    }
    return response;
    
  }

  
}
