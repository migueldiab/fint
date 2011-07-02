package edu.ort.dcomp.fint.engine;

import edu.ort.dcomp.fint.modelo.managers.TransaccionManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author migueldiab
 */
@Stateless
@LocalBean
public class TransaccionFacade {

  @EJB
  TransaccionManagerLocal transaccionManagerLocal;

}
