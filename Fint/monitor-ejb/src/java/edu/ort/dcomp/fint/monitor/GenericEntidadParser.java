package edu.ort.dcomp.fint.monitor;

import edu.ort.dcomp.fint.modelo.Cuenta;
import edu.ort.dcomp.fint.modelo.EntidadFinanciera;
import edu.ort.dcomp.fint.modelo.Usuario;
import edu.ort.dcomp.fint.ws.entidades.Movimiento;
import java.math.BigDecimal;
import javax.ejb.LocalBean;

/**
 *
 * @author migueldiab
 */
@LocalBean
public interface GenericEntidadParser {
  
  EntidadFinanciera getEntidadAsociada();

  Cuenta actualizarCuenta(Cuenta cuenta, Usuario usuario) throws Exception;

  Movimiento retirarDinero(Cuenta cuenta, Usuario usuario, BigDecimal importe) throws Exception;
}
