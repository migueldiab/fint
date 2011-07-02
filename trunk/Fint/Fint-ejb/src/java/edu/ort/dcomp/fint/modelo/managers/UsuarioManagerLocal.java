package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Usuario;
import java.util.List;
import javax.ejb.LocalBean;

/**
 *
 * @author migueldiab
 */
@LocalBean
public interface UsuarioManagerLocal {

  void persist(Usuario usuario);

  Usuario merge(Usuario usuario);

  void remove(Usuario usuario);

  Usuario find(Object id);

  List<Usuario> findAll();

  List<Usuario> findRange(int[] range);

  int count();

  public Usuario findByLogin(String user);

  void flush();

}
