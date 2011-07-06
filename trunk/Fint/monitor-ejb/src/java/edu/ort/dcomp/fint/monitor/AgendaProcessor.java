package edu.ort.dcomp.fint.monitor;

import edu.ort.dcomp.fint.modelo.Agenda;
import edu.ort.dcomp.fint.modelo.managers.AgendaManagerLocal;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

/**
 *
 * @author migueldiab
 */
@Stateless
@LocalBean
public class AgendaProcessor {
  @Resource(name = "jms/colaAgenda")
  private Queue colaAgenda;
  @Resource(name = "jms/colaAgendaFactory")
  private ConnectionFactory colaAgendaFactory;

  @EJB
  AgendaManagerLocal ejbAgenda;

  @Asynchronous
  public void procesarPagosPendientes() {
    System.out.println("Pagos Pendientes");
    List<Agenda> agendas = ejbAgenda.findAll();
    for (Agenda agenda : agendas) {
      try {
        sendJMSMessageToColaAgenda(agenda);
      } catch (JMSException ex) {
        Logger.getLogger(AgendaProcessor.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  private Message createJMSMessageForjmsColaAgenda(Session session, Serializable messageData) throws JMSException {
    ObjectMessage om = session.createObjectMessage();
    om.setObject(messageData);
    return om;
  }

  private void sendJMSMessageToColaAgenda(Serializable messageData) throws JMSException {
    Connection connection = null;
    Session session = null;
    try {
      connection = colaAgendaFactory.createConnection();
      session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      MessageProducer messageProducer = session.createProducer(colaAgenda);
      messageProducer.send(createJMSMessageForjmsColaAgenda(session, messageData));
    } finally {
      if (session != null) {
        try {
          session.close();
        } catch (JMSException e) {
          Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
        }
      }
      if (connection != null) {
        connection.close();
      }
      
    }
  }

}
