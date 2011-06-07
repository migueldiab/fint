package edu.ort.dcomp.fint.engine;

import edu.ort.dcomp.fint.controller.UsuarioController;
import edu.ort.dcomp.fint.modelo.Grupo;
import edu.ort.dcomp.fint.modelo.EntidadFinanciera;
import edu.ort.dcomp.fint.modelo.Usuario;
import edu.ort.dcomp.fint.modelo.facades.GrupoManagerLocal;
import edu.ort.dcomp.fint.modelo.facades.EntidadFinancieraManagerLocal;
import edu.ort.dcomp.fint.modelo.facades.ProveedorManagerLocal;
import edu.ort.dcomp.fint.modelo.facades.UsuarioManagerLocal;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author migueldiab
 */
@Named
@Stateless
public class Facade {

  @EJB
  UsuarioManagerLocal usuarioFacadeLocal;

  @EJB
  GrupoManagerLocal grupoFacadeLocal;

  @EJB
  EntidadFinancieraManagerLocal tipoCuentaManagerLocal;

  @EJB
  ProveedorManagerLocal tipoServicioManagerLocal;

  @EJB
  UsuarioController usuarioController;

  public String getWelcomeMessage() {
    return "Bienvenido a la página de inicio de FINT";
  }

  public void setup() throws UnsupportedEncodingException, NoSuchAlgorithmException {
    Grupo unGrupo = new Grupo();
    unGrupo.setLogin("admin");
    unGrupo.setPermisos("admin");
    grupoFacadeLocal.persist(unGrupo);
    Usuario unUsuario = new Usuario();
    unUsuario.setLogin("admin");
    unUsuario.setContrasena("admin");
    usuarioFacadeLocal.persist(unUsuario);
    

  }

  public Usuario obtenerUsuarioLogueado() {
    return usuarioController.obtenerUsuarioLogueado();
  }
}
