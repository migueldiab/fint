package edu.ort.dcomp.fint.monitor;

import edu.ort.dcomp.fint.modelo.Servicio;
import java.net.MalformedURLException;
import javax.ejb.Asynchronous;

/**
 *
 * @author migueldiab
 */
public interface GenericProveedorParser {

  Servicio getServicioAsociado();

  @Asynchronous
  void leerFacturasPendientes() throws MalformedURLException;

  @Asynchronous
  void leerFacturasPasadas() throws MalformedURLException;

}
