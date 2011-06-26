/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.fint.backend.modelo;

import edu.ort.dcomp.fint.modelo.Agenda;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author migueldiab
 */
@Stateless
public class AgendaFacade extends AbstractFacade<Agenda> {
  @PersistenceContext(unitName = "backend-webPU")
  private EntityManager em;

  protected EntityManager getEntityManager() {
    return em;
  }

  public AgendaFacade() {
    super(Agenda.class);
  }

}
