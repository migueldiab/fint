package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Agenda;
import edu.ort.dcomp.fint.modelo.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author migueldiab
 */
@Stateless
public class AgendaManager extends AbstractManager<Agenda> implements AgendaManagerLocal {
  public AgendaManager() {
    super(Agenda.class);
  }

  @Override
  public List<Agenda> buscarPorUsuario(Usuario usuario) {
    Query q = getEntityManager().createQuery("SELECT a FROM Agenda a WHERE a.usuario = :usuario");
    q.setParameter("usuario", usuario);
    final List<Agenda> lista = q.getResultList();
    return lista;
  }

  @Override
  public List<Agenda> buscarAgendasDelDia() {
    Query q = getEntityManager().createQuery("SELECT a FROM Agenda a");
    final List<Agenda> lista = q.getResultList();
    return lista;
  }

}
