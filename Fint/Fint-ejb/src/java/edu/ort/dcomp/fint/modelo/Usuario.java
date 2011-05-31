package edu.ort.dcomp.fint.modelo;

import edu.ort.common.utils.EncryptUtils;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author migueldiab
 */
@Entity
public class Usuario implements Serializable {
  private static final long serialVersionUID = 201105301000L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String login;
  private String contrasena;
  private String nombre;
  private String apellido;
  private Long ci;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    int hash = 30;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    Boolean areEqual = Boolean.FALSE;
    if ((object instanceof Usuario)) {
      Usuario other = (Usuario) object;
      if (this.id != null && this.id.equals(other.id)) {
        areEqual = true;
      }
    }
    return areEqual;
  }

  @Override
  public String toString() {
    return "edu.ort.dcomp.fint.modelo.Usuario[id=" + id + "]";
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getContrasena() {
    return contrasena;
  }

  public void setContrasena(String contrasena) throws UnsupportedEncodingException, NoSuchAlgorithmException {
    this.contrasena = EncryptUtils.encodeSha256(contrasena);
  }

  public Long getCi() {
    return ci;
  }

  public void setCi(Long ci) {
    this.ci = ci;
  }

  public void setContrasenaHash(String contrasena) {
    this.contrasena = contrasena;
  }

}
