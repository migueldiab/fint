/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.fint.modelo.facades;

import edu.ort.dcomp.fint.modelo.Proveedor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author migueldiab
 */
@Stateless
public class ProveedorManager extends AbstractFacade<Proveedor> implements ProveedorManagerLocal {
  @PersistenceContext(unitName = "FintPU")
  private EntityManager em;

  protected EntityManager getEntityManager() {
    return em;
  }

  public ProveedorManager() {
    super(Proveedor.class);
  }

}
