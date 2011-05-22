/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.fint.modelo.facades;

import edu.ort.dcomp.fint.modelo.Usuario;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author migueldiab
 */
@Remote
public interface UsuarioFacadeRemote {

  void create(Usuario usuario);

  void edit(Usuario usuario);

  void remove(Usuario usuario);

  Usuario find(Object id);

  List<Usuario> findAll();

  List<Usuario> findRange(int[] range);

  int count();

}
