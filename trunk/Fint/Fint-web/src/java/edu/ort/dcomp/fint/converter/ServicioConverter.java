package edu.ort.dcomp.fint.converter;

import edu.ort.dcomp.fint.modelo.Servicio;
import edu.ort.dcomp.fint.modelo.managers.ServicioManagerLocal;
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
@FacesConverter(value="servicioConverter")
public class ServicioConverter implements Converter {

  @Override
  public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
    Servicio unServicio = null;
    if (null != string && string.length() > 0) {
      Integer idServicio = Integer.parseInt(string);
      ServicioManagerLocal controller = null;
      try {
        InitialContext ic = new InitialContext();
        controller = (ServicioManagerLocal) ic.lookup("java:global/Fint/DataAccess-ejb/ServicioManagerLocal");
      } catch (NamingException ex) {
        Logger.getLogger(ServicioConverter.class.getName()).log(Level.SEVERE, null, ex);
      }      
      unServicio = controller.find(idServicio);
    }
    return unServicio;
  }

  @Override
  public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
    String anEntity = null;
    if (object != null) {
      if (object instanceof Servicio) {
        Servicio o = (Servicio) object;
        anEntity = o.getId().toString();
      } else {
        throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: Servicio");
      }    }
    return anEntity;
  }

}
