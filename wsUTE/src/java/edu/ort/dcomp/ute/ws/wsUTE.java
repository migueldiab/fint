package edu.ort.dcomp.ute.ws;

import edu.ort.dcomp.ute.modelo.Recibo;
import edu.ort.dcomp.ute.managers.ClienteFacade;
import edu.ort.dcomp.ute.managers.CuentaFacade;
import edu.ort.dcomp.ute.managers.FacturaFacade;
import edu.ort.dcomp.ute.managers.ReciboFacade;
import edu.ort.dcomp.ute.modelo.Cliente;
import edu.ort.dcomp.ute.modelo.Cuenta;
import edu.ort.dcomp.ute.modelo.Factura;
import edu.ort.dcomp.ute.modelo.Factura.Estado;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ejb.Stateless;

/**
 *
 * @author migueldiab
 */
@WebService()
@Stateless()
public class wsUTE {

  @EJB
  private FacturaFacade facturaFacade;
  @EJB
  private ClienteFacade clienteFacade;
  @EJB
  private CuentaFacade cuentaFacade;
  @EJB
  private ReciboFacade reciboFacade;

  /**
   * Web service operation
   */
  @WebMethod(operationName = "obtenerFacturasPendientes")
  public List<Factura> obtenerFacturasPendientes(
          @WebParam(name = "idCuenta") final long idCuenta,
          @WebParam(name = "usuario") final String usuario,
          @WebParam(name = "password") final String password) {
    Cuenta unaCuenta = cuentaFacade.find(idCuenta);
    return facturaFacade.buscarPorCuentaEstado(unaCuenta, Factura.Estado.PENDIENTE);
  }

  /**
   * Web service operation
   */
  @WebMethod(operationName = "obtenerFacturasPasadas")
  public List<Factura> obtenerFacturasPasadas(
          @WebParam(name = "idCuenta") final long idCuenta,
          @WebParam(name = "dias") final int dias,
          @WebParam(name = "usuario") final String usuario,
          @WebParam(name = "password") final String password) {
    Cuenta unaCuenta = cuentaFacade.find(idCuenta);
    return facturaFacade.buscarPorCuentaDiasPasados(unaCuenta, dias);
  }

  /**
   * Web service operation
   */
  @WebMethod(operationName = "obtenerFacturasPorCuentaEstado")
  public List<Factura> obtenerFacturasPorCuentaEstado(@WebParam(name = "usuario")
  final String usuario, @WebParam(name = "password")
  final String password, @WebParam(name = "idCuenta")
  final Long idCuenta, @WebParam(name = "estadoFactura")
  final Estado estadoFactura) {
    Cuenta unaCuenta = cuentaFacade.find(idCuenta);
    return facturaFacade.buscarPorCuentaEstado(unaCuenta, estadoFactura);
  }

  /**
   * Web service operation
   */
  @WebMethod(operationName = "obtenerCuentasPorCliente")
  public List<Cuenta> obtenerCuentasPorCliente(@WebParam(name = "ciCliente")
  final Long ciCliente, @WebParam(name = "password")
  final String password) {
    Cliente unCliente = clienteFacade.buscarPorCI(ciCliente);
    return cuentaFacade.buscarPorCliente(unCliente);
  }

  /**
   * Web service operation
   */
  @WebMethod(operationName = "pagarCuenta")
  public Recibo pagarCuenta(@WebParam(name = "usuario")
  String usuario, @WebParam(name = "password")
  String password, @WebParam(name = "idCuenta")
  Long idCuenta, @WebParam(name = "idFactura")
  Long idFactura, @WebParam(name = "claveEntidadFinanciera")
  String claveEntidadFinanciera) {
    Cuenta unaCuenta = cuentaFacade.find(idCuenta);
    Factura unaFactura = facturaFacade.find(idFactura);
    return reciboFacade.pagarFactura(unaCuenta, unaFactura, claveEntidadFinanciera);
  }


}
