package edu.ort.dcomp.fint.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author migueldiab
 */
@Entity
public class Cuenta implements Serializable {
  public enum Tipo {
    CAJA_DE_AHORRO, CUENTA_CORRIENTE, TARJETA_DE_CREDITO, PRESTAMO, LINEA_DE_CREDITO
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  private String nombre;
  private Long numeroCuenta;
  @OneToMany
  @JoinColumn(name="id_cuenta")
  private Set<Transaccion> transacciones;
  @ManyToOne
  @NotNull
  private EntidadFinanciera entidadFinanciera;
  private Tipo tipo;
  @Temporal(javax.persistence.TemporalType.TIMESTAMP)
  private Date fechaActualizacion;
  private BigDecimal saldo;

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

  public Tipo getTipo() {
    return tipo;
  }

  public void setTipo(Tipo tipo) {
    this.tipo = tipo;
  }

  public Date getFechaActualizacion() {
    return fechaActualizacion;
  }

  public void setFechaActualizacion(Date fechaActualizacion) {
    this.fechaActualizacion = fechaActualizacion;
  }

  public BigDecimal getSaldo() {
    return saldo;
  }

  public void setSaldo(BigDecimal saldo) {
    this.saldo = saldo;
  }

  public Long getNumeroCuenta() {
    return numeroCuenta;
  }

  public void setNumeroCuenta(Long numeroCuenta) {
    this.numeroCuenta = numeroCuenta;
  }

  
}
