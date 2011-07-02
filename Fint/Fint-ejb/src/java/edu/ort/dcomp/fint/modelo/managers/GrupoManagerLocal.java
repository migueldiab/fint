package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Grupo;
import java.util.List;
import javax.ejb.LocalBean;

/**
 *
 * @author migueldiab
 */
@LocalBean
public interface GrupoManagerLocal {

  void persist(Grupo grupo);

  Grupo merge(Grupo grupo);

  void remove(Grupo grupo);

  Grupo find(Object id);

  List<Grupo> findAll();

  List<Grupo> findRange(int[] range);

  int count();

}
