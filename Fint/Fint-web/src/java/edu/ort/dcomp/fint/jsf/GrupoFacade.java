/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.fint.jsf;

import edu.ort.dcomp.fint.modelo.Grupo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author migueldiab
 */
@Stateless
public class GrupoFacade extends AbstractFacade<Grupo> {
  @PersistenceContext(unitName = "FintPU")
  private EntityManager em;

  protected EntityManager getEntityManager() {
    return em;
  }

  public GrupoFacade() {
    super(Grupo.class);
  }

}
