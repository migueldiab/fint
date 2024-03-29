package edu.ort.common.log;

import java.util.List;
import javax.ejb.LocalBean;

/**
 *
 * @author migueldiab
 */
@LocalBean
public interface LogEntryFacadeLocal {

  void create(LogEntry logEntry);

  void edit(LogEntry logEntry);

  void remove(LogEntry logEntry);

  LogEntry find(Object id);

  List<LogEntry> findAll();

  List<LogEntry> findRange(int[] range);

  int count();

}
