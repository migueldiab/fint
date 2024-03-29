package edu.ort.dcomp.fint.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *
 * @author migueldiab
 */
@Entity
@Table(
  uniqueConstraints=
    @UniqueConstraint(columnNames={"NOMBRE"})
)
public class Proveedor implements Serializable {
  private static final long serialVersionUID = 1L;
  public enum wsParser {
    UTEParser, OSEParser, AntelParser
  }
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @NotNull
  private String nombre;
  @Enumerated
  @NotNull
  private wsParser parser;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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
    if (!(object instanceof Proveedor)) {
      return false;
    }
    Proveedor other = (Proveedor) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "edu.ort.dcomp.fint.modelo.TipoServicio[id=" + id + "]";
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public wsParser getParser() {
    return parser;
  }

  public void setParser(wsParser parser) {
    this.parser = parser;
  }

  
}
