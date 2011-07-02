package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Categoria;
import java.util.List;
import javax.ejb.LocalBean;

/**
 *
 * @author migueldiab
 */
@LocalBean
public interface CategoriaManagerLocal {

  void persist(Categoria categoria);

  Categoria merge(Categoria categoria);

  void remove(Categoria categoria);

  Categoria find(Object id);

  List<Categoria> findAll();

  List<Categoria> findRange(int[] range);

  int count();

}
