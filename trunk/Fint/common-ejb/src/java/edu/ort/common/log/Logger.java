package edu.ort.common.log;

import javax.ejb.Asynchronous;
import javax.ejb.Local;

/**
 *
 * @author migueldiab
 */
@Local
public interface Logger {

  @Asynchronous
  void crit(String mensaje, String trace);

  @Asynchronous
  void debug(String mensaje);

  @Asynchronous
  void error(String mensaje, String trace);

  @Asynchronous
  void info(String mensaje);

  @Asynchronous
  void warn(String mensaje, String trace);

}
