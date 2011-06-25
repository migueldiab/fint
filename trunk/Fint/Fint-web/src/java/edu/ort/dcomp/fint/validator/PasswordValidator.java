package edu.ort.dcomp.fint.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author migueldiab
 */
@FacesValidator(value="passwordValidator")
public class PasswordValidator implements Validator{

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    UIInput pwdInput = (UIInput) component.findComponent("contrasena");    
    String password = (String) pwdInput.getValue();
    String repeat = (String) value;
    Boolean error = Boolean.FALSE;
    String msg = null;
    if (!password.equals(repeat)) {
      error = Boolean.TRUE;
      msg = "Las claves ingresadas no coinciden";
    } else if (password.length() < 6) {
      error = Boolean.TRUE;
      msg = "La clave debe tener al menos 6 caracteres";
    }
    if (error) {
     FacesMessage message = new FacesMessage();
     message.setDetail(msg);
     message.setSummary(msg);
     message.setSeverity(FacesMessage.SEVERITY_ERROR);
     throw new ValidatorException(message);
    }
  }

}
