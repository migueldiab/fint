package edu.ort.dcomp.brou.facades;

import edu.ort.dcomp.brou.modelo.Cliente;
import edu.ort.dcomp.brou.modelo.Cuenta;
import edu.ort.dcomp.brou.modelo.Movimiento;
import java.math.BigDecimal;
import java.util.Date;
import javax.ejb.Stateless;

/**
 *
 * @author migueldiab
 */
@Stateless
public class MovimientoFacade extends AbstractFacade<Movimiento> {
  
  public MovimientoFacade() {
    super(Movimiento.class);
  }

  public Movimiento retirarDinero(Cliente unCliente, Cuenta unaCuenta, BigDecimal importe, String descripcion) {
    Movimiento unM = new Movimiento();
    unM.setCuenta(unaCuenta);
    unM.setFecha(new Date());
    unM.setConcepto(descripcion);
    unM.setImporte(importe);
    unM.setTipo(Movimiento.Tipo.RETIRO);
    create(unM);    
    return unM;
  }

}
