package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Grupo;
import javax.ejb.Stateless;

/**
 *
 * @author migueldiab
 */
@Stateless
public class GrupoManager extends AbstractManager<Grupo> implements GrupoManagerLocal {
  public GrupoManager() {
    super(Grupo.class);
  }

}
