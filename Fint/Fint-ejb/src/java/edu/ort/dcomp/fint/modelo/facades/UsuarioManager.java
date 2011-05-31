package edu.ort.dcomp.fint.modelo.facades;

import edu.ort.dcomp.fint.modelo.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author migueldiab
 */
@Stateless
public class UsuarioManager extends AbstractManager<Usuario> implements UsuarioManagerLocal {
  @PersistenceContext(unitName = "FintPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

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
