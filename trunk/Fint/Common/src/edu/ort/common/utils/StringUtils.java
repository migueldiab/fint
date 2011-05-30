package edu.ort.common.utils;

/**
 *
 * @author migueldiab
 */
public final class StringUtils {

  public static final char BREAK = '\n';
  public static final String CR_LF = "\r\n";
  /**
   * Static class.
   */
  private StringUtils() {
  }

  /**
   * Removes the last character of a StringBuilder.
   * @param strIn A String Builder
   */
  public static void removeLastCharacter(final StringBuilder strIn) {
    strIn.deleteCharAt(strIn.length() - 1);
  }

  /**
   * Removes the last character of a string into a new string.
   * @param strIn An input String
   * @return The output String
   */
  public static String removeLastCharacter(final String strIn) {
    String retStr = strIn;

    retStr = strIn.substring(0, strIn.length() - 1);

    return retStr;
  }

  /**
   * Replacte the last character of a string with the new string.
   * @param strIn An input String
   * @param string The string to replace with
   */
  public static void replaceLastCharacter(final StringBuilder strIn, final String string) {
    strIn.replace((strIn.length() - 1), strIn.length(), string);
  }
}
