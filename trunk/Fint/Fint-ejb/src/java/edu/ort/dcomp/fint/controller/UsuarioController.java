package edu.ort.dcomp.fint.controller;

import edu.ort.dcomp.fint.modelo.Cuenta;
import edu.ort.dcomp.fint.modelo.Usuario;
import edu.ort.dcomp.fint.modelo.facades.UsuarioManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author migueldiab
 */
@Stateless
public class UsuarioController {

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
    final ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
    final HttpServletRequest request = (HttpServletRequest) external.getRequest();
    final String user = request.getRemoteUser();
    return findByLogin(user);
  }

  public void guardarCuenta(Cuenta cuenta) {
    Usuario unUsuario = obtenerUsuarioLogueado();
    unUsuario.agregarCuenta(cuenta);
    usuarioManager.merge(unUsuario);
  }

}
