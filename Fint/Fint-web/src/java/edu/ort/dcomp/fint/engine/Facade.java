package edu.ort.dcomp.fint.engine;

import edu.ort.dcomp.fint.controller.UsuarioController;
import edu.ort.dcomp.fint.modelo.Grupo;
import edu.ort.dcomp.fint.modelo.EntidadFinanciera;
import edu.ort.dcomp.fint.modelo.Proveedor;
import edu.ort.dcomp.fint.modelo.Usuario;
import edu.ort.dcomp.fint.modelo.facades.GrupoManagerLocal;
import edu.ort.dcomp.fint.modelo.facades.EntidadFinancieraManagerLocal;
import edu.ort.dcomp.fint.modelo.facades.ProveedorManagerLocal;
import edu.ort.dcomp.fint.modelo.facades.UsuarioManagerLocal;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
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

  public static Facade getFacade() {
    Facade managedFacade = null;
    return managedFacade;
  }

  @EJB
  UsuarioManagerLocal usuarioFacadeLocal;

  @EJB
  GrupoManagerLocal grupoFacadeLocal;

  @EJB
  EntidadFinancieraManagerLocal entidadFinancieraManagerLocal;

  @EJB
  ProveedorManagerLocal proveedorManagerLocal;

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

    EntidadFinanciera brou = new EntidadFinanciera();
    brou.setNombre("BROU");
    EntidadFinanciera nbc = new EntidadFinanciera();
    nbc.setNombre("Nuevo Banco Comercial");
    entidadFinancieraManagerLocal.create(brou);
    entidadFinancieraManagerLocal.create(nbc);

    Proveedor ute = new Proveedor();
    ute.setNombre("UTE");
    Proveedor ose = new Proveedor();
    ose.setNombre("OSE");
    Proveedor antel = new Proveedor();
    antel.setNombre("Antel");
    proveedorManagerLocal.create(ose);
    proveedorManagerLocal.create(antel);
    proveedorManagerLocal.create(ute);


  }

  public Usuario obtenerUsuarioLogueado() {
    return usuarioController.obtenerUsuarioLogueado();
  }

  public List<EntidadFinanciera> getEntidadesFinancieras() {
    return entidadFinancieraManagerLocal.findAll();
  }

  public EntidadFinanciera getEntidadFinancieraById(Integer idEF) {
    return entidadFinancieraManagerLocal.find(idEF);
  }
}
