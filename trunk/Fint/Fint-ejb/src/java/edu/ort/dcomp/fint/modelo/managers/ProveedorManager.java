/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Proveedor;
import javax.ejb.Stateless;

/**
 *
 * @author migueldiab
 */
@Stateless
public class ProveedorManager extends AbstractManager<Proveedor> implements ProveedorManagerLocal {
  public ProveedorManager() {
    super(Proveedor.class);
  }

}
