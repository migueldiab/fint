package edu.ort.dcomp.fint.monitor;

import edu.ort.dcomp.fint.monitor.client.Factura;
import java.net.MalformedURLException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author migueldiab
 */
@Stateless
@LocalBean
public class FacturaParser {

  @EJB
  WSFacade wSFacade;

  public void leerFacturasPendientes() throws MalformedURLException {
    Long ciUsuario = 28597576L;
    List<Factura> result = wSFacade.getProxy().obtenerFacturasPendientes(ciUsuario, "", "");
    for (Factura factura : result) {
      System.out.println("factura" + factura.getConcepto());
    }
  }
}
