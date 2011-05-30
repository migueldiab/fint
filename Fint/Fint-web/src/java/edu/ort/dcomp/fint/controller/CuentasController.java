package edu.ort.dcomp.fint.controller;

import javax.ejb.Stateless;

/**
 *
 * @author migueldiab
 */
@Stateless
public class CuentasController {
  public static final String PATH = "/cuentas/";
  public String crearCuenta() {
    System.out.println("Creada!");
    return PATH + "crear.xhtml";
  }
}
