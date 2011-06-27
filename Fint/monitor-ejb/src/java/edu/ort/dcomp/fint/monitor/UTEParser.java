package edu.ort.dcomp.fint.monitor;

import edu.ort.common.utils.DateTime;
import edu.ort.dcomp.fint.modelo.Agenda;
import edu.ort.dcomp.fint.modelo.Servicio;
import edu.ort.dcomp.fint.modelo.Transaccion;
import edu.ort.dcomp.fint.modelo.Transaccion.Estado;
import edu.ort.dcomp.fint.modelo.Usuario;
import edu.ort.dcomp.fint.modelo.managers.ServicioManagerLocal;
import edu.ort.dcomp.fint.modelo.managers.TransaccionManagerLocal;
import edu.ort.dcomp.fint.modelo.managers.UsuarioManagerLocal;
import edu.ort.dcomp.fint.monitor.client.Factura;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author migueldiab
 */
@Stateless
@LocalBean
public class UTEParser implements GenericProveedorParser {

  private final String SERVICIO_ASOCIADO = "UTE";
  private Servicio servicioAsociado;
  
  @EJB
  WSFacade wSFacade;

  @EJB
  UsuarioManagerLocal ejbUsuario;
  @EJB
  ServicioManagerLocal ejbServicio;
  @EJB
  TransaccionManagerLocal ejbTransaccion;

  @Asynchronous
  @Override
  public void leerFacturasPendientes() throws MalformedURLException {
    final List<Usuario> lista = ejbUsuario.findAll();
    for (Usuario usuario : lista) {
      List<Factura> result = wSFacade.getProxy().
              obtenerFacturasPendientes(usuario.getCi(), "", "");
      for (Factura factura : result) {
        if (!ejbTransaccion.existe(servicioAsociado, factura.getId().toString())) {
          importarTransaccion(factura, usuario, servicioAsociado);
        }
      }
    } 
  }
  
  @Override
  public Servicio getServicioAsociado() {
    if (null == servicioAsociado) {
      servicioAsociado = ejbServicio.buscarPorNombre(SERVICIO_ASOCIADO);
    }
    return servicioAsociado;
  }

  private Estado parseEstado(edu.ort.dcomp.fint.monitor.client.Estado estado) {
    Estado result = null;
    switch (estado) {
      case ANULADA:
        result = Estado.CANCELADA;
        break;
      case PAGA:
        result = Estado.PAGA;
        break;
      case PENDIENTE:
        result = Estado.PENDIENTE;
        break;
      case VENCIDA:
        result = Estado.VENCIDA;
        break;
      default:
        result = Estado.DESCONOCIDO;
    }
    return result;
  }

  @Asynchronous
  @Override
  public void leerFacturasPasadas() throws MalformedURLException {
    final List<Usuario> lista = ejbUsuario.findAll();
    for (Usuario usuario : lista) {
      List<Factura> result = wSFacade.getProxy().
              obtenerFacturasPasadas(usuario.getCi(), 15, "", "");
      for (Factura factura : result) {
        Transaccion trans = ejbTransaccion.buscarPorServicioNumero(servicioAsociado, factura.getId().toString());
        if (null == trans) {
          importarTransaccion(factura, usuario, servicioAsociado);
        } else {
          actualizarTransaccion(trans, factura, usuario, servicioAsociado);
        }
      }
    }
  }

  private void importarTransaccion(Factura factura, Usuario usuario, Servicio servicioAsociado) {
    final Transaccion unaT = new Transaccion();
    unaT.setConcepto(factura.getConcepto());
    unaT.setDestinatario(SERVICIO_ASOCIADO);
    unaT.setImporte(factura.getImporte());
    unaT.setFechaEmision(DateTime.asDate(factura.getFechaEmision()) );
    unaT.setFechaIngreso(new Date());
    unaT.setFechaActualizacion(new Date());
    unaT.setFechaPago(DateTime.asDate(factura.getFechaPago()));
    unaT.setFechaVencimiento(DateTime.asDate(factura.getFechaVencimiento()));
    unaT.setNumero(factura.getId().toString());
    unaT.setServicio(servicioAsociado);
    unaT.setTipo(Transaccion.Tipo.CUENTA);
    unaT.setEstado(parseEstado(factura.getEstado()));
    unaT.setUsuario(usuario);
    ejbTransaccion.persist(unaT);
  }

  private void actualizarTransaccion(Transaccion trans, Factura factura, Usuario usuario, Servicio servicioAsociado) {
    trans.setFechaActualizacion(new Date());
    trans.setFechaPago(DateTime.asDate(factura.getFechaPago()));
    trans.setEstado(parseEstado(factura.getEstado()));
    ejbTransaccion.merge(trans);
  }
}
