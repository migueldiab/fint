package edu.ort.dcomp.fint.engine;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author migueldiab
 */
@Stateless
@ManagedBean
public class Facade {

  public String getWelcomeMessage() {
    return "Bienvenido a la página de inicio de FINT";
  }
}
