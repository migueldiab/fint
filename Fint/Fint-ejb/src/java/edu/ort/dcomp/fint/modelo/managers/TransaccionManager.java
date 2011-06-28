/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Servicio;
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

  @Override
  public boolean existe(Servicio servicioAsociado, String toString) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Transaccion buscarPorServicioNumero(Servicio servicioAsociado, String toString) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
