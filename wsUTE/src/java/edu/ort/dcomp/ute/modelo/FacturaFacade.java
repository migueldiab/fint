/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.ute.modelo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author migueldiab
 */
@Stateless
public class FacturaFacade extends AbstractFacade<Factura> {
  @PersistenceContext(unitName = "wsUTEPU")
  private EntityManager em;

  protected EntityManager getEntityManager() {
    return em;
  }

  public FacturaFacade() {
    super(Factura.class);
  }

}
