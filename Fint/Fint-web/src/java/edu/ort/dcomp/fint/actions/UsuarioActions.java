package edu.ort.dcomp.fint.actions;

import edu.ort.dcomp.fint.controller.UsuarioController;
import edu.ort.dcomp.fint.jsf.JsfUtil;
import edu.ort.dcomp.fint.modelo.Cuenta;
import edu.ort.dcomp.fint.modelo.Usuario;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Set;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author migueldiab
 */
@ManagedBean
@SessionScoped
public class UsuarioActions {

  private Usuario usuario;

  public UsuarioActions() {
  }

  @EJB
  private UsuarioController usuarioController;

  public Usuario getUsuario() {
    if (null == usuario) {
      usuario = usuarioController.obtenerUsuarioLogueado();
    }
    return usuario;
  }

  public Set<Cuenta> getCuentas() {
    if (null == usuario) {
      usuario = usuarioController.obtenerUsuarioLogueado();
    }
    Set<Cuenta> lista = usuario.getCuentas();
    System.out.println("Cuentas " + lista.size());
    for (Cuenta cuenta : lista) {
      System.out.println("cuenta" + cuenta.getNombre());
    }
    return lista;
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
    return null != getUsuario();
  }
  
  public void logout() throws IOException {
    final FacesContext context = FacesContext.getCurrentInstance();
 		final ExternalContext ec = context.getExternalContext();
 		final HttpServletRequest request = (HttpServletRequest)ec.getRequest();
 	  request.getSession(false).invalidate();
    usuario = null;
    JsfUtil.redirect("index.xhtml");
  }
  
  private UsuarioController getController() {
    return usuarioController;
  }

  public String update() {
    final Usuario usuarioActual = usuarioController.obtenerUsuarioLogueado();
    System.out.println("Actual " + usuarioActual);
    System.out.println("Logueado " + usuario);
    final String pass1 = JsfUtil.getRequestParameter("form_usuario:contrasena");
    final String pass2 = JsfUtil.getRequestParameter("form_usuario:contrasena2");
    String result = null;
    if (pass1.equals(pass2)) {
      if (pass1.isEmpty() && pass2.isEmpty()) {
        usuario.setContrasenaHash(usuarioActual.getContrasena());
      } else {
        try {
          usuario.setContrasena(pass1);
        } catch (Exception ex) {
          JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
      }
      try {
        getController().edit(usuario);
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

}
