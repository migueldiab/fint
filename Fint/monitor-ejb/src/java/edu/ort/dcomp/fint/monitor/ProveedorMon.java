package edu.ort.dcomp.fint.monitor;

import java.util.Date;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

/**
 *
 * @author migueldiab
 */
@Singleton
@LocalBean
public class ProveedorMon {
  @Resource
  TimerService timerService;

  private Date lastProgrammaticTimeout;
  private Date lastAutomaticTimeout;

  private Logger logger = Logger.getLogger(
          "com.sun.tutorial.javaee.ejb.timersession.TimerSessionBean");

  public void setTimer(long intervalDuration) {
      logger.info("Setting a programmatic timeout for "
              + intervalDuration + " milliseconds from now.");
      Timer timer = timerService.createTimer(intervalDuration,
              "Created new programmatic timer");
  }

  @Timeout
  public void programmaticTimeout(Timer timer) {
      this.setLastProgrammaticTimeout(new Date());
      logger.info("Programmatic timeout occurred.");
  }

  @Schedule(minute="*/3", hour="*")
  public void automaticTimeout() {
      this.setLastAutomaticTimeout(new Date());
      logger.info("Automatic timeout occured");
  }

  public String getLastProgrammaticTimeout() {
      if (lastProgrammaticTimeout != null) {
          return lastProgrammaticTimeout.toString();
      } else {
          return "never";
      }

  }

  public void setLastProgrammaticTimeout(Date lastTimeout) {
      this.lastProgrammaticTimeout = lastTimeout;
  }

  public String getLastAutomaticTimeout() {
      if (lastAutomaticTimeout != null) {
          return lastAutomaticTimeout.toString();
      } else {
          return "never";
      }
  }

  public void setLastAutomaticTimeout(Date lastAutomaticTimeout) {
      this.lastAutomaticTimeout = lastAutomaticTimeout;
  }

 
}
