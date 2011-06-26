package edu.ort.dcomp.fint.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Embeddable;

/**
 *
 * @author migueldiab
 */
@Embeddable
public class Recurrencia implements Serializable {
  private static final Long serialVersionUID = 1L;

  public enum Tipo {
    DIARIA, SEMANAL, MENSUAL, ANUAL
  }
  public enum Dias {
    LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
  }
  private Tipo tipo;
  private Integer cadaX;
  private List<Dias> dias;

  public Integer getCadaX() {
    return cadaX;
  }

  public void setCadaX(Integer cadaX) {
    this.cadaX = cadaX;
  }

  public List<Dias> getDias() {
    return dias;
  }

  public void setDias(List<Dias> dias) {
    this.dias = dias;
  }

  public Tipo getTipo() {
    return tipo;
  }

  public void setTipo(Tipo tipo) {
    this.tipo = tipo;
  }

}
