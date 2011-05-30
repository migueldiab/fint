package edu.ort.common.utils;

import org.apache.log4j.Logger;

import java.math.BigDecimal;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author migueldiab
 */
public final class ExchangeRates {
  /**
   * A map of Currencies with a value of Money assigned, being that the exchange rate defined to a
   * default currency.
   */
  private static Map<Currency, Money> parities = new HashMap<Currency, Money>();

  /**
   * Logger.
   */
  private static final Logger LOGGER = Logger.getLogger(ExchangeRates.class);

  /**
   * Static class.
   */
  private ExchangeRates() {}

  /**
   * Adds a parity to the map, or updates the existing one.
   *
   * @param aCurrency Any currency
   * @param aRate The exchange rate against the default currency.
   */
  public static void addParity(final Currency aCurrency, final Money aRate) {
    parities.put(aCurrency, aRate);
  }

  /**
   * Returns the parity that matches the currency given.
   *
   * @param aCurrency Any currency
   * @return The Money match
   */
  public static Money getParity(final Currency aCurrency) {
    Money retMoney = parities.get(aCurrency);

    if (retMoney == null) {
      LOGGER.warn("Currency not found, using default value => " + aCurrency);
      retMoney = new Money("1.00", aCurrency);
    }

    return retMoney;
  }

  /**
   * Adds a parity from a currency and a BigDecimal value (as exchange rate).
   *
   * @param aCurrency Any currency
   * @param parityValue The exchange rate against the default currency
   */
  public static void addParity(final Currency aCurrency, final BigDecimal parityValue) {
    final Money aRate = new Money(parityValue, aCurrency);

    parities.put(aCurrency, aRate);
  }
}

