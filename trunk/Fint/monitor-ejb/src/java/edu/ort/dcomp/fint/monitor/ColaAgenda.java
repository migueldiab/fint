package edu.ort.dcomp.fint.monitor;

import edu.ort.dcomp.fint.modelo.Agenda;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
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
    
  public ColaAgenda() {
  }

  @Override
  public void onMessage(Message message) {
    System.out.println("onMessage");
    if (message instanceof ObjectMessage) {
      ObjectMessage om = (ObjectMessage) message;
      Agenda agenda = null;
      try {
        agenda = (Agenda) om.getObject();
      } catch (JMSException ex) {
        Logger.getLogger(ColaAgenda.class.getName()).log(Level.SEVERE, null, ex);
      }
      System.out.println("Recibido una agenda : " + agenda.getCuenta().getNombre());
    }
  }
    
}
