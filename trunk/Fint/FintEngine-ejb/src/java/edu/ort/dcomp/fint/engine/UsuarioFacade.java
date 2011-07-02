package edu.ort.dcomp.fint.engine;

import edu.ort.common.log.Logger;
import edu.ort.common.mail.MailerLocal;
import edu.ort.dcomp.fint.modelo.Agenda;
import edu.ort.dcomp.fint.modelo.Cuenta;
import edu.ort.dcomp.fint.modelo.Grupo;
import edu.ort.dcomp.fint.modelo.Servicio;
import edu.ort.dcomp.fint.modelo.Transaccion;
import edu.ort.dcomp.fint.modelo.Usuario;
import edu.ort.dcomp.fint.modelo.managers.AgendaManagerLocal;
import edu.ort.dcomp.fint.modelo.managers.GrupoManagerLocal;
import edu.ort.dcomp.fint.modelo.managers.TransaccionManagerLocal;
import edu.ort.dcomp.fint.modelo.managers.UsuarioManagerLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author migueldiab
 */
@Stateful
@LocalBean
public class UsuarioFacade {

  private Usuario unUsuario;
  
  @EJB 
  private UsuarioManagerLocal ejbUsuario;

  @EJB
  private GrupoManagerLocal ejbGrupo;

  @EJB
  private AgendaManagerLocal ejbAgenda;

  @EJB
  private TransaccionManagerLocal ejbTransaccion;

  @EJB
  private MailerLocal  mailer;

  @EJB
  private Logger logger;
  
  private String GRUPO_USUARIO = "usuario";
  
  public UsuarioFacade() {
    
  }

  public Usuario findByLogin(String user) {
    return ejbUsuario.findByLogin(user);
  }

  public Usuario find(Long key) {
    return ejbUsuario.find(key);
  }

  public void edit(Usuario usuarioLogueado) {
    ejbUsuario.merge(usuarioLogueado);
  }

  public Usuario getUsuario() {
    if (null == unUsuario) {
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
    unUsuario = ejbUsuario.merge(getUsuario());
  }

  public void borrarCuenta(Cuenta unaCuenta) {
    getUsuario().getCuentas().remove(unaCuenta);
    unUsuario = ejbUsuario.merge(getUsuario());
  }

  public void guardarServicio(Servicio servicio) {
    getUsuario().agregarServicio(servicio);
    unUsuario = ejbUsuario.merge(getUsuario());
  }

  public void borrarServicio(Servicio unServicio) {
    getUsuario().getServicios().remove(unServicio);
    unUsuario = ejbUsuario.merge(getUsuario());
  }

  public void crearNuevoUsuario(Usuario nuevoUsuario) {
    ejbUsuario.persist(nuevoUsuario);
    Grupo unGrupo = new Grupo();
    unGrupo.setLogin(nuevoUsuario.getLogin());
    unGrupo.setPermisos(GRUPO_USUARIO);
    ejbGrupo.persist(unGrupo);
    try {
      mailer.sendMail(nuevoUsuario.getEmail(), "Bienvenido a FINT", "Bienvenido " + nuevoUsuario.getNombre());
    } catch (Exception e) {
      logger.error("Error al notificar al usuario", e.toString());
    }
  }

  public List<Agenda> getAgendaPagos(Usuario usuario) {
    List<Agenda> result = null;
    result = ejbAgenda.buscarPorUsuario(usuario);
    return result;
  }

  public void crearAgenda(Cuenta unaCuenta, Servicio unServicio) {
    Usuario usuario = getUsuario();
    Agenda unaAgenda = new Agenda();
    unaAgenda.setCuenta(unaCuenta);
    unaAgenda.setServicio(unServicio);
    unaAgenda.setTipo(Agenda.Tipo.SERVICIO);
    unaAgenda.setUsuario(usuario);
    ejbAgenda.persist(unaAgenda);
  }

  public void registrarTransaccion(Transaccion transaccion) {
    transaccion.setUsuario(getUsuario());
    Date fecha = new Date();
    transaccion.setFechaActualizacion(fecha);
    transaccion.setFechaIngreso(fecha);
    ejbTransaccion.persist(transaccion);    
  }

}
