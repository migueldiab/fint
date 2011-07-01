package edu.ort.dcomp.ute.modelo;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author migueldiab
 */
@Entity
public class Recibo {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  @NotNull
  private Factura factura;
  @NotNull
  private String claveEntidad;
  @Temporal(javax.persistence.TemporalType.DATE)
  private Date fechaRecibo;
  private BigDecimal importe;

  public String getClaveEntidad() {
    return claveEntidad;
  }

  public void setClaveEntidad(String claveEntidad) {
    this.claveEntidad = claveEntidad;
  }

  public Factura getFactura() {
    return factura;
  }

  public void setFactura(Factura factura) {
    this.factura = factura;
  }

  public Date getFechaRecibo() {
    return fechaRecibo;
  }

  public void setFechaRecibo(Date fechaRecibo) {
    this.fechaRecibo = fechaRecibo;
  }

  public BigDecimal getImporte() {
    return importe;
  }

  public void setImporte(BigDecimal importe) {
    this.importe = importe;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
