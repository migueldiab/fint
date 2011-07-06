package edu.ort.dcomp.fint.monitor;

import edu.ort.common.log.Logger;
import edu.ort.dcomp.fint.modelo.Agenda;
import edu.ort.dcomp.fint.modelo.EntidadFinanciera;
import edu.ort.dcomp.fint.modelo.Proveedor;
import edu.ort.dcomp.fint.modelo.Servicio;
import edu.ort.dcomp.fint.modelo.Transaccion;
import edu.ort.dcomp.fint.modelo.managers.ServicioManagerLocal;
import edu.ort.dcomp.fint.modelo.managers.TransaccionManagerLocal;
import edu.ort.dcomp.fint.ws.entidades.Movimiento;
import edu.ort.dcomp.fint.ws.servicio.Recibo;
import java.util.Date;
import java.util.List;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

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

  @EJB
  private ServicioManagerLocal ejbServicio;

  @EJB
  private TransaccionManagerLocal ejbTransaccion;

  public ColaAgenda() {
  }

  @Override
  public void onMessage(Message message) {
    logger.info("Procesando cola de mensaje de agenda");
    if (message instanceof ObjectMessage) {
      ObjectMessage om = (ObjectMessage) message;
      if (om instanceof Agenda) {
        try {
          final Agenda agenda = (Agenda) om.getObject();
          procesarAgenda(agenda);
        } catch (NamingException ex) {
          logger.error("Error al procesar la agenda.", ex.toString());
        } catch (JMSException ex) {
          logger.error("Error al leer la agenda.", ex.toString());
        }
      } else if (om instanceof Servicio) {
        try {
          final Servicio servicio = (Servicio) om.getObject();
          procesarServicio(servicio);
        } catch (NamingException ex) {
          logger.error("Error al procesar el servicio.", ex.toString());
        } catch (JMSException ex) {
          logger.error("Error al leer el servicio.", ex.toString());
        }
      }
      
    }
  }

  private GenericProveedorParser getParserProveedor(Proveedor proveedor) throws NamingException {
    GenericProveedorParser result = null;
    String name = null;
    switch (proveedor.getParser()) {
      case UTEParser:
        name = "java:global/Fint/monitor-ejb/UTEParser";
        break;
      default:
        logger.error("No existe un parser para el proveedor " + proveedor, "");
        throw new NamingException("No existe un parser para el proveedor " + proveedor);
    }
    try
    {
       final Context ctx = new InitialContext();
       result = (GenericProveedorParser) ctx.lookup(name);
    }
    catch (NamingException e)
    {
      logger.error("Could not locate parser", e.toString());
      throw e;
    }
    return result;
  }

  private GenericEntidadParser getParserEntidad(EntidadFinanciera entidadFinanciera) throws NamingException {
    String jndi;
    GenericEntidadParser gep;
    switch (entidadFinanciera.getParser()) {
      case wsBROU:
        jndi = "java:global/Fint/monitor-ejb/BROUParser";
        break;
      case wsITAU:
        jndi = "java:global/Fint/monitor-ejb/ITAUParser";
        break;
      case wsNBC:
        jndi = "java:global/Fint/monitor-ejb/NBCParser";
        break;
      default:
        throw new AssertionError();
    }
    try
    {
       final Context ctx = new InitialContext();
       gep = (GenericEntidadParser) ctx.lookup(jndi);
    }
    catch (NamingException e)
    {
      logger.error("Could not locate parser", e.toString());
      throw e;
    }
    return gep;
  }

  private String obtenerClave(Movimiento retiro) {
    return retiro.getId().toString() + retiro.getCuenta();
  }

  private void procesarAgenda(Agenda agenda) throws NamingException {
    logger.info("Recibido agenda : " + agenda.toString());
    List<Transaccion> listaPendientes = ejbTransaccion.buscarFacturasPendientes(agenda);
    if (!listaPendientes.isEmpty()) {
      GenericProveedorParser parserPr = getParserProveedor(agenda.getServicio().getProveedor());
      for (Transaccion transaccion : listaPendientes) {
        GenericEntidadParser parserEF = getParserEntidad(agenda.getCuenta().getEntidadFinanciera());
        Movimiento retiro = parserEF.retirarDinero(transaccion.getCuenta(), transaccion.getUsuario(), transaccion.getImporte());
        Recibo recibo = parserPr.pagarCuenta(transaccion, obtenerClave(retiro));
        transaccion.setEstado(Transaccion.Estado.PAGA);
        transaccion.setFechaPago(new Date());
        transaccion.setDestinatario(recibo.getClaveEntidad());
        ejbTransaccion.merge(transaccion);
      }
    }
  }

  private void procesarServicio(Servicio servicio) throws NamingException {
    logger.info("Recibido agenda : " + servicio.toString());
    GenericProveedorParser parserPr = getParserProveedor(servicio.getProveedor());
    parserPr.leerFacturasPendientes(servicio);
  }
    
}
