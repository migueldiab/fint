package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Servicio;
import edu.ort.dcomp.fint.modelo.Transaccion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author migueldiab
 */
@Local
public interface TransaccionManagerLocal {

  void persist(Transaccion transaccion);

  Transaccion merge(Transaccion transaccion);

  void remove(Transaccion transaccion);

  Transaccion find(Object id);

  List<Transaccion> findAll();

  List<Transaccion> findRange(int[] range);

  int count();

  public boolean existe(Servicio servicioAsociado, String toString);

  public Transaccion buscarPorServicioNumero(Servicio servicioAsociado, String numero);

}
