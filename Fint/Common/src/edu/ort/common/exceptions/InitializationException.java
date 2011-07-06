package edu.ort.common.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author migueldiab
 */
public class InitializationException extends Exception {

  private List<Exception> errorList = new ArrayList<Exception>();

  public InitializationException() {
    super();
  }

  public void addError(final Exception error)   {
    errorList.add(error);
  }

  public List<Exception> getErrorList() {
    return errorList;
  }

}
