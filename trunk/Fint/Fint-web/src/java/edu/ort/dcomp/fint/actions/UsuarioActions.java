package edu.ort.dcomp.fint.actions;

import edu.ort.dcomp.fint.controller.UsuarioController;
import edu.ort.dcomp.fint.jsf.JsfUtil;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author migueldiab
 */
@Named
public class UsuarioActions {

  @EJB
  private UsuarioController usuarioController;

  public String guardarUsuario() {
    System.out.println("Guarde el usuario");
    return "/usuario/perfil";
  }
  public String getUsuario() {
    final ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
    final HttpServletRequest request = (HttpServletRequest) external.getRequest();
    final String user = request.getRemoteUser();
    JsfUtil.addCookie(user, user);
    return user;
  }
  public Boolean isUserInRole(final String role) {
    final ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
    final HttpServletRequest request = (HttpServletRequest) external.getRequest();
    return request.isUserInRole(role);
  }

  public void redirectHome() throws IOException {
    JsfUtil.redirect("inicio.xhtml");
  }
  public void redirectLogin() throws IOException {
    JsfUtil.redirect("index.xhtml");
  }
  public Boolean getLoggedIn() {
    final ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
    final HttpServletRequest request = (HttpServletRequest) external.getRequest();
    System.out.println("request.getRemoteUser()" + request.getRemoteUser());
    return request.getRemoteUser() != null;
  }
  
  public void logout() throws IOException {
    final FacesContext context = FacesContext.getCurrentInstance();
 		final ExternalContext ec = context.getExternalContext();
 		final HttpServletRequest request = (HttpServletRequest)ec.getRequest();
 	  request.getSession(false).invalidate();
    JsfUtil.redirect("index.xhtml");
  }
  
}
