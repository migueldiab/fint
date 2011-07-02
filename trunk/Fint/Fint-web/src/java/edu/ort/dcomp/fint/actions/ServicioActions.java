package edu.ort.dcomp.fint.actions;

import edu.ort.dcomp.fint.engine.ServicioFacade;
import edu.ort.dcomp.fint.engine.UsuarioFacade;
import edu.ort.dcomp.fint.converter.ProveedorConverter;
import edu.ort.dcomp.fint.engine.Engine;
import edu.ort.dcomp.fint.jsf.JsfUtil;
import edu.ort.dcomp.fint.modelo.Proveedor;
import edu.ort.dcomp.fint.modelo.Servicio;
import java.util.List;
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

  @EJB
  private ServicioFacade servicioFacade;
  @EJB
  private Engine engine;

  private Servicio servicio;
  private static String PATH = "/servicios/";

  public ServicioActions() {
  }

  @EJB
  private UsuarioFacade usuarioController;
  
  public Servicio getServicio() {
    if (null == servicio) {
      servicio = new Servicio();
    }
    return servicio;
  }

  public String conectar() {
    String response;
    response = PATH + "lista";
    String id = JsfUtil.getRequestParameter("conectar:id");
    String password = JsfUtil.getRequestParameter("conectar:password");
    Proveedor proveedor = (Proveedor) JsfUtil.getObjectFromRequestParameter("conectar:proveedor", new ProveedorConverter(), null);
    List<Servicio> lista = servicioFacade.listarCuentasProveedor(id, password, proveedor);
    for (Servicio servicio1 : lista) {
      
    }
    return response;
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

  public List<Proveedor> getProveedores() {
    return servicioFacade.getProveedores();
  }

  public Proveedor getProveedorById(Integer idProveedor) {
    return servicioFacade.getProveedorById(idProveedor);
  }

  
}
