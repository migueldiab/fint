package edu.ort.dcomp.fint.monitor;

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
import edu.ort.dcomp.fint.ws.servicio.WsUTE;
import edu.ort.dcomp.fint.ws.servicio.WsUTEService;
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
  public void leerFacturasPendientes() throws MalformedURLException {
    final List<Usuario> lista = ejbUsuario.findAll();
    for (Usuario usuario : lista) {
      List<Factura> result = getProxy().
              obtenerFacturasPendientes(usuario.getCi(), "", "");
      for (Factura factura : result) {
//        if (!ejbTransaccion.existe(proveedorAsociado, factura.getId().toString())) {
//          importarTransaccion(factura, usuario, proveedorAsociado);
//        }
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
  public void leerFacturasPasadas() throws MalformedURLException {
    final List<Usuario> lista = ejbUsuario.findAll();
    for (Usuario usuario : lista) {
      List<Factura> result = getProxy().
              obtenerFacturasPasadas(usuario.getCi(), 15, "", "");
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

  private void importarTransaccion(Factura factura, Usuario usuario, Servicio servicioAsociado) {
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

  @Override
  public List<Servicio> listarCuentas(String id, String password) throws MalformedURLException {
    logger.info("List<Servicio> listarCuentas(id "+id+", password"+password+")");
    Long ciCliente = Long.parseLong(id);
    List<Cuenta> cuentas = null;
    List<Servicio> result = null;
    WsUTE proxy = getProxy();
    cuentas = proxy.obtenerCuentasPorCliente(ciCliente, password);
    result = importarCuentas(cuentas);
    return result;
  }

  private List<Servicio> importarCuentas(List<Cuenta> cuentas) {
    logger.info("List<Servicio> importarCuentas(cuentas "+cuentas+")");
    List<Servicio> result = new ArrayList<Servicio>();
    for (Cuenta cuenta : cuentas) {
      Servicio nuevoServicio = new Servicio();
      //nuevoServicio.setCategoria();
      nuevoServicio.setNombre(PROVEEDOR_ASOCIADO + " - " + cuenta.getId().toString());
      nuevoServicio.setNumero(cuenta.getId());
      nuevoServicio.setProveedor(getProveedorAsociado());
      result.add(nuevoServicio);
    }
    return result;
  }

  private WsUTE getProxy() throws MalformedURLException {
    return getService().getWsUTEPort();
  }

  private URL getWSURL() throws MalformedURLException {
    if (null == WS_URL) {
      WS_URL = "http://localhost:8080/wsUTEService/wsUTE?wsdl";
    }
    return new URL(WS_URL);
  }

  private WsUTEService getService() throws MalformedURLException {
    if (null == service) {
      final QName serviceName = new QName("http://ws.ute.dcomp.ort.edu/", "wsUTEService");
      service = new WsUTEService(getWSURL(), serviceName);
    }
    return service;
  }
}
