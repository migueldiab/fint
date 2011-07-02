package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Agenda;
import edu.ort.dcomp.fint.modelo.Usuario;
import java.util.List;
import java.util.Set;
import javax.ejb.LocalBean;

/**
 *
 * @author migueldiab
 */
@LocalBean
public interface AgendaManagerLocal {

  void persist(Agenda agenda);

  Agenda merge(Agenda agenda);

  void remove(Agenda agenda);

  Agenda find(Object id);

  List<Agenda> findAll();

  List<Agenda> findRange(int[] range);

  int count();

  public List<Agenda> buscarPorUsuario(Usuario usuario);

}
