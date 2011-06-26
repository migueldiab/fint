package edu.ort.dcomp.fint.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author migueldiab
 */
@FacesValidator(value="emailValidator")
public class EmailValidator implements Validator{

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    String enteredEmail = (String) value;
    //Set the email pattern string
    Pattern p = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$");
    //Match the given string with the pattern
    Matcher m = p.matcher(enteredEmail);
    //Check whether match is found
    boolean matchFound = m.matches();
    Boolean error = Boolean.FALSE;
    String msg = null;
    if (!matchFound) {
      error = Boolean.TRUE;
      msg = "La dirección de correo no parece ser válida";
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
