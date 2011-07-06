package edu.ort.dcomp.fint.monitor;

import edu.ort.common.exceptions.WebServiceCommunicationException;
import edu.ort.dcomp.fint.modelo.Proveedor;
import edu.ort.dcomp.fint.modelo.Servicio;
import edu.ort.dcomp.fint.modelo.Transaccion;
import edu.ort.dcomp.fint.ws.servicio.Recibo;
import java.util.List;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;

/**
 *
 * @author migueldiab
 */
@LocalBean
public interface GenericProveedorParser {

  Proveedor getProveedorAsociado();

  @Asynchronous
  void leerFacturasPendientes(Servicio servicio) throws WebServiceCommunicationException;

  @Asynchronous
  void leerFacturasPasadas() throws WebServiceCommunicationException;

  public List<Servicio> listarCuentas(String id, String password) throws WebServiceCommunicationException;

  public Recibo pagarCuenta(Transaccion factura, String clavePago);
}
