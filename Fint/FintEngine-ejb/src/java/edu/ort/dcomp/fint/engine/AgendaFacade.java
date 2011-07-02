package edu.ort.dcomp.fint.engine;

import edu.ort.dcomp.fint.modelo.Agenda;
import edu.ort.dcomp.fint.modelo.managers.AgendaManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author migueldiab
 */
@Stateless
@LocalBean
public class AgendaFacade {

  @EJB
  AgendaManagerLocal agendaManagerLocal;

  public void borrarAgenda(Agenda unaAgenda) {
    agendaManagerLocal.remove(unaAgenda);
  }
    
 
}
