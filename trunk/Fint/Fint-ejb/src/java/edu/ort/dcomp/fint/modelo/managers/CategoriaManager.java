package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Categoria;
import javax.ejb.Stateless;

/**
 *
 * @author migueldiab
 */
@Stateless
public class CategoriaManager extends AbstractManager<Categoria> {
  public CategoriaManager() {
    super(Categoria.class);
  }

}
