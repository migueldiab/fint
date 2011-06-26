package edu.ort.dcomp.ute.ws;

import edu.ort.dcomp.ute.modelo.Cliente;
import edu.ort.dcomp.ute.modelo.ClienteFacade;
import edu.ort.dcomp.ute.modelo.Factura;
import edu.ort.dcomp.ute.modelo.FacturaFacade;
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
public class ConsultasWS {

  @EJB
  private FacturaFacade facturaFacade;
  @EJB
  private ClienteFacade clienteFacade;
  /**
   * Web service operation
   */
  @WebMethod(operationName = "obtenerFacturasPendientes")
  public List<Factura> obtenerFacturasPendientes(
          @WebParam(name = "ciUsuario") final long ciUsuario,
          @WebParam(name = "usuario") final String usuario,
          @WebParam(name = "password") final String password) {
    Cliente unCliente = clienteFacade.buscarPorCI(ciUsuario);
    return facturaFacade.buscarPorClienteEstado(unCliente, Factura.Estado.PENDIENTE);
  }

}
