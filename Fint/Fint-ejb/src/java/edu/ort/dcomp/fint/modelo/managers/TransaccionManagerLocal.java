package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Agenda;
import edu.ort.dcomp.fint.modelo.Cuenta;
import edu.ort.dcomp.fint.modelo.Servicio;
import edu.ort.dcomp.fint.modelo.Transaccion;
import edu.ort.dcomp.fint.modelo.Usuario;
import java.util.List;
import javax.ejb.LocalBean;

/**
 *
 * @author migueldiab
 */
@LocalBean
public interface TransaccionManagerLocal {

  void persist(Transaccion transaccion);

  Transaccion merge(Transaccion transaccion);

  void remove(Transaccion transaccion);

  Transaccion find(Object id);

  List<Transaccion> findAll();

  List<Transaccion> findRange(int[] range);

  int count();

  public boolean existe(Servicio servicio, String numero);

  public Transaccion buscarPorServicioNumero(Servicio servicio, String numero);

  public List<Transaccion> obtenerPorCuentaOrdenadoPorFecha(Cuenta cuenta);

  public List<Transaccion> buscarFacturasPendientes(Agenda agenda);

  public List<Transaccion> obtenerPorServicioOrdenadoPorFecha(Servicio servicio);

  public List<Transaccion> obtenerPendientesPorUsuario(Usuario usuario);

}
