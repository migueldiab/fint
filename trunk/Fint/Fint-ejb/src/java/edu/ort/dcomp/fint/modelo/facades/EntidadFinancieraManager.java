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
public class EntidadFinancieraManager extends AbstractFacade<EntidadFinanciera> implements EntidadFinancieraManagerLocal {
  @PersistenceContext(unitName = "FintPU")
  private EntityManager em;

  protected EntityManager getEntityManager() {
    return em;
  }

  public EntidadFinancieraManager() {
    super(EntidadFinanciera.class);
  }

}
