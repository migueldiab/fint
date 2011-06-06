package edu.ort.dcomp.fint.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author migueldiab
 */
@Entity
public class Cuenta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String nombre;
  
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

}
