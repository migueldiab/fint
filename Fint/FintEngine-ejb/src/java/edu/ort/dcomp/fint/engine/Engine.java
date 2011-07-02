package edu.ort.dcomp.fint.engine;

import edu.ort.common.log.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 *
 * @author migueldiab
 */
@Singleton
@LocalBean
public class Engine {

  @EJB
  private Logger logger;

  public void infoLog(String string) {
    logger.info(string);
  }

  public void errorLog(String string, String ex) {
    logger.error(string, ex);
  }

}
