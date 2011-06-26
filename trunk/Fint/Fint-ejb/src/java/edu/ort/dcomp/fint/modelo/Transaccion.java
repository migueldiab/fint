package edu.ort.dcomp.fint.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author migueldiab
 */
@Entity
public class Transaccion implements Serializable {
  public enum Tipo {
    RETIRO, DEPOSITO, TRANSFERENCIA
  }
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String numero;
  @Temporal(javax.persistence.TemporalType.DATE)
  private Date fecha;
  private String destinatario;
  private Categoria categoria;
  @ManyToOne
  private Cuenta cuenta;
  @ManyToOne
  private Servicio servicio;
  @Enumerated(EnumType.STRING)
  private Tipo tipo;
  
  private BigDecimal importe;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getImporte() {
    return importe;
  }

  public void setImporte(BigDecimal importe) {
    this.importe = importe;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public String getDestinatario() {
    return destinatario;
  }

  public void setDestinatario(String destinatario) {
    this.destinatario = destinatario;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
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

  public Tipo getTipo() {
    return tipo;
  }

  public void setTipo(Tipo tipo) {
    this.tipo = tipo;
  }

}
