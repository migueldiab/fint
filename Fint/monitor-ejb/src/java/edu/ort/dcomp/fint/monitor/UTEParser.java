package edu.ort.dcomp.fint.monitor;

import edu.ort.common.exceptions.EmptyPropertyException;
import edu.ort.common.exceptions.WebServiceCommunicationException;
import edu.ort.common.log.Logger;
import edu.ort.common.utils.DateTime;
import edu.ort.dcomp.fint.modelo.Proveedor;
import edu.ort.dcomp.fint.modelo.Servicio;
import edu.ort.dcomp.fint.modelo.Transaccion;
import edu.ort.dcomp.fint.modelo.Transaccion.Estado;
import edu.ort.dcomp.fint.modelo.Usuario;
import edu.ort.dcomp.fint.modelo.managers.ProveedorManagerLocal;
import edu.ort.dcomp.fint.modelo.managers.TransaccionManagerLocal;
import edu.ort.dcomp.fint.modelo.managers.UsuarioManagerLocal;
import edu.ort.dcomp.fint.ws.servicio.Cuenta;
import edu.ort.dcomp.fint.ws.servicio.Factura;
import edu.ort.dcomp.fint.ws.servicio.Recibo;
import edu.ort.dcomp.fint.ws.servicio.WsUTE;
import edu.ort.dcomp.fint.ws.servicio.WsUTEService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.namespace.QName;

/**
 *
 * @author migueldiab
 */
@Stateless
public class UTEParser implements GenericProveedorParser {
  private WsUTEService service;

  private static String WS_URL;

  private final String PROVEEDOR_ASOCIADO = "UTE";
  private Proveedor proveedorAsociado;
  
  @EJB
  private UsuarioManagerLocal ejbUsuario;
  @EJB
  private ProveedorManagerLocal ejbProveedor;
  @EJB
  private TransaccionManagerLocal ejbTransaccion;
  @EJB
  private Logger logger;

  @Asynchronous
  @Override
  public void leerFacturasPendientes(Servicio servicio) throws WebServiceCommunicationException {
    WsUTE proxy;
    try {
      proxy = getProxy();
    } catch (MalformedURLException ex) {
      throw new WebServiceCommunicationException("Error en la configuración. Contacte al administrador", ex);
    } catch (IOException ex) {
      throw new WebServiceCommunicationException("Error en la conección. Intente mas tarde", ex);
    } catch (EmptyPropertyException ex) {
      throw new WebServiceCommunicationException("Error en la configuración. Contacte al administrador", ex);
    }
    List<Factura> result = proxy.obtenerFacturasPendientes(servicio.getNumero(), servicio.getUsuarioWs(), servicio.getPassWs());
    for (Factura factura : result) {
      if (!ejbTransaccion.existe(servicio, factura.getId().toString())) {
        importarTransaccion(factura, servicio);
      }
    }
  }
  
  @Override
  public Proveedor getProveedorAsociado() {
    if (null == proveedorAsociado) {
      proveedorAsociado = ejbProveedor.buscarPorNombre(PROVEEDOR_ASOCIADO);
    }
    return proveedorAsociado;
  }

  private Estado parseEstado(edu.ort.dcomp.fint.ws.servicio.Estado estado) {
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
  public void leerFacturasPasadas() throws WebServiceCommunicationException {
    final List<Usuario> lista = ejbUsuario.findAll();
    WsUTE proxy;
    try {
      proxy = getProxy();
    } catch (MalformedURLException ex) {
      throw new WebServiceCommunicationException("Error en la configuración. Contacte al administrador", ex);
    } catch (IOException ex) {
      throw new WebServiceCommunicationException("Error en la conección. Intente mas tarde", ex);
    } catch (EmptyPropertyException ex) {
      throw new WebServiceCommunicationException("Error en la configuración. Contacte al administrador", ex);
    }
    for (Usuario usuario : lista) {
      List<Factura> result = proxy.obtenerFacturasPasadas(usuario.getCi(), 15, "", "");
      for (Factura factura : result) {
//        Transaccion trans = ejbTransaccion.buscarPorServicioNumero(proveedorAsociado, factura.getId().toString());
//        if (null == trans) {
//          importarTransaccion(factura, usuario, proveedorAsociado);
//        } else {
//          actualizarTransaccion(trans, factura, usuario, proveedorAsociado);
//        }
      }
    }
  }

  private void importarTransaccion(Factura factura, Servicio servicio) {
    final Transaccion unaT = new Transaccion();
    unaT.setConcepto(factura.getConcepto());
    unaT.setDestinatario(PROVEEDOR_ASOCIADO);
    unaT.setImporte(factura.getImporte());
    unaT.setFechaEmision(DateTime.asDate(factura.getFechaEmision()) );
    unaT.setFechaIngreso(new Date());
    unaT.setFechaActualizacion(new Date());
    unaT.setFechaPago(DateTime.asDate(factura.getFechaPago()));
    unaT.setFechaVencimiento(DateTime.asDate(factura.getFechaVencimiento()));
    unaT.setNumero(factura.getId().toString());
    unaT.setServicio(servicio);
    unaT.setTipo(Transaccion.Tipo.CUENTA);
    unaT.setEstado(parseEstado(factura.getEstado()));
    unaT.setUsuario(servicio.getUsuario());
    ejbTransaccion.persist(unaT);
  }

  private void actualizarTransaccion(Transaccion trans, Factura factura, Usuario usuario, Servicio servicioAsociado) {
    trans.setFechaActualizacion(new Date());
    trans.setFechaPago(DateTime.asDate(factura.getFechaPago()));
    trans.setEstado(parseEstado(factura.getEstado()));
    ejbTransaccion.merge(trans);
  }

  @Override
  public List<Servicio> listarCuentas(String id, String password) throws WebServiceCommunicationException {
    logger.info("List<Servicio> listarCuentas(id "+id+", password"+password+")");
    Long ciCliente = Long.parseLong(id);
    List<Cuenta> cuentas = null;
    List<Servicio> result = null;
    WsUTE proxy = null;
    try {
      proxy = getProxy();
      cuentas = proxy.obtenerCuentasPorCliente(ciCliente, password);
      result = importarCuentas(cuentas, id, password);
    } catch (EmptyPropertyException propEx) {
      throw new WebServiceCommunicationException("Error en la configuración. Contacte al administrador", propEx);
    } catch (IOException ioEx) {
      throw new WebServiceCommunicationException("Error en la comunicación. Intente mas tarde", ioEx);
    }
    return result;
  }

  private List<Servicio> importarCuentas(List<Cuenta> cuentas, String id, String password) {
    logger.info("List<Servicio> importarCuentas(cuentas "+cuentas+")");
    List<Servicio> result = new ArrayList<Servicio>();
    for (Cuenta cuenta : cuentas) {
      Servicio nuevoServicio = new Servicio();
      //nuevoServicio.setCategoria();
      nuevoServicio.setNombre(PROVEEDOR_ASOCIADO + " - " + cuenta.getId().toString());
      nuevoServicio.setNumero(cuenta.getId());
      nuevoServicio.setProveedor(getProveedorAsociado());
      nuevoServicio.setConectado(Boolean.TRUE);
      nuevoServicio.setUsuarioWs(id);
      nuevoServicio.setPassWs(password);
      result.add(nuevoServicio);
    }
    return result;
  }


  @Override
  public Recibo pagarCuenta(Transaccion factura, String clavePago) {
    logger.info("pagarCuenta(Transaccion factura, String clavePago)");
    Recibo result = null;
    String usuarioWs = factura.getServicio().getUsuarioWs();
    String passWs = factura.getServicio().getPassWs();
    Long idCuenta = factura.getServicio().getNumero();
    Long idFactura = Long.parseLong(factura.getNumero());
    WsUTE proxy = null;
    try {
      proxy = getProxy();
      result = proxy.pagarCuenta(usuarioWs, passWs, idCuenta, idFactura, clavePago);
    } catch (EmptyPropertyException propEx) {
      throw new WebServiceCommunicationException("Error en la configuración. Contacte al administrador", propEx);
    } catch (IOException ioEx) {
      throw new WebServiceCommunicationException("Error en la comunicación. Intente mas tarde", ioEx);
    }
    return result;
  }

  private WsUTE getProxy() throws MalformedURLException, IOException, EmptyPropertyException {
    return getService().getWsUTEPort();
  }

  private URL getWSURL() throws MalformedURLException, IOException, EmptyPropertyException {
    if (null == WS_URL) {
      WS_URL = Monitor.getInstance().getProperty("WS_UTE");
    }
    return new URL(WS_URL);
  }

  private WsUTEService getService() throws MalformedURLException, IOException, EmptyPropertyException {
    if (null == service) {
      final QName serviceName = new QName("http://ws.ute.dcomp.ort.edu/", "wsUTEService");
      service = new WsUTEService(getWSURL(), serviceName);
    }
    return service;
  }

}
