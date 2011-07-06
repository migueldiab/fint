package edu.ort.dcomp.fint.monitor;

import edu.ort.common.log.Logger;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
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
  private TimerService timerService;

  List<Timer> timers = new ArrayList<Timer>();

  @EJB
  AgendaProcessor agendaProcessor;

  @EJB
  private Logger logger;

  private Boolean run = Boolean.FALSE;
  private String PAGOS = "procesarPagos";
  private String FACTURAS = "obtenerFacturasPendientes";

  @PostConstruct
  public void startUpTimers() {
    System.out.println("Construyendo Timers");
    TimerConfig facturaTimerConfig = new TimerConfig();
    facturaTimerConfig.setPersistent(false);
    facturaTimerConfig.setInfo(FACTURAS);
    ScheduleExpression facturasSched = new ScheduleExpression();
    facturasSched.second("0");
    facturasSched.minute("*/1");
    facturasSched.hour("*");
    Timer facturaTimer = timerService.createCalendarTimer(facturasSched, facturaTimerConfig);
    timers.add(facturaTimer);
    TimerConfig pagosTimerConfig = new TimerConfig();
    pagosTimerConfig.setPersistent(false);
    pagosTimerConfig.setInfo(PAGOS);
    ScheduleExpression pagosSched = new ScheduleExpression();
    pagosSched.second("30");
    pagosSched.minute("*/1");
    pagosSched.hour("*");
    Timer pagosTimer = timerService.createCalendarTimer(pagosSched, pagosTimerConfig);
    timers.add(pagosTimer);
  }

  @Timeout
  public void programmaticTimeout(Timer timer) throws MalformedURLException {
    if (PAGOS.equals(timer.getInfo())) {
      logger.info("Procesando Pagos");
      agendaProcessor.procesarPagosPendientes();
    } else if (FACTURAS.equals(timer.getInfo())) {
      logger.info("Procesando Facturas");
      agendaProcessor.procesarFacturasNuevas();
    }
  }

//  @Schedule(hour="*", minute="*", second="*/5")
//  public void automaticTimeout() {
//    System.out.println("");
//  }


 
}
