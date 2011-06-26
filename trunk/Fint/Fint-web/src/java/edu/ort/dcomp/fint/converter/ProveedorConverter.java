package edu.ort.dcomp.fint.converter;

import edu.ort.dcomp.fint.modelo.Proveedor;
import edu.ort.dcomp.fint.modelo.managers.ProveedorManagerLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author migueldiab
 */
@FacesConverter(value="proveedorConverter")
public class ProveedorConverter implements Converter {

  @Override
  public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
    Proveedor unProveedor = null;
    if (null != string && string.length() > 0) {
      Integer idProveedor = Integer.parseInt(string);
      ProveedorManagerLocal controller = null;
      try {
        InitialContext ic = new InitialContext();
        controller = (ProveedorManagerLocal) ic.lookup("java:global/Fint/Fint-ejb/ProveedorManagerLocal");
      } catch (NamingException ex) {
        Logger.getLogger(ProveedorConverter.class.getName()).log(Level.SEVERE, null, ex);
      }      
      unProveedor = controller.find(idProveedor);
    }
    return unProveedor;
  }

  @Override
  public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
    String anEntity = null;
    if (object != null) {
      if (object instanceof Proveedor) {
        Proveedor o = (Proveedor) object;
        anEntity = o.getId().toString();
      } else {
        throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: Proveedor");
      }    }
    return anEntity;
  }

}
