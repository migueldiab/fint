package edu.ort.dcomp.fint.jsf;

import edu.ort.common.utils.DateTime;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author migueldiab
 */
public class JsfUtil {

  public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
    int size = selectOne ? entities.size() + 1 : entities.size();
    SelectItem[] items = new SelectItem[size];
    int i = 0;
    if (selectOne) {
      items[0] = new SelectItem("", "---");
      i++;
    }
    for (Object x : entities) {
      items[i++] = new SelectItem(x, x.toString());
    }
    return items;
  }

  public static void ensureAddErrorMessage(Exception ex, String defaultMsg) {
    String msg = ex.getLocalizedMessage();
    if (msg != null && msg.length() > 0) {
      addErrorMessage(msg);
    } else {
      addErrorMessage(defaultMsg);
    }
  }

  public static void addErrorMessages(List<String> messages) {
    for (String message : messages) {
      addErrorMessage(message);
    }
  }

  public static void addErrorMessage(String msg) {
    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
    FacesContext.getCurrentInstance().addMessage(null, facesMsg);
  }

  public static void addSuccessMessage(String msg) {
    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
    FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
  }

  public static void addWarningMessage(String msg) {
    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg);
    FacesContext.getCurrentInstance().addMessage(null, facesMsg);
  }

  public static void addFatalMessage(String msg) {
    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg);
    FacesContext.getCurrentInstance().addMessage(null, facesMsg);
  }

  public static void addErrorMessage(Exception ex, String defaultMsg) {
    String msg = ex.getLocalizedMessage();
    if (msg != null && msg.length() > 0) {
      addErrorMessage(msg);
    } else {
      addErrorMessage(defaultMsg);
    }
  }

  public static String getRequestParameter(String key) {
    return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
  }

  public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
    String theId = JsfUtil.getRequestParameter(requestParameterName);
    return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
  }

  public static <T> List<T> arrayToList(T[] arr) {
    List<T> result;
    if (arr == null) {
      result = new ArrayList<T>();
    } else {
      result = Arrays.asList(arr);
    }
    return result;
  }

  public static <T> Set<T> arrayToSet(T[] arr) {
    Set<T> result;
    if (arr == null) {
      result = new HashSet<T>();
    } else {
      result = new HashSet(Arrays.asList(arr));
    }
    return result;
  }

  public static Object[] collectionToArray(Collection<?> c) {
    Object[] result;
    if (c == null) {
      result = new Object[0];
    } else {
      result = c.toArray();
    }
    return result;
  }

  public static <T> List<T> setToList(Set<T> set) {
    return new ArrayList<T>(set);
  }

  public static String getAsConvertedString(Object object, Converter converter) {
    return converter.getAsString(FacesContext.getCurrentInstance(), null, object);
  }

  public static String getCollectionAsString(Collection<?> collection) {
    String result;
    if (collection == null || collection.isEmpty()) {
      result = "(No Items)";
    } else {
      final StringBuilder sb = new StringBuilder();
      int i = 0;
      for (Object item : collection) {
        if (i > 0) {
          sb.append("<br />");
        }
        sb.append(item);
        i++;
      }
      result = sb.toString();
    }
    return result;
  }

  public static void addFlash(String key, Object value) {
  FacesContext context = FacesContext.getCurrentInstance();
  context.getExternalContext().getFlash().put(key, value);
  }

  public static Integer getIntegerParameter(String string) throws NumberFormatException {
    String idAsString = JsfUtil.getRequestParameter("roomId");
    return Integer.parseInt(idAsString);
  }

  public static Date getDateParameter(String string) throws ParseException {
    final String strDate = JsfUtil.getRequestParameter(string);
    Date returnDate = null;
    returnDate = DateTime.parse(strDate);
    return returnDate;
  }

  public static void addCookie(String key, String value) {
    addCookie(key, value, 3600);
  }

  private static void addCookie(String key, String value, int age) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    Cookie aCookie = new Cookie(key, value);
    aCookie.setMaxAge(age);
    ((HttpServletResponse)facesContext.getExternalContext().getResponse()).addCookie(aCookie);
  }

  public static String getCookie(String key) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    String cookieName = null;
    String result = null;
    Cookie cookie[] = ((HttpServletRequest)facesContext.getExternalContext().getRequest()).getCookies();    
    if(cookie != null && cookie.length > 0) {
      for(int i = 0; i<cookie.length; i++) {
        cookieName = cookie[i].getName();
        if(cookieName.equals(key)) {
          result = cookie[i].getValue();
        }
      }
    }
    return result;
  }

  public static void removeCookie(String key) {
    System.out.println("Borrando cookie " + key);
    FacesContext facesContext = FacesContext.getCurrentInstance();
    Cookie aCookie = new Cookie(key, null);
    aCookie.setMaxAge(0);
    ((HttpServletResponse)facesContext.getExternalContext().getResponse()).addCookie(aCookie);
  }

  public static void redirect(String url) throws IOException {
    System.out.println("Redirigiendo a " + url);
    FacesContext facesContext = FacesContext.getCurrentInstance();
    facesContext.getExternalContext().redirect(url);
  }

  public static void dispatch(String string) throws IOException {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    facesContext.getExternalContext().dispatch(string);
  }
}
