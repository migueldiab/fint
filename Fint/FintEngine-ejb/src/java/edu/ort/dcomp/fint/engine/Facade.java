package edu.ort.dcomp.fint.engine;

import edu.ort.dcomp.fint.modelo.Grupo;
import edu.ort.dcomp.fint.modelo.EntidadFinanciera;
import edu.ort.dcomp.fint.modelo.Proveedor;
import edu.ort.dcomp.fint.modelo.Usuario;
import edu.ort.dcomp.fint.modelo.managers.GrupoManagerLocal;
import edu.ort.dcomp.fint.modelo.managers.EntidadFinancieraManagerLocal;
import edu.ort.dcomp.fint.modelo.managers.ProveedorManagerLocal;
import edu.ort.dcomp.fint.modelo.managers.UsuarioManagerLocal;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author migueldiab
 */
@Stateless
@LocalBean
public class Facade {

  public static Facade getFacade() {
    Facade managedFacade = null;
    return managedFacade;
  }

  @EJB
  private UsuarioManagerLocal ejbUsuario;

  @EJB
  private GrupoManagerLocal ejbGrupo;

  @EJB
  private EntidadFinancieraManagerLocal ejbEntidadFinanciera;

  @EJB
  private ProveedorManagerLocal ejbProveedor;

  public String getWelcomeMessage() {
    return "Bienvenido a la página de inicio de FINT";
  }

  public void setup() throws UnsupportedEncodingException, NoSuchAlgorithmException {
    Grupo unGrupo = new Grupo();
    unGrupo.setLogin("admin");
    unGrupo.setPermisos("admin");
    ejbGrupo.persist(unGrupo);
    Usuario unUsuario = new Usuario();
    unUsuario.setLogin("admin");
    unUsuario.setContrasena("admin");
    unUsuario.setCi(99999999L);
    unUsuario.setEmail("admin@fint.com");
    ejbUsuario.persist(unUsuario);

    EntidadFinanciera brou = new EntidadFinanciera();
    brou.setNombre("BROU");
    EntidadFinanciera nbc = new EntidadFinanciera();
    nbc.setNombre("Nuevo Banco Comercial");
    ejbEntidadFinanciera.persist(brou);
    ejbEntidadFinanciera.persist(nbc);

    Proveedor ute = new Proveedor();
    ute.setNombre("UTE");
    Proveedor ose = new Proveedor();
    ose.setNombre("OSE");
    Proveedor antel = new Proveedor();
    antel.setNombre("Antel");
    ejbProveedor.persist(ose);
    ejbProveedor.persist(antel);
    ejbProveedor.persist(ute);
  }

  public List<EntidadFinanciera> getEntidadesFinancieras() {
    return ejbEntidadFinanciera.findAll();
  }

  public EntidadFinanciera getEntidadFinancieraById(Integer idEF) {
    return ejbEntidadFinanciera.find(idEF);
  }

}
