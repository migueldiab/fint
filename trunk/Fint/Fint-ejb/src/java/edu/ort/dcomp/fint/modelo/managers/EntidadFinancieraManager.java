package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.EntidadFinanciera;
import javax.ejb.Stateless;

/**
 *
 * @author migueldiab
 */
@Stateless
public class EntidadFinancieraManager extends AbstractManager<EntidadFinanciera> implements EntidadFinancieraManagerLocal {

  public EntidadFinancieraManager() {
    super(EntidadFinanciera.class);
  }

}
