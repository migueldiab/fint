package edu.ort.dcomp.fint.engine;

import edu.ort.dcomp.fint.modelo.Cuenta;
import edu.ort.dcomp.fint.modelo.Servicio;
import edu.ort.dcomp.fint.modelo.Transaccion;
import edu.ort.dcomp.fint.modelo.managers.TransaccionManagerLocal;
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
public class TransaccionFacade {

  @EJB
  private TransaccionManagerLocal ejbTransaccion;

  public List<Transaccion> obtenerPorCuentaOrdenadoPorFecha(Cuenta cuenta) {
    return ejbTransaccion.obtenerPorCuentaOrdenadoPorFecha(cuenta);
  }

  public List<Transaccion> obtenerPorServicioOrdenadoPorFecha(Servicio servicio) {
    return ejbTransaccion.obtenerPorServicioOrdenadoPorFecha(servicio);
  }

}
