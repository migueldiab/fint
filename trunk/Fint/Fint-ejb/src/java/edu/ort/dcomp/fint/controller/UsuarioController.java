package edu.ort.dcomp.fint.controller;

import edu.ort.dcomp.fint.modelo.Cuenta;
import edu.ort.dcomp.fint.modelo.Usuario;
import edu.ort.dcomp.fint.modelo.managers.UsuarioManagerLocal;
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
public class UsuarioController {

  Usuario unUsuario;
  
  @EJB 
  private UsuarioManagerLocal usuarioManager;
  
  public UsuarioController() {
    
  }

  public Usuario findByLogin(String user) {
    return usuarioManager.findByLogin(user);
  }

  public Usuario find(Long key) {
    return usuarioManager.find(key);
  }

  public void edit(Usuario usuarioLogueado) {
    usuarioManager.merge(usuarioLogueado);
  }

  public Usuario obtenerUsuarioLogueado() {
    if (null == unUsuario) {
      final ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
      final HttpServletRequest request = (HttpServletRequest) external.getRequest();
      final String user = request.getRemoteUser();
      unUsuario = findByLogin(user);
    }
    return unUsuario;
  }

  public void guardarCuenta(Cuenta cuenta) {
    unUsuario.agregarCuenta(cuenta);
    unUsuario = usuarioManager.merge(unUsuario);
  }

  public void logout() {
    unUsuario = null;
  }

}
