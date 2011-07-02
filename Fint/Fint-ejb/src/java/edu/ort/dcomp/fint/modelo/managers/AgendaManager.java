package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Agenda;
import javax.ejb.Stateless;

/**
 *
 * @author migueldiab
 */
@Stateless
public class AgendaManager extends AbstractManager<Agenda> implements AgendaManagerLocal {
  public AgendaManager() {
    super(Agenda.class);
  }

}
