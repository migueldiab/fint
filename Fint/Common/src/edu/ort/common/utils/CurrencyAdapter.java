package edu.ort.common.utils;

import java.util.Currency;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author migueldiab
 */
public class CurrencyAdapter extends XmlAdapter<String, Currency>{

	@Override
	public String marshal(Currency v) throws Exception {
		return v.toString();
	}

	@Override
	public Currency unmarshal(String v) throws Exception {
		return Currency.getInstance(v);
	}

}