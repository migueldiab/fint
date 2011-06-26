/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.fint.backend.modelo;

import edu.ort.dcomp.fint.modelo.Categoria;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author migueldiab
 */
@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> {
  @PersistenceContext(unitName = "backend-webPU")
  private EntityManager em;

  protected EntityManager getEntityManager() {
    return em;
  }

  public CategoriaFacade() {
    super(Categoria.class);
  }

}
