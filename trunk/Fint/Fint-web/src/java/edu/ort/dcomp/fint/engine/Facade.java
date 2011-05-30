package edu.ort.dcomp.fint.engine;

import edu.ort.dcomp.fint.modelo.Grupo;
import edu.ort.dcomp.fint.modelo.Usuario;
import edu.ort.dcomp.fint.modelo.facades.GrupoFacadeLocal;
import edu.ort.dcomp.fint.modelo.facades.UsuarioFacadeLocal;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.inject.Named;

/**
 *
 * @author migueldiab
 */
@Named
public class Facade {

  @EJB
  UsuarioFacadeLocal usuarioFacadeLocal;

  @EJB
  GrupoFacadeLocal grupoFacadeLocal;

  public String getWelcomeMessage() {
    return "Bienvenido a la página de inicio de FINT";
  }

  public void setup() throws UnsupportedEncodingException, NoSuchAlgorithmException {
    Grupo unGrupo = new Grupo();
    unGrupo.setLogin("admin");
    unGrupo.setPermisos("admin");
    grupoFacadeLocal.create(unGrupo);
    Usuario unUsuario = new Usuario();
    unUsuario.setLogin("admin");
    unUsuario.setContrasena("admin");
    usuarioFacadeLocal.create(unUsuario);
  }
}
