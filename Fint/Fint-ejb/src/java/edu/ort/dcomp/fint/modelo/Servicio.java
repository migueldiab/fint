package edu.ort.dcomp.fint.modelo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author migueldiab
 */
@Entity
public class Servicio implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  private String nombre;
  private Long numero;
  @OneToMany
  @JoinColumn(name="id_servicio")
  private Set<Transaccion> transacciones;
  @ManyToOne
  @NotNull
  @JoinColumn(name="id_usuario")
  private Usuario usuario;
  @ManyToOne
  @NotNull
  private Proveedor proveedor;
  @ManyToOne
  @JoinColumn(name="id_categoria")
  private Categoria categoria;
  private Boolean conectado;
  private String usuarioWs;
  private String passWs;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Proveedor getProveedor() {
    return proveedor;
  }

  public void setProveedor(Proveedor proveedor) {
    this.proveedor = proveedor;
  }

  public Set<Transaccion> getTransacciones() {
    return transacciones;
  }

  public void setTransacciones(Set<Transaccion> transacciones) {
    this.transacciones = transacciones;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Servicio other = (Servicio) obj;
    if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
    return hash;
  }

  @Override
  public String toString() {
    return getNombre();
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public Long getNumero() {
    return numero;
  }

  public void setNumero(Long numero) {
    this.numero = numero;
  }

  public Boolean getConectado() {
    return conectado;
  }

  public void setConectado(Boolean conectado) {
    this.conectado = conectado;
  }

  public String getPassWs() {
    return passWs;
  }

  public void setPassWs(String passWs) {
    this.passWs = passWs;
  }

  public String getUsuarioWs() {
    return usuarioWs;
  }

  public void setUsuarioWs(String usuarioWs) {
    this.usuarioWs = usuarioWs;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }


}
