package edu.ort.dcomp.fint.converter;

import edu.ort.dcomp.fint.modelo.Cuenta;
import edu.ort.dcomp.fint.modelo.managers.CuentaManagerLocal;
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
@FacesConverter(value="cuentaConverter")
public class CuentaConverter implements Converter {

  @Override
  public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
    Cuenta unaCuenta = null;
    if (null != string && string.length() > 0) {
      Long idCuenta = Long.parseLong(string);
      CuentaManagerLocal controller = null;
      try {
        InitialContext ic = new InitialContext();
        controller = (CuentaManagerLocal) ic.lookup("java:global/Fint/Fint-ejb/CuentaManager");
      } catch (NamingException ex) {
        Logger.getLogger(CuentaConverter.class.getName()).log(Level.SEVERE, null, ex);
      }      
      unaCuenta = controller.find(idCuenta);
    }
    return unaCuenta;
  }

  @Override
  public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
    String anEntity = null;
    if (object != null) {
      if (object instanceof Cuenta) {
        Cuenta o = (Cuenta) object;
        anEntity = o.getId().toString();
      } else {
        throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: Servicio");
      }    }
    return anEntity;
  }

}
