/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.fint.modelo.facades;

import edu.ort.dcomp.fint.modelo.EntidadFinanciera;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author migueldiab
 */
@Stateless
public class EntidadFinancieraFacade extends AbstractFacade<EntidadFinanciera> implements EntidadFinancieraFacadeLocal {
  @PersistenceContext(unitName = "FintPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public EntidadFinancieraFacade() {
    super(EntidadFinanciera.class);
  }

}
