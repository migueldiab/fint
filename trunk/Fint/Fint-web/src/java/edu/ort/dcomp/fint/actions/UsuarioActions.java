package edu.ort.dcomp.fint.actions;

import edu.ort.common.log.Logger;
import edu.ort.dcomp.fint.controller.UsuarioController;
import edu.ort.dcomp.fint.jsf.JsfUtil;
import edu.ort.dcomp.fint.modelo.Cuenta;
import edu.ort.dcomp.fint.modelo.Servicio;
import edu.ort.dcomp.fint.modelo.Usuario;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author migueldiab
 */
@ManagedBean
@RequestScoped
public class UsuarioActions {

  private Usuario nuevoUsuario = new Usuario();
  
  public UsuarioActions() {
  }

  @EJB
  private UsuarioController usuarioController;

  @EJB
  private Logger logger;

  public Usuario getUsuario() {
    logger.info("UsuarioActions - getUsuario");
    return usuarioController.getUsuario();
  }

  public Set<Cuenta> getCuentas() {
    Set<Cuenta> lista = getUsuario().getCuentas();
    return lista;
  }

  public Set<Servicio> getServicios() {
    Set<Servicio> lista = getUsuario().getServicios();
    return lista;
  }

  public Boolean isUserInRole(final String role) {
    logger.info("UsuarioActions - isUserInRole" + role);
    final ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
    final HttpServletRequest request = (HttpServletRequest) external.getRequest();
    return request.isUserInRole(role);
  }

  public void redirectHome() throws IOException {
    JsfUtil.redirectPartial("inicio.xhtml");
  }
  public void redirectLogin() throws IOException {
    JsfUtil.redirectPartial("index.xhtml");
  }
  
  public Boolean getLoggedIn() {
    logger.info("UsuarioActions - getLoggedIn" + getUsuario());
    return null != getUsuario();
  }
  
  public void logout() throws IOException {
    logger.info("UsuarioActions - logout" + getUsuario());
    final FacesContext context = FacesContext.getCurrentInstance();
 		final ExternalContext ec = context.getExternalContext();
 		final HttpServletRequest request = (HttpServletRequest)ec.getRequest();
 	  request.getSession(false).invalidate();
    usuarioController.logout();
    JsfUtil.redirect("index.xhtml");
  }

  public String crear() {
    usuarioController.crearNuevoUsuario(nuevoUsuario);
    return "/index";
  }
  private UsuarioController getController() {
    return usuarioController;
  }

  public String update() {
    final Usuario usuarioActual = usuarioController.getUsuario();
    System.out.println("Actual " + usuarioActual);
    System.out.println("Logueado " + getUsuario());
    final String pass1 = JsfUtil.getRequestParameter("form_usuario:contrasena");
    final String pass2 = JsfUtil.getRequestParameter("form_usuario:contrasena2");
    String result = null;
    if (pass1.equals(pass2)) {
      if (pass1.isEmpty() && pass2.isEmpty()) {
        getUsuario().setContrasenaHash(usuarioActual.getContrasena());
      } else {
        try {
          getUsuario().setContrasena(pass1);
        } catch (Exception ex) {
          JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
      }
      try {
        getController().edit(getUsuario());
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
        result = "perfil";
      } catch (Exception e) {
        JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
      }
    } else {
      JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PasswordsNoMatch"));
    }
    return result;
  }

  public Usuario getNuevoUsuario() {
    return nuevoUsuario;
  }

  public void setNuevoUsuario(Usuario nuevoUsuario) {
    this.nuevoUsuario = nuevoUsuario;
  }

}
