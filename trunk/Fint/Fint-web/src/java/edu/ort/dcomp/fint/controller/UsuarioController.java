package edu.ort.dcomp.fint.controller;

import edu.ort.dcomp.fint.modelo.Usuario;
import edu.ort.dcomp.fint.modelo.facades.UsuarioManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

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
    usuarioManager.edit(usuarioLogueado);
  }

}
