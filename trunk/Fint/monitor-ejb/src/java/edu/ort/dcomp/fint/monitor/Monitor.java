package edu.ort.dcomp.fint.monitor;

import edu.ort.common.exceptions.EmptyPropertyException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author migueldiab
 */
public class Monitor {

  private static Monitor instance;

  private Properties monitorProp = new Properties();
  
  private Monitor() {
  }

  public static Monitor getInstance() throws IOException {
    if (null == instance) {
      instance = new Monitor();
      instance.initializeProperties();
    }
    return instance;
  }

  private void initializeProperties() throws IOException {
    InputStream resource = getClass().getClassLoader().getResourceAsStream("monitor.properties");
    monitorProp.load(resource);monitorProp.load(resource);
  }

  public String getProperty(final String key) throws EmptyPropertyException, IOException {
    String property = monitorProp.getProperty(key);
    if (property==null || property.isEmpty()) {
      initializeProperties();
      property = monitorProp.getProperty(key);
      if (property==null || property.isEmpty()) {
        throw new EmptyPropertyException("Null or Empty Property with Key " + key);
      }
    }
    return property;
  }

}
