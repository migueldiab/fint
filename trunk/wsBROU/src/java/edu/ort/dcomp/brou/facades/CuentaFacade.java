package edu.ort.dcomp.brou.facades;

import edu.ort.dcomp.brou.modelo.Cliente;
import edu.ort.dcomp.brou.modelo.Cuenta;
import edu.ort.dcomp.brou.modelo.Movimiento;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author migueldiab
 */
@Stateless
public class CuentaFacade extends AbstractFacade<Cuenta> {

  @EJB
  private MovimientoFacade movimientoFacade;

  public CuentaFacade() {
    super(Cuenta.class);
  }

  public Cuenta buscarCuentaPorCliente(Cliente unCliente, Long idCuenta) throws Exception {
    Cuenta cuenta = find(idCuenta);
    if (!cuenta.getCliente().equals(unCliente)) {
      throw new Exception("Cliente no está autorizado en esa cuenta.");
    }
    return cuenta;
  }

  public Movimiento retirarDinero(Cliente unCliente, Cuenta unaCuenta, BigDecimal importe, String descripcion) {
    Movimiento unM = movimientoFacade.retirarDinero(unCliente, unaCuenta, importe, descripcion);
    BigDecimal nuevo = unaCuenta.getBalance().subtract(unM.getImporte());
    unaCuenta.setBalance(nuevo);
    edit(unaCuenta);
    return unM;
  }


}
