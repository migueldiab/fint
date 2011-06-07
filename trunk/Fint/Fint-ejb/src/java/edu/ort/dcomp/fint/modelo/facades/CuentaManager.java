/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.fint.modelo.facades;

import edu.ort.dcomp.fint.modelo.Cuenta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author migueldiab
 */
@Stateless
public class CuentaManager extends AbstractManager<Cuenta> implements CuentaManagerLocal {
  @PersistenceContext(unitName = "FintPU")
  private EntityManager em;

  protected EntityManager getEntityManager() {
    return em;
  }

  public CuentaManager() {
    super(Cuenta.class);
  }

}
