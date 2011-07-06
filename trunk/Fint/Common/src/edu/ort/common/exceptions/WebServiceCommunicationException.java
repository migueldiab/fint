package edu.ort.common.exceptions;

import javax.xml.ws.WebServiceException;

/**
 *
 * @author migueldiab
 */
public class WebServiceCommunicationException extends WebServiceException {

  public WebServiceCommunicationException(final String msg) {
    super(msg);
  }

  public WebServiceCommunicationException(String msg, Throwable throwable) {
    super(msg, throwable);
  }

}
