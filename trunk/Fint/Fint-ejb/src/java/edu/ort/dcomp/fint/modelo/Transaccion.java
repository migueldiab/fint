package edu.ort.dcomp.fint.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author migueldiab
 */
@Entity
public class Transaccion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToMany
  private Usuario usuario;
  @OneToMany
  private Cuenta cuenta;
  @OneToMany
  private Servicio servicio;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Cuenta getCuenta() {
    return cuenta;
  }

  public void setCuenta(Cuenta cuenta) {
    this.cuenta = cuenta;
  }

  public Servicio getServicio() {
    return servicio;
  }

  public void setServicio(Servicio servicio) {
    this.servicio = servicio;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }


}
