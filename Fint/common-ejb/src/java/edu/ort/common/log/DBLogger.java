package edu.ort.common.log;

import java.util.Date;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author migueldiab
 */
@Stateless
public class DBLogger implements Logger {

  @EJB
  LogEntryFacadeLocal ejbLog;

  @Asynchronous
  @Override
  public void info(String mensaje) {
    System.out.println(mensaje);
    LogEntry log = new LogEntry();
    log.setMensaje(mensaje);
    log.setSeveridad(LogEntry.Severidad.INFO);
    log.setTimestamp(new Date());
    log.setTrace("");
    ejbLog.create(log);
  }
  @Asynchronous
  @Override
  public void debug(String mensaje) {
    System.out.println(mensaje);
    LogEntry log = new LogEntry();
    log.setMensaje(mensaje);
    log.setSeveridad(LogEntry.Severidad.DEBUG);
    log.setTimestamp(new Date());
    log.setTrace("");
    ejbLog.create(log);
  }
  @Asynchronous
  @Override
  public void crit(String mensaje, String trace) {
    System.out.println(mensaje);
    LogEntry log = new LogEntry();
    log.setMensaje(mensaje);
    log.setSeveridad(LogEntry.Severidad.CRITICAL);
    log.setTimestamp(new Date());
    log.setTrace(trace);
    ejbLog.create(log);
  }
  @Asynchronous
  @Override
  public void error(String mensaje, String trace) {
    System.out.println(mensaje);
    LogEntry log = new LogEntry();
    log.setMensaje(mensaje);
    log.setSeveridad(LogEntry.Severidad.ERROR);
    log.setTimestamp(new Date());
    log.setTrace(trace);
    ejbLog.create(log);
  }
  @Asynchronous
  @Override
  public void warn(String mensaje, String trace) {
    System.out.println(mensaje);
    LogEntry log = new LogEntry();
    log.setMensaje(mensaje);
    log.setSeveridad(LogEntry.Severidad.WARN);
    log.setTimestamp(new Date());
    log.setTrace(trace);
    ejbLog.create(log);
  }
}
