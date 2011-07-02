package edu.ort.dcomp.fint.monitor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
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
  private TimerService timerService;

  private Boolean run = Boolean.FALSE;

  @PostConstruct
  public void startUpTimers() {
     System.out.println("Construyendo Timers");
//     TimerConfig timerConfig = new TimerConfig();
//     timerConfig.setPersistent(false);
//     timerConfig.setInfo("parserProveedorAgendado");
//     ScheduleExpression sched = new ScheduleExpression();
//     sched.second("0");
//     sched.minute("*/1");
//     sched.hour("*");
//     Timer timer = timerService.createCalendarTimer(sched, timerConfig);
  }

//  @Timeout
//  public void programmaticTimeout(Timer timer) throws MalformedURLException {
//    uteParser.leerFacturasPendientes();
//    uteParser.leerFacturasPasadas();
//  }

//  @Schedule(hour="*", minute="*", second="*/5")
//  public void automaticTimeout() {
//    System.out.println("");
//  }


 
}
