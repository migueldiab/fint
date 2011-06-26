package edu.ort.dcomp.fint.converter;

import edu.ort.dcomp.fint.modelo.EntidadFinanciera;
import edu.ort.dcomp.fint.modelo.managers.EntidadFinancieraManagerLocal;
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
@FacesConverter(value="entidadFinancieraConverter")
public class EntidadFinancieraConverter implements Converter {

  @Override
  public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
    System.out.println("Sistema de Control" + string);
    EntidadFinanciera unaEntidadFinanciera = null;
    if (null != string && string.length() > 0) {
      Integer idEF = Integer.parseInt(string);
      EntidadFinancieraManagerLocal controller = null;
      try {
        InitialContext ic = new InitialContext();
        controller = (EntidadFinancieraManagerLocal) ic.lookup("java:global/Fint/Fint-ejb/EntidadFinancieraManager");
      } catch (NamingException ex) {
        Logger.getLogger(EntidadFinancieraConverter.class.getName()).log(Level.SEVERE, null, ex);
      }      
      unaEntidadFinanciera = controller.find(idEF);
    }
    return unaEntidadFinanciera;
  }

  @Override
  public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
    String anEntity = null;
    if (object != null) {
      if (object instanceof EntidadFinanciera) {
        EntidadFinanciera o = (EntidadFinanciera) object;
        anEntity = o.getId().toString();
      } else {
        throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: EntidadFinanciera");
      }    }
    return anEntity;
  }

}
