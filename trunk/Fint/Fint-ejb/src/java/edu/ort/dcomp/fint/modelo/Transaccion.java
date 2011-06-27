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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author migueldiab
 */
@Entity
public class Transaccion implements Serializable {
  public enum Tipo {
    RETIRO, DEPOSITO, TRANSFERENCIA, CUENTA
  }
  public enum Estado {
    PENDIENTE, PAGA, VENCIDA, CANCELADA, DESCONOCIDO
  }
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String numero;
  @Temporal(javax.persistence.TemporalType.DATE)
  private Date fechaIngreso;
  @Temporal(javax.persistence.TemporalType.DATE)
  private Date fechaEmision;
  @Temporal(javax.persistence.TemporalType.DATE)
  private Date fechaPago;
  @Temporal(javax.persistence.TemporalType.DATE)
  private Date fechaActualizacion;
  @Temporal(javax.persistence.TemporalType.DATE)
  private Date fechaVencimiento;
  private String destinatario;
  @ManyToOne
  @JoinColumn(name="id_cuenta")
  private Cuenta cuenta;
  @ManyToOne
  @JoinColumn(name="id_servicio")
  private Servicio servicio;
  @ManyToOne
  @JoinColumn(name="id_usuario")
  private Usuario usuario;
  @Enumerated(EnumType.STRING)
  private Tipo tipo;
  private String concepto;
  @Enumerated(EnumType.STRING)
  private Estado estado;  
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

  public String getDestinatario() {
    return destinatario;
  }

  public void setDestinatario(String destinatario) {
    this.destinatario = destinatario;
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

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Date getFechaEmision() {
    return fechaEmision;
  }

  public void setFechaEmision(Date fechaEmision) {
    this.fechaEmision = fechaEmision;
  }

  public Date getFechaIngreso() {
    return fechaIngreso;
  }

  public void setFechaIngreso(Date fechaIngreso) {
    this.fechaIngreso = fechaIngreso;
  }

  public Date getFechaPago() {
    return fechaPago;
  }

  public void setFechaPago(Date fechaPago) {
    this.fechaPago = fechaPago;
  }

  public Date getFechaVencimiento() {
    return fechaVencimiento;
  }

  public void setFechaVencimiento(Date fechaVencimiento) {
    this.fechaVencimiento = fechaVencimiento;
  }

  public String getConcepto() {
    return concepto;
  }

  public void setConcepto(String concepto) {
    this.concepto = concepto;
  }

  public Estado getEstado() {
    return estado;
  }

  public void setEstado(Estado estado) {
    this.estado = estado;
  }

  public Date getFechaActualizacion() {
    return fechaActualizacion;
  }

  public void setFechaActualizacion(Date fechaActualizacion) {
    this.fechaActualizacion = fechaActualizacion;
  }

}
