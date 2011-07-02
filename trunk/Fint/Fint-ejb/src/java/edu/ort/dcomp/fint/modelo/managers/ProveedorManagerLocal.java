package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Proveedor;
import java.util.List;
import javax.ejb.LocalBean;

/**
 *
 * @author migueldiab
 */
@LocalBean
public interface ProveedorManagerLocal {

  void persist(Proveedor tipoServicio);

  Proveedor merge(Proveedor tipoServicio);

  void remove(Proveedor tipoServicio);

  Proveedor find(Object id);

  List<Proveedor> findAll();

  List<Proveedor> findRange(int[] range);

  int count();

  public Proveedor buscarPorNombre(String PROVEEDOR_ASOCIADO);

}
