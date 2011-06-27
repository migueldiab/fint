package edu.ort.dcomp.fint.monitor;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.ScheduleExpression;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

/**
 *
 * @author migueldiab
 */
@Singleton
@LocalBean
@Startup
public class ProveedorMon {
  @Resource
  TimerService timerService;

  @EJB
  UTEParser uteParser;

  private Boolean run = Boolean.FALSE;
  
  private Logger logger = Logger.getLogger(
          "com.sun.tutorial.javaee.ejb.timersession.TimerSessionBean");

  @PostConstruct
  public void startUpTimers() {
    System.out.println("Construyendo Timers");
     TimerConfig timerConfig = new TimerConfig();
     timerConfig.setPersistent(true);
     timerConfig.setInfo("parserProveedorAgendado");
     ScheduleExpression sched = new ScheduleExpression();
     sched.second("*/30");
     sched.minute("*");
     sched.hour("*");
     Timer timer = timerService.createCalendarTimer(sched, timerConfig);
  }

  @Timeout
  public void programmaticTimeout(Timer timer) throws MalformedURLException {
    uteParser.leerFacturasPendientes();
    uteParser.leerFacturasPasadas();
  }

//  @Schedule(second="*/5", minute="*", hour="*")
//  public void automaticTimeout() {
//    System.out.println("");
//  }


 
}
