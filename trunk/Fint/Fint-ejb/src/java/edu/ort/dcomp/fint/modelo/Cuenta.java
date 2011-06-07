package edu.ort.dcomp.fint.modelo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author migueldiab
 */
@Entity
public class Cuenta implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @NotNull
  private String nombre;
  @OneToMany
  @JoinColumn(name="id_cuenta")
  private Set<Transaccion> transacciones;
  @ManyToOne
  @NotNull
  private EntidadFinanciera entidadFinanciera;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public EntidadFinanciera getTipoCuenta() {
    return entidadFinanciera;
  }

  public void setTipoCuenta(EntidadFinanciera tipoCuenta) {
    this.entidadFinanciera = tipoCuenta;
  }

  public Set<Transaccion> getTransacciones() {
    return transacciones;
  }

  public void setTransacciones(Set<Transaccion> transacciones) {
    this.transacciones = transacciones;
  }

  
}
