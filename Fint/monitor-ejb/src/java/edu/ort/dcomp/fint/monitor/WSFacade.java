package edu.ort.dcomp.fint.monitor;

import edu.ort.dcomp.fint.monitor.client.ConsultasWS;
import edu.ort.dcomp.fint.monitor.client.ConsultasWSService;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author migueldiab
 */
@Stateless
@LocalBean
public class WSFacade {
  @WebServiceRef(wsdlLocation = "http://localhost:8080/ConsultasWSService/ConsultasWS?wsdl")
  private ConsultasWSService service = getService();
    
  private static String WS_URL;
  private static int WS_TIMEOUT = -1;

  public ConsultasWS getProxy() throws MalformedURLException {
    ConsultasWS consultaWS = service.getConsultasWSPort();
    return consultaWS;
  }

  private URL getWSURL() throws MalformedURLException {
    if (null == WS_URL) {
      WS_URL = "http://localhost:8080/ConsultasWSService/ConsultasWS?wsdl";
    }
    return new URL(WS_URL);
  }

  private ConsultasWSService getService() {
    final QName serviceName = new QName("http://ws.ute.dcomp.ort.edu/", "ConsultasWSService");
    ConsultasWSService service = null;
    try {
      service = new ConsultasWSService(getWSURL(), serviceName);
    } catch (MalformedURLException ex) {
      Logger.getLogger(WSFacade.class.getName()).log(Level.SEVERE, null, ex);
    }
    return service;
  }


}
