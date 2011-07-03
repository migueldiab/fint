package edu.ort.dcomp.fint.monitor;

import edu.ort.common.log.Logger;
import edu.ort.dcomp.fint.modelo.Agenda;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *
 * @author migueldiab
 */
@MessageDriven(mappedName = "jms/colaAgenda", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
public class ColaAgenda implements MessageListener {

  @EJB
  private Logger logger;

  public ColaAgenda() {
  }

  @Override
  public void onMessage(Message message) {
    logger.info("Procesando cola de mensaje de agenda");
    if (message instanceof ObjectMessage) {
      ObjectMessage om = (ObjectMessage) message;
      try {
        final Agenda agenda = (Agenda) om.getObject();
        logger.info("Recibido agenda : " + agenda.toString());
      } catch (JMSException ex) {
        logger.error("Error al leer agenda.", ex.toString());
      }
      
    }
  }
    
}
