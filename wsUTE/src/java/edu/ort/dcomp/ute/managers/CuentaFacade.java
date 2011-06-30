package edu.ort.dcomp.ute.managers;

import edu.ort.dcomp.ute.modelo.Cuenta;
import edu.ort.dcomp.ute.modelo.Factura;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author migueldiab
 */
@Stateless
public class CuentaFacade extends AbstractFacade<Cuenta> {
  @PersistenceContext(unitName = "wsUTEPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public CuentaFacade() {
    super(Cuenta.class);
  }

  public void cargarFactura(Factura factura) {
    Cuenta unaCuenta = factura.getCuenta();
    edit(unaCuenta);
    BigDecimal nuevoBal = unaCuenta.getBalance().subtract(factura.getImporte());
    unaCuenta.setBalance(nuevoBal);
  }

  public void quitarFactura(Factura factura) {
    Cuenta unaCuenta = factura.getCuenta();
    edit(unaCuenta);
    BigDecimal nuevoBal = unaCuenta.getBalance().add(factura.getImporte());
    unaCuenta.setBalance(nuevoBal);
  }

  public void pagarFactura(Factura factura) {
    Cuenta unaCuenta = factura.getCuenta();
    edit(unaCuenta);
    BigDecimal nuevoBal = unaCuenta.getBalance().add(factura.getImporte());
    unaCuenta.setBalance(nuevoBal);
  }

}
