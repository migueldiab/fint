/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.common.log;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author migueldiab
 */
@Entity
public class LogEntry implements Serializable {
  private static final long serialVersionUID = 1L;
  public enum Severidad {
    DEBUG, INFO, WARN, ERROR, CRITICAL
  }
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Enumerated
  Severidad severidad;
  private String mensaje;
  private String trace;
  @Temporal(javax.persistence.TemporalType.TIMESTAMP)
  private Date registrado;


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
    if (!(object instanceof LogEntry)) {
      return false;
    }
    LogEntry other = (LogEntry) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "edu.ort.common.log.LogEntry[id=" + id + "]";
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public Severidad getSeveridad() {
    return severidad;
  }

  public void setSeveridad(Severidad severidad) {
    this.severidad = severidad;
  }

  public Date getTimestamp() {
    return registrado;
  }

  public void setTimestamp(Date timestamp) {
    this.registrado = timestamp;
  }

  public String getTrace() {
    return trace;
  }

  public void setTrace(String trace) {
    this.trace = trace;
  }

}
