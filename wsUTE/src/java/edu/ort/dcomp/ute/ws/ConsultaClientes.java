package edu.ort.dcomp.ute.ws;

import edu.ort.dcomp.ute.controller.ClienteController;
import edu.ort.dcomp.ute.controller.FacturaController;
import edu.ort.dcomp.ute.modelo.Factura;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;

/**
 *
 * @author migueldiab
 */
@WebService()
@Stateless()
public class ConsultaClientes {

  @EJB
  FacturaController facturaController;
  
  public List<Factura> obtenerFacturasPendientePorCliente(final String usuario, final String password, final long ciCliente) {
    return facturaController.obtenerFacturasPorCliente(ciCliente, Factura.Estado.PENDIENTE);
  }

  public List<Factura> obtenerFacturasPagasPorCliente(final String usuario, final String password, final long ciCliente) {
    return facturaController.obtenerFacturasPorCliente(ciCliente, Factura.Estado.PAGA);
  }

  public List<Factura> obtenerFacturasPendientesPorCliente(final String usuario, final String password, final long ciCliente) {
    return facturaController.obtenerFacturasPorCliente(ciCliente, Factura.Estado.PENDIENTE);
  }

  public List<Factura> obtenerFacturasVencidasPorCliente(final String usuario, final String password, final long ciCliente) {
    return facturaController.obtenerFacturasPorCliente(ciCliente, Factura.Estado.VENCIDA);
  }


}
