/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Transaccion;
import javax.ejb.Stateless;

/**
 *
 * @author migueldiab
 */
@Stateless
public class TransaccionManager extends AbstractManager<Transaccion> implements TransaccionManagerLocal {

  public TransaccionManager() {
    super(Transaccion.class);
  }

}
