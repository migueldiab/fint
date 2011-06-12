package edu.ort.dcomp.fint.converter;

import edu.ort.dcomp.fint.controller.UsuarioController;
import edu.ort.dcomp.fint.modelo.Usuario;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author migueldiab
 */

@FacesConverter(forClass=Usuario.class)
public class UsuarioControllerConverter implements Converter {

  @Override
  public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
    Usuario unUsuario = null;
    if (value != null && value.length() > 0) {
      UsuarioController controller = (UsuarioController)facesContext.getApplication().getELResolver().
          getValue(facesContext.getELContext(), null, "usuarioController");
      unUsuario = controller.find(getKey(value));
    }
    return unUsuario;
    
  }

  java.lang.Long getKey(String value) {
    java.lang.Long key;
    key = Long.valueOf(value);
    return key;
  }

  String getStringKey(java.lang.Long value) {
    StringBuilder sb = new StringBuilder();
    sb.append(value);
    return sb.toString();
  }

  @Override
  public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
    if (object == null) {
      return null;
    }
    if (object instanceof Usuario) {
      Usuario o = (Usuario) object;
      return getStringKey(o.getId());
    } else {
      throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: "+UsuarioController.class.getName());
    }
  }

}
