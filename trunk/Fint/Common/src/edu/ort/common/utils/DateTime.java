package edu.ort.common.utils;

//~--- non-JDK imports --------------------------------------------------------
import java.text.ParseException;
import org.apache.log4j.Logger;

//~--- JDK imports ------------------------------------------------------------
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author migueldiab
 */
public final class DateTime {
  /**
   * A standard Date Format.
   */
  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
  private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm", Locale.getDefault());
  private static final SimpleDateFormat GMT_TIME_FORMAT = new SimpleDateFormat("HH:mm", Locale.getDefault());

  /**
   * An average month (30 days) in miliseconds.
   */
  public static final int ONE_AVG_MONTH = 30 * 24 * 60 * 60 * 1000;

  /**
   * An average year (365 days) in miliseconds.
   */
  public static final int ONE_AVG_YEAR = 365 * 24 * 60 * 60 * 1000;

  /**
   * A day in miliseconds.
   */
  public static final int ONE_DAY = 24 * 60 * 60 * 1000;

  /**
   * An hour in miliseconds.
   */
  public static final int ONE_HOUR = 60 * 60 * 1000;

  /**
   * A minute in miliseconds.
   */
  public static final int ONE_MINUTE = 60 * 1000;

  /**
   * A second in miliseconds.
   */
  public static final int ONE_SECOND = 1000;

  /**
   * A week in miliseconds.
   */
  public static final int ONE_WEEK = 7 * 24 * 60 * 60 * 1000;

  /**
   * LOGGER.
   */
  private static final Logger LOGGER = Logger.getLogger(DateTime.class);

  static {
    GMT_TIME_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
  }

  public static Object addToNow(int pieceOfTime, int increment) {
    Date result;
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    cal.add(pieceOfTime, increment);
    result = cal.getTime();
    return result;
  }

  /**
   * Static class, should not be instantiated.
   */
  private DateTime() {}

  /**
   * Formats the current time/date to the specified format.
   *
   * @param dateFormat A format pattern
   * @return The formatted date
   */
  public static String formatNow(final String dateFormat) {
    return formatDate(Calendar.getInstance().getTime(), dateFormat);
  }

  /**
   * Formats a given time/date to the specified format.
   *
   * @param aDate Any date
   * @param dateFormat A format pattern
   * @return The formatted date
   */
  public static String formatDate(final Date aDate, final String dateFormat) {
    final SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.getDefault());

    return formatter.format(aDate);
  }

  /**
   * Returns current date/time in the format yyyy/MM/dd HH:mm:ss.
   *
   * @return Formated current date/time
   */
  public static String getNow() {
    final String dateFormat = "yyyy/MM/dd HH:mm:ss";

    return formatNow(dateFormat);
  }

  /**
   * Returns a date object with teh current date/time instance set in the calendar.
   *
   * @return A date object with current date/time.
   */
  public static Date getDate() {
    return Calendar.getInstance().getTime();
  }

  /**
   * Returns true if the given date is less than n seconds away from the current time.
   *
   * @param aDate The date to evaluate against
   * @param seconds The number of seconds to evaluate against
   * @return True if the date is less than n seconds away
   */
  public static boolean isNowLessThan(final Date aDate, final int seconds) {
    final int  miliseconds = seconds * ONE_SECOND;
    final long now         = getDate().getTime();
    boolean    isLess      = Boolean.FALSE;

    if ((aDate.getTime() - now) < miliseconds) {
      isLess = Boolean.TRUE;
    }

    return isLess;
  }

  /**
   * Returns true if the given date is more than n seconds away from the current time.
   *
   * @param aDate The date to evaluate against
   * @param seconds The number of seconds to evaluate against
   * @return True if the date is less than n seconds away
   */
  public static boolean isNowMoreThan(final Date aDate, final int seconds) {
    final int  miliseconds = seconds * ONE_SECOND;
    final long now         = getDate().getTime();
    boolean    isMore      = Boolean.FALSE;
    if ((aDate.getTime() - now) > miliseconds) {
      isMore = Boolean.TRUE;
    }
    return isMore;
  }


  /**
   * Given a string date, uses teh default DATE_FORMAT to parse it into
   * a date object.
   *
   * @param date A DATE_FORMAT formatted string
   * @return A date corresponding the date string given
   */
  public static Date parse(final String date) throws ParseException {
    synchronized (DateTime.class) {
      return DateTime.parse(date, DATE_FORMAT);
    }
  }


  public static Date parseTime(final String time) throws ParseException {
    synchronized (DateTime.class) {
      return DateTime.parse(time, TIME_FORMAT);
    }
  }

  public static Date parseGMTTime(final String time) throws ParseException {
    synchronized (DateTime.class) {
      return DateTime.parse(time, GMT_TIME_FORMAT);
    }
  }

  /**
   * Given a string date and a date format, returns a date object with the
   * formatted date.
   *
   * @param aDate A aFormat formatted string date
   * @param aFormat Any valid format
   * @return a Date object with the aDate date.
   */
  public static Date parse(final String aDate, final SimpleDateFormat aFormat) throws ParseException {
    Date returnDate = null;

    try {
      returnDate = aFormat.parse(aDate);
    } catch (ParseException e) {
      final String message = "Could not format the date with the given formatter";
      LOGGER.warn(message, e);
      throw new ParseException(message, e.getErrorOffset());
    }

    return returnDate;
  }

  /**
   * Gets the number of milliseconds since epoch for current date/time.
   *
   * @return number of miliseconds from epoch till now.
   */
  public static long getTime() {
    return getDate().getTime();
  }

  public static Date addMinutes(Date aDate, int minutes) {
    Date result;
    Calendar cal = Calendar.getInstance();
    cal.setTime(aDate);
    cal.add(Calendar.MINUTE, minutes);
    result = cal.getTime();
    return result;
  }

  /**
   * Given a start and end Date and a TimeUnit (Seconds, Minutes, etc), returns the
   * difference between start and date in the specified unit.
   *
   * @param dateOne Start Date
   * @param dateTwo End Date
   * @param timeUnit Tipe of return
   * @return Number of Time Units between two dates.
   */
  public static long getDiff(final Date dateOne, final Date dateTwo, final TimeUnit timeUnit) {
    final long miliseconds = dateOne.getTime() - dateTwo.getTime();
    return timeUnit.convert(miliseconds, TimeUnit.MILLISECONDS);
  }

  /**
   * Given a Two Dates, adds the Time part (Hour and Minute only) of the second
   * to the first one.
   *
   * @param aDate Any Date
   * @param aTime Any Date where only Hours and Minutes are considered.
   * @return New Date resulting of the addition.
   */
  public static Date addTimeFromDate(Date aDate, Date aTime) {    
    Calendar datePart = Calendar.getInstance();
    datePart.setTime(aDate);
    Calendar timePart = Calendar.getInstance();
    timePart.setTime(aTime);
    datePart.add(Calendar.MINUTE, timePart.get(Calendar.MINUTE));
    datePart.add(Calendar.HOUR_OF_DAY, timePart.get(Calendar.HOUR_OF_DAY));
    return datePart.getTime();
  }

/**
     * Needed to create XMLGregorianCalendar instances
     */
    private static DatatypeFactory df = null;
    static {
        try {
            df = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException dce) {
            throw new IllegalStateException(
                "Exception while obtaining DatatypeFactory instance", dce);
        }
    }

    /**
     * Converts a java.util.Date into an instance of XMLGregorianCalendar
     *
     * @param date Instance of java.util.Date or a null reference
     * @return XMLGregorianCalendar instance whose value is based upon the
     *  value in the date parameter. If the date parameter is null then
     *  this method will simply return null.
     */
    public static XMLGregorianCalendar asXMLGregorianCalendar(java.util.Date date) {
        if (date == null) {
            return null;
        } else {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTimeInMillis(date.getTime());
            return df.newXMLGregorianCalendar(gc);
        }
    }

    /**
     * Converts an XMLGregorianCalendar to an instance of java.util.Date
     *
     * @param xgc Instance of XMLGregorianCalendar or a null reference
     * @return java.util.Date instance whose value is based upon the
     *  value in the xgc parameter. If the xgc parameter is null then
     *  this method will simply return null.
     */
    public static java.util.Date asDate(XMLGregorianCalendar xgc) {
        if (xgc == null) {
            return null;
        } else {
            return xgc.toGregorianCalendar().getTime();
        }
    }

}

