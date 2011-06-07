package edu.ort.common.utils;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.persistence.Temporal;
import org.apache.log4j.Logger;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author migueldiab
 */
@Embeddable
public class Money implements Serializable {

  protected static final Logger LOGGER = Logger.getLogger(Money.class);
  private BigDecimal            amount;
  private Currency              currency;
  @Temporal(value = javax.persistence.TemporalType.TIMESTAMP)
  private Date                  moneyUpdatedAt;
  private String                security;

  /**
   * Creates a new Money with US$ 0.00
   */
  public Money() throws NoSuchAlgorithmException {
    amount   = new BigDecimal("0.00");
    currency = Currency.getInstance("USD");
    moneyUpdatedAt = new Date();
    security = EncryptUtils.encodeMD5(amount.toString()+currency.toString()+moneyUpdatedAt.toString());
  }

  public Money(BigDecimal newAmount, Currency newCurrency) throws NoSuchAlgorithmException {
    amount   = newAmount;
    currency = newCurrency;
    moneyUpdatedAt = new Date();
    security = EncryptUtils.encodeMD5(amount.toString()+currency.toString()+moneyUpdatedAt.toString());
  }

  public Money(String newAmount, Currency newCurrency) throws NoSuchAlgorithmException {
    amount   = new BigDecimal(newAmount);
    currency = newCurrency;
    moneyUpdatedAt = new Date();
    security = EncryptUtils.encodeMD5(amount.toString()+currency.toString()+moneyUpdatedAt.toString());
  }

  /**
   * @return the amount
   */
  public BigDecimal getAmount() {
    return amount;
  }

  /**
   * Returns an amount of money converted to the specified currency.
   * @param aCurrency The currency money should be converted into.
   * @return the amount
   */
  public final BigDecimal getAmount(final Currency aCurrency) throws NoSuchAlgorithmException {
    final Money destRate = ExchangeRates.getParity(aCurrency);
    final Money srcRate  = ExchangeRates.getParity(this.getCurrency());
    BigDecimal  convertedAmount;

    if (!srcRate.equals(destRate)) {
      convertedAmount = amount.multiply(srcRate.getAmount()).divide(destRate.getAmount(), BigDecimal.ROUND_UP);
    } else {
      convertedAmount = amount;
    }

    LOGGER.debug("Converted " + this + " => " + aCurrency + " - " + destRate);

    return convertedAmount;
  }

  /**
   * @param amount the amount to set
   */
  public void setAmount(BigDecimal newAmount) throws NoSuchAlgorithmException {
    amount = newAmount;
    setMoneyUpdatedAt(new Date());
    setSecurity(EncryptUtils.encodeMD5(amount.toString() + currency.toString() + getMoneyUpdatedAt().toString()));
  }

  /**
   * @param amount the amount to set
   */
  public void setAmount(String newAmount) throws NoSuchAlgorithmException {
    amount = new BigDecimal(newAmount);
    setMoneyUpdatedAt(new Date());
    setSecurity(EncryptUtils.encodeMD5(amount.toString() + currency.toString() + getMoneyUpdatedAt().toString()));
  }

  /**
   * @return the currency
   */
  @XmlJavaTypeAdapter(CurrencyAdapter.class)  
  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency aCurrency) {
    currency = aCurrency;
  }

  @Override
  public final String toString() {
    return this.getCurrency() + " " + this.getAmount();
  }

  /**
   * @return the security
   */
  public String getSecurity() {
    return security;
  }

  /**
   * @param security the security to set
   */
  public void setSecurity(String security) {
    this.security = security;
  }

  /**
   * @return the updatedDate
   */
  public Date getMoneyUpdatedAt() {
    return (Date) moneyUpdatedAt.clone();
  }

  /**
   * @param updatedDate the updatedDate to set
   */
  public void setMoneyUpdatedAt(Date updatedDate) {
    moneyUpdatedAt = new Date(updatedDate.getTime());
  }

  /**
   * @return the currency
   */
  public String getCurrencyCode() {
    return currency.getCurrencyCode();
  }
  /**
   * @param currency the currency to set
   */
  public void setCurrencyCode(String code) {
    this.currency = Currency.getInstance(code);
  }

  /**
   * @return the currency
   */
  public String getSymbol() {
    return currency.getSymbol(Locale.FRANCE);
  }
  /**
   * @param currency the currency to set
   */
  public void setSymbol(String symbol) {
    
  }

}
