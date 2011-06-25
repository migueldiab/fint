package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author migueldiab
 */
@Stateless
public class UsuarioManager extends AbstractManager<Usuario> implements UsuarioManagerLocal {

  public UsuarioManager() {
    super(Usuario.class);
  }

  @Override
  public Usuario findByLogin(String user) {
    Query q = getEntityManager().createQuery("SELECT u FROM Usuario u WHERE u.login = :login");
    q.setParameter("login", user);
    q.setMaxResults(2);
    final List<Usuario> lista = q.getResultList();
    Usuario resultUsuario = null;
    if (1 == lista.size()) {
      resultUsuario = lista.get(0);
    }
    return resultUsuario;
  }

}
