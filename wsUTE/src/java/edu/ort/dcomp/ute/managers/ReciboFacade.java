/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.ute.managers;

import edu.ort.dcomp.ute.modelo.Cuenta;
import edu.ort.dcomp.ute.modelo.Factura;
import edu.ort.dcomp.ute.modelo.Recibo;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author migueldiab
 */
@Stateless
public class ReciboFacade extends AbstractFacade<Recibo> {
  @PersistenceContext(unitName = "wsUTEPU")
  private EntityManager em;

  protected EntityManager getEntityManager() {
    return em;
  }

  public ReciboFacade() {
    super(Recibo.class);
  }

  public Recibo pagarFactura(Cuenta unaCuenta, Factura unaFactura, String claveEntidadFinanciera) {
    Recibo result = null;
    if (unaFactura.getCuenta().equals(unaCuenta)) {
      result = new Recibo();
      result.setClaveEntidad(claveEntidadFinanciera);
      result.setFactura(unaFactura);
      result.setFechaRecibo(new Date());
      result.setImporte(unaFactura.getImporte());
      create(result);
      unaFactura.setEstado(Factura.Estado.PAGA);
    }
    return result;
  }

}
