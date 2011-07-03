package edu.ort.dcomp.fint.monitor;

import edu.ort.common.log.Logger;
import edu.ort.dcomp.fint.modelo.Cuenta;
import edu.ort.dcomp.fint.modelo.EntidadFinanciera;
import edu.ort.dcomp.fint.modelo.Usuario;
import edu.ort.dcomp.fint.modelo.managers.EntidadFinancieraManagerLocal;
import edu.ort.dcomp.fint.modelo.managers.TransaccionManagerLocal;
import edu.ort.dcomp.fint.modelo.managers.UsuarioManagerLocal;
import edu.ort.dcomp.fint.ws.entidades.Exception_Exception;
import edu.ort.dcomp.fint.ws.entidades.Movimiento;
import edu.ort.dcomp.fint.ws.entidades.WsBROU;
import edu.ort.dcomp.fint.ws.entidades.WsBROUService;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.namespace.QName;

/**
 *
 * @author migueldiab
 */
@Stateless
public class BROUParser implements GenericEntidadParser {
  private WsBROUService service;

  private static String WS_URL;

  private final String ENTIDAD_ASOCIADA = "BROU";

  private EntidadFinanciera entidadAsociada;

  @EJB
  private UsuarioManagerLocal ejbUsuario;
  @EJB
  private EntidadFinancieraManagerLocal ejbEntidad;
  @EJB
  private TransaccionManagerLocal ejbTransaccion;
  @EJB
  private Logger logger;

  @Override
  public EntidadFinanciera getEntidadAsociada() {
    if (null == entidadAsociada) {
      entidadAsociada = ejbEntidad.buscarPorNombre(ENTIDAD_ASOCIADA);
    }
    return entidadAsociada;
  }

  @Override
  public Cuenta actualizarCuenta(Cuenta cuentaLocal, Usuario usuario) throws MalformedURLException, Exception_Exception {
    String ciUsuario = usuario.getCi().toString();
    String password = usuario.getCi().toString();
    edu.ort.dcomp.fint.ws.entidades.Cuenta cuentaRemota =
            getProxy().obtenerCuenta(ciUsuario, password, cuentaLocal.getNumeroCuenta());
    cuentaLocal.setFechaActualizacion(new Date());
    cuentaLocal.setSaldo(cuentaRemota.getBalance());
    return cuentaLocal;
  }

  @Override
  public Movimiento retirarDinero(Cuenta cuenta, Usuario usuario, BigDecimal importe) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
    

  private WsBROU getProxy() throws MalformedURLException {
    return getService().getWsBROUPort();
  }

  private URL getWSURL() throws MalformedURLException {
    if (null == WS_URL) {
      WS_URL = "http://localhost:8080/wsBROUService/wsBROU?wsdl";
    }
    return new URL(WS_URL);
  }

  private WsBROUService getService() throws MalformedURLException {
    if (null == service) {
      final QName serviceName = new QName("http://ws.brou.dcomp.ort.edu/", "wsBROUService");
      service = new WsBROUService(getWSURL(), serviceName);
    }
    return service;
  }
}
