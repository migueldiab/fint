package edu.ort.dcomp.fint.monitor;

import edu.ort.common.log.Logger;
import edu.ort.dcomp.fint.modelo.Agenda;
import edu.ort.dcomp.fint.modelo.Servicio;
import edu.ort.dcomp.fint.modelo.managers.AgendaManagerLocal;
import edu.ort.dcomp.fint.modelo.managers.ServicioManagerLocal;
import java.io.Serializable;
import java.util.List;
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

  @EJB
  ServicioManagerLocal ejbServicio;

  @EJB
  Logger logger;

  @Asynchronous
  public void procesarPagosPendientes() {
    logger.info("Pagos Pendientes");
    List<Agenda> agendas = ejbAgenda.buscarAgendasDelDia();
    for (Agenda agenda : agendas) {
      try {
        sendJMSMessageToColaAgenda(agenda);
      } catch (JMSException ex) {
        logger.error("Error procesando Pagos Pendientes", ex.toString());
      }
    }
  }

  @Asynchronous
  public void procesarFacturasNuevas() {
    logger.info("Facturas Nuevas");
    List<Servicio> servicios = ejbServicio.buscarServiciosConectados();
    for (Servicio servicio : servicios) {
      try {
        sendJMSMessageToColaAgenda(servicio);
      } catch (JMSException ex) {
        logger.error("Error procesando Pagos Pendientes", ex.toString());
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
          logger.error("Error enviando mensaje a la cola", e.toString());
        }
      }
      if (connection != null) {
        connection.close();
      }
      
    }
  }

}
