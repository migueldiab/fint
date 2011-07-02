package edu.ort.dcomp.fint.engine;

import edu.ort.dcomp.fint.modelo.Proveedor;
import edu.ort.dcomp.fint.modelo.Servicio;
import edu.ort.dcomp.fint.modelo.managers.ProveedorManagerLocal;
import edu.ort.dcomp.fint.monitor.GenericProveedorParser;
import java.net.MalformedURLException;
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
  
  public List<Servicio> listarCuentasProveedor(String id, String password, Proveedor proveedor) throws MalformedURLException {
    engine.infoLog("List<Servicio> listarCuentasProveedor(id "+id+", password "+password+", Proveedor "+proveedor+")");
    List<Servicio> result = null;
    if (initParser(proveedor)) {
      result = genericProveedorParser.listarCuentas(id, password);
    }
    return result;
  }

  private Boolean initParser(Proveedor proveedor) {
    Boolean result = Boolean.FALSE;
    String name = null;
    switch (proveedor.getParser()) {
      case UTEParser:
        name = "java:global/Fint/monitor-ejb/UTEParser";
        break;
      default:
        engine.errorLog("No existe un parser para el proveedor " + proveedor, "");
    }
    try
    {
       final Context ctx = new InitialContext();
       genericProveedorParser = (GenericProveedorParser) ctx.lookup(name);
       result = Boolean.TRUE;
    }
    catch (NamingException e)
    {
      engine.errorLog("Could not locate parser", e.toString());
      
    }
    return result;
  }

  public List<Proveedor> getProveedores() {
    engine.infoLog("getProveedores()");
    return ejbProveedor.findAll();
  }

  public Proveedor getProveedorById(Integer idProveedor) {
    return ejbProveedor.find(idProveedor);
  }

  
}
