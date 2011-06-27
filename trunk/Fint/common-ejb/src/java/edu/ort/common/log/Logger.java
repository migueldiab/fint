package edu.ort.common.log;

import java.util.Date;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author migueldiab
 */
@Stateless
@LocalBean
public class Logger {

  @EJB
  LogEntryFacadeLocal ejbLog;

  @Asynchronous
  public void info(String mensaje) {
    LogEntry log = new LogEntry();
    log.setMensaje(mensaje);
    log.setSeveridad(LogEntry.Severidad.INFO);
    log.setTimestamp(new Date());
    log.setTrace("");
    ejbLog.create(log);
  }
  @Asynchronous
  public void debug(String mensaje) {
    LogEntry log = new LogEntry();
    log.setMensaje(mensaje);
    log.setSeveridad(LogEntry.Severidad.DEBUG);
    log.setTimestamp(new Date());
    log.setTrace("");
    ejbLog.create(log);
  }
  @Asynchronous
  public void crit(String mensaje, String trace) {
    LogEntry log = new LogEntry();
    log.setMensaje(mensaje);
    log.setSeveridad(LogEntry.Severidad.CRITICAL);
    log.setTimestamp(new Date());
    log.setTrace(trace);
    ejbLog.create(log);
  }
  @Asynchronous
  public void error(String mensaje, String trace) {
    LogEntry log = new LogEntry();
    log.setMensaje(mensaje);
    log.setSeveridad(LogEntry.Severidad.ERROR);
    log.setTimestamp(new Date());
    log.setTrace(trace);
    ejbLog.create(log);
  }
  @Asynchronous
  public void warn(String mensaje, String trace) {
    LogEntry log = new LogEntry();
    log.setMensaje(mensaje);
    log.setSeveridad(LogEntry.Severidad.WARN);
    log.setTimestamp(new Date());
    log.setTrace(trace);
    ejbLog.create(log);
  }
}
