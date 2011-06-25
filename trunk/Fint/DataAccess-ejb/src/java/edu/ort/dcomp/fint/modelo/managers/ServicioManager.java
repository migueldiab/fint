package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Servicio;
import javax.ejb.Stateless;

/**
 *
 * @author migueldiab
 */
@Stateless
public class ServicioManager extends AbstractManager<Servicio> implements ServicioManagerLocal {

  public ServicioManager() {
    super(Servicio.class);
  }

}
