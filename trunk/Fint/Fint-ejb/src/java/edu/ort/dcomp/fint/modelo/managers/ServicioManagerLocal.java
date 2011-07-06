package edu.ort.dcomp.fint.modelo.managers;

import edu.ort.dcomp.fint.modelo.Agenda;
import edu.ort.dcomp.fint.modelo.Servicio;
import edu.ort.dcomp.fint.modelo.Transaccion;
import java.util.List;
import javax.ejb.LocalBean;

/**
 *
 * @author migueldiab
 */
@LocalBean
public interface ServicioManagerLocal {

  void persist(Servicio servicio);

  Servicio merge(Servicio servicio);

  void remove(Servicio servicio);

  Servicio find(Object id);

  List<Servicio> findAll();

  List<Servicio> findRange(int[] range);

  int count();

  public Servicio buscarPorNombre(String SERVICIO_ASOCIADO);

  public List<Servicio> buscarServiciosConectados();

}
