package edu.ort.dcomp.fint.modelo;

import edu.ort.common.utils.EncryptUtils;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
    @UniqueConstraint(columnNames={"LOGIN"})
)
public class Usuario implements Serializable {
  private static final long serialVersionUID = 201105301000L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  private String login;
  @NotNull
  private String contrasena;
  private String nombre;
  private String apellido;
  private Long ci;
  @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
  @JoinColumn(name="id_usuario")
  private Set<Cuenta> cuentas;
  @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
  @JoinColumn(name="id_usuario")
  private Set<Servicio> servicios;
  @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
  @JoinColumn(name="id_usuario")
  private Set<Transaccion> transacciones;

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

  public Set<Cuenta> getCuentas() {
    return cuentas;
  }

  public void setCuentas(Set<Cuenta> cuentas) {
    this.cuentas = cuentas;
  }

  public Set<Servicio> getServicios() {
    return servicios;
  }

  public void setServicios(Set<Servicio> servicios) {
    this.servicios = servicios;
  }

  public void agregarCuenta(Cuenta cuenta) {
    if (null == cuentas) {
      cuentas = new HashSet<Cuenta>();
    }
    cuentas.add(cuenta);
  }

  public void agregarServicio(Servicio servicio) {
    if (null == servicios) {
      servicios = new HashSet<Servicio>();
    }
    servicios.add(servicio);
  }

}
