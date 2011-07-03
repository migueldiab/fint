package edu.ort.dcomp.brou.ws;

import edu.ort.dcomp.brou.facades.ClienteFacade;
import edu.ort.dcomp.brou.facades.CuentaFacade;
import edu.ort.dcomp.brou.modelo.Cliente;
import edu.ort.dcomp.brou.modelo.Cuenta;
import edu.ort.dcomp.brou.modelo.Movimiento;
import java.math.BigDecimal;
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
public class wsBROU {

  @EJB
  private ClienteFacade clienteFacade;

  @EJB
  private CuentaFacade cuentaFacade;


  /**
   * Web service operation
   */
  @WebMethod(operationName = "obtenerCuenta")
  public Cuenta obtenerCuenta(@WebParam(name = "usuario")
  final String usuario, @WebParam(name = "password")
  final String password, @WebParam(name = "idCuenta")
  final Long idCuenta) throws Exception {
    Cliente unCliente = clienteFacade.validarUsuario(usuario, password);
    Cuenta unaCuenta = cuentaFacade.buscarCuentaPorCliente(unCliente, idCuenta);
    return unaCuenta;
  }

  /**
   * Web service operation
   */
  @WebMethod(operationName = "retirarDinero")
  public Movimiento retirarDinero(@WebParam(name = "usuario")
  final String usuario, @WebParam(name = "password")
  final String password, @WebParam(name = "idCuenta")
  final Long idCuenta, @WebParam(name = "importe")
  final Float importe, @WebParam(name = "descripcion")
  final String descripcion) throws Exception {
    Cliente unCliente = clienteFacade.validarUsuario(usuario, password);
    Cuenta unaCuenta = cuentaFacade.buscarCuentaPorCliente(unCliente, idCuenta);
    BigDecimal importeBD = new BigDecimal(Float.toString(importe));
    Movimiento unMovimiento = cuentaFacade.retirarDinero(unCliente, unaCuenta, importeBD, descripcion);
    return unMovimiento;
  }

}
