package edu.ort.dcomp.fint.actions;

import edu.ort.dcomp.fint.controller.UsuarioController;
import edu.ort.dcomp.fint.jsf.JsfUtil;
import edu.ort.dcomp.fint.modelo.Servicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author migueldiab
 */
@ManagedBean
@RequestScoped
public class ServicioActions {

  private Servicio servicio;
  private static String PATH = "/servicios/";

  public ServicioActions() {
  }

  @EJB
  private UsuarioController usuarioController;
  
  public Servicio getServicio() {
    if (null == servicio) {
      servicio = new Servicio();
    }
    return servicio;
  }

  public String guardar() {
    String response;
    try {
      usuarioController.guardarServicio(servicio);
      response = PATH + "lista";
    } catch (Exception e) {
      Logger.getLogger("ServicioActions").log(Level.WARNING, "No se pudo crear el servicio");
      JsfUtil.addErrorMessage("No se pudo crear la servicio");
      response = PATH + "crear";
    }
    return response;
    
  }

  public String borrarServicio(Servicio unServicio) {
    System.out.println("Borrando");
    usuarioController.borrarServicio(unServicio);
    return PATH + "borrada";
  }

  
}
