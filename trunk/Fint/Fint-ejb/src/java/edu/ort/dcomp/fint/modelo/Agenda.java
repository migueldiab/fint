package edu.ort.dcomp.fint.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author migueldiab
 */
@Entity
public class Agenda implements Serializable {
  public enum Tipo {
    SERVICIO, SUELDO, JUBILACION, VIATICOS, AGUINALDO, LICENCIA
  }
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String numero;
  @Temporal(javax.persistence.TemporalType.DATE)
  private Date fecha;
  private String destinatario;
  @ManyToOne
  private Categoria categoria;
  @OneToOne
  private Servicio servicio;
  @OneToOne
  private Cuenta cuenta;
  @ManyToOne
  private Usuario usuario;
  @Enumerated(EnumType.STRING)
  private Tipo tipo;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Agenda)) {
      return false;
    }
    Agenda other = (Agenda) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "edu.ort.dcomp.fint.modelo.Agenda[id=" + id + "]";
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

  public Tipo getTipo() {
    return tipo;
  }

  public void setTipo(Tipo tipo) {
    this.tipo = tipo;
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
