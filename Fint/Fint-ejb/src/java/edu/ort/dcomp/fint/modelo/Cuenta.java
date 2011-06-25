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
  private Long id;
  @NotNull
  private String nombre;
  @OneToMany
  @JoinColumn(name="id_cuenta")
  private Set<Transaccion> transacciones;
  @ManyToOne
  @NotNull
  private EntidadFinanciera entidadFinanciera;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public EntidadFinanciera getEntidadFinanciera() {
    return entidadFinanciera;
  }

  public void setEntidadFinanciera(EntidadFinanciera tipoCuenta) {
    this.entidadFinanciera = tipoCuenta;
  }

  public Set<Transaccion> getTransacciones() {
    return transacciones;
  }

  public void setTransacciones(Set<Transaccion> transacciones) {
    this.transacciones = transacciones;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Cuenta other = (Cuenta) obj;
    if (this.id != other.id) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int hash = 9;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public String toString() {
    return getNombre();
  }

  
}
