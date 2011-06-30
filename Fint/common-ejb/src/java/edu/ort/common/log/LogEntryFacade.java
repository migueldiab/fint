package edu.ort.common.log;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author migueldiab
 */
@Stateless
public class LogEntryFacade extends AbstractFacade<LogEntry> implements LogEntryFacadeLocal {
  @PersistenceContext(unitName = "LoggerPU")
  private EntityManager em;

  protected EntityManager getEntityManager() {
    return em;
  }

  public LogEntryFacade() {
    super(LogEntry.class);
  }

}
