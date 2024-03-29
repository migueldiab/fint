package edu.ort.dcomp.fint.actions;

import edu.ort.dcomp.fint.engine.CuentaFacade;
import edu.ort.dcomp.fint.engine.Engine;
import edu.ort.dcomp.fint.engine.TransaccionFacade;
import edu.ort.dcomp.fint.engine.UsuarioFacade;
import edu.ort.dcomp.fint.jsf.JsfUtil;
import edu.ort.dcomp.fint.modelo.Transaccion;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author migueldiab
 */
@ManagedBean
@RequestScoped
public class TransaccionActions {

  private Transaccion transaccion = new Transaccion();

  @EJB
  private UsuarioFacade usuarioController;

  @EJB
  private TransaccionFacade transaccionController;

  @EJB
  Engine engine;

  private static final String PATH = "/cuentas/";

  public Transaccion getTransaccion() {
    return transaccion;
  }


  public String guardar() {
    engine.infoLog("Transacciones guardar");
    String response;
    try {
      usuarioController.registrarTransaccion(transaccion);
      response = PATH + "lista";
    } catch (Exception e) {
      engine.errorLog("Transaccion - No se pudo registrar", e.getLocalizedMessage());
      JsfUtil.addErrorMessage("No se pudo crear la Transaccion");
      response = PATH + "registrar";
    }
    return response;

  }
  public Transaccion.Tipo[] getTipos() {
    return Transaccion.Tipo.values();
  }

  public Transaccion.Estado[] getEstados() {
    return Transaccion.Estado.values();
  }

  public Boolean esRetiro(Transaccion trans) {
    Boolean result = null;
    switch (trans.getTipo()) {
      case CUENTA:
        result = Boolean.TRUE;
        break;
      case DEPOSITO:
        result = Boolean.FALSE;
        break;
      case RETIRO:
        result = Boolean.TRUE;
        break;
      case TRANSFERENCIA:
        result = Boolean.FALSE;
        break;
    }
    return result;
  }

}
