package edu.ort.dcomp.fint.engine;

import edu.ort.dcomp.fint.modelo.Proveedor;
import edu.ort.dcomp.fint.modelo.Servicio;
import edu.ort.dcomp.fint.modelo.managers.ProveedorManagerLocal;
import edu.ort.dcomp.fint.monitor.GenericProveedorParser;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author migueldiab
 */
@Stateless
@LocalBean
public class ServicioFacade {

  private GenericProveedorParser genericProveedorParser;

  @EJB
  private Engine engine;

  @EJB
  private ProveedorManagerLocal ejbProveedor;
  
  public List<Servicio> listarCuentasProveedor(String id, String password, Proveedor proveedor) {
    engine.infoLog("List<Servicio> listarCuentasProveedor(id "+id+", password "+password+", Proveedor "+proveedor+")");
    List<Servicio> result = null;
    initParser(proveedor);
    result = genericProveedorParser.listarCuentas(id, password);
    return result;
  }

  private void initParser(Proveedor proveedor) {
    String name = null;
    switch (proveedor.getParser()) {
      case UTEParser:
        name = "java:module/UTEParser!edu.ort.dcomp.fint.monitor.UTEParser";
        break;
      default:
        throw new AssertionError();
    }
    try
    {
       final Context ctx = new InitialContext();
       genericProveedorParser = (GenericProveedorParser) ctx.lookup(name);
    }
    catch ( NamingException e )
    {
      System.out.println("Could not locate parser");
    }    
  }

  public List<Proveedor> getProveedores() {
    engine.infoLog("getProveedores()");
    return ejbProveedor.findAll();
  }

  public Proveedor getProveedorById(Integer idProveedor) {
    return ejbProveedor.find(idProveedor);
  }

  
}
