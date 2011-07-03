package edu.ort.dcomp.brou.facades;

import edu.ort.dcomp.brou.modelo.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author migueldiab
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

  public ClienteFacade() {
    super(Cliente.class);
  }

  public Cliente validarUsuario(String usuario, String password) {
    Long ciCliente = Long.parseLong(usuario);
    Query q = getEntityManager().createQuery("SELECT c FROM Cliente c WHERE c.ci = :ci");
    q.setParameter("ci", ciCliente);
    q.setMaxResults(2);
    final List<Cliente> lista = q.getResultList();
    Cliente resultUsuario = null;
    if (1 == lista.size()) {
      resultUsuario = lista.get(0);
    }
    return resultUsuario;
  }

}
