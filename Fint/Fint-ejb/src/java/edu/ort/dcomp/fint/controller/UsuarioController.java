package edu.ort.dcomp.fint.controller;

import edu.ort.dcomp.fint.modelo.Cuenta;
import edu.ort.dcomp.fint.modelo.Servicio;
import edu.ort.dcomp.fint.modelo.Usuario;
import edu.ort.dcomp.fint.modelo.managers.UsuarioManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author migueldiab
 */
@Stateful
public class UsuarioController {

  private Usuario unUsuario;
  
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

  public Usuario getUsuario() {
    if (null == unUsuario) {
      System.out.println("Loading");
      final ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
      final HttpServletRequest request = (HttpServletRequest) external.getRequest();
      final String user = request.getRemoteUser();
      unUsuario = findByLogin(user);
    }
    return unUsuario;
  }

  public void logout() {
    unUsuario = null;
  }

  public void guardarCuenta(Cuenta cuenta) {
    getUsuario().agregarCuenta(cuenta);
    unUsuario = usuarioManager.merge(getUsuario());
  }

  public void borrarCuenta(Cuenta unaCuenta) {
    getUsuario().getCuentas().remove(unaCuenta);
    unUsuario = usuarioManager.merge(getUsuario());
  }

  public void guardarServicio(Servicio servicio) {
    getUsuario().agregarServicio(servicio);
    unUsuario = usuarioManager.merge(getUsuario());
  }

  public void borrarServicio(Servicio unServicio) {
    getUsuario().getServicios().remove(unServicio);
    unUsuario = usuarioManager.merge(getUsuario());
  }

}
