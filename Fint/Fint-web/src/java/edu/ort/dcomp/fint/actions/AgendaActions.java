package edu.ort.dcomp.fint.actions;

import edu.ort.dcomp.fint.converter.CuentaConverter;
import edu.ort.dcomp.fint.converter.ServicioConverter;
import edu.ort.dcomp.fint.engine.AgendaFacade;
import edu.ort.dcomp.fint.engine.Engine;
import edu.ort.dcomp.fint.engine.UsuarioFacade;
import edu.ort.dcomp.fint.jsf.JsfUtil;
import edu.ort.dcomp.fint.modelo.Agenda;
import edu.ort.dcomp.fint.modelo.Cuenta;
import edu.ort.dcomp.fint.modelo.Servicio;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author migueldiab
 */
@ManagedBean
@RequestScoped
public class AgendaActions {
  private static final String PATH = "/pagos/";

  @EJB
  private UsuarioFacade usuarioController;

  @EJB
  Engine engine;
  
  @EJB
  AgendaFacade agendaController;

  public String crearPago() {
    String result;
    try {
      Cuenta unaCuenta = (Cuenta) JsfUtil.getObjectFromRequestParameter("pago:cuenta", new CuentaConverter(), null);
      Servicio unServicio = (Servicio) JsfUtil.getObjectFromRequestParameter("pago:servicio", new ServicioConverter(), null);
      usuarioController.crearAgenda(unaCuenta, unServicio);
      JsfUtil.addSuccessMessage("Pago creado!");
      result = PATH + "lista";
    } catch (Exception e) {
      String msg = "No se pudo crear el pago";
      engine.errorLog(msg, e.toString());
      JsfUtil.addErrorMessage(msg);
      result = PATH + "crear";
    }
    return result;
  }

  public String borrarPago(Agenda unaAgenda) {
    System.out.println("Borrando");
    agendaController.borrarAgenda(unaAgenda);
    return PATH + "borrada";
  }

}
