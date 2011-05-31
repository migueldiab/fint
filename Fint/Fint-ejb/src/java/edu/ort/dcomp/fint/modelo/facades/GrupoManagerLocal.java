/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.dcomp.fint.modelo.facades;

import edu.ort.dcomp.fint.modelo.Grupo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author migueldiab
 */
@Local
public interface GrupoManagerLocal {

  void create(Grupo grupo);

  void edit(Grupo grupo);

  void remove(Grupo grupo);

  Grupo find(Object id);

  List<Grupo> findAll();

  List<Grupo> findRange(int[] range);

  int count();

}
