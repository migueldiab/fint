package edu.ort.dcomp.fint.engine;

import edu.ort.dcomp.fint.modelo.Usuario;
import edu.ort.dcomp.fint.modelo.facades.UsuarioFacadeLocal;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author migueldiab
 */
@ManagedBean
@ApplicationScoped
public class Engine {
  @EJB
  private UsuarioFacadeLocal usuarioFacade;

  public Usuario getListaEntidades() {
    Usuario unUsuario = new Usuario();
    unUsuario.setId(1L);
    unUsuario.setNombre("Miguel");
    usuarioFacade.create(unUsuario);
    return unUsuario;
  }
}
