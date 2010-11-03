package de.oose.wechselkurs;

/**
 * Service for currency rates. 
 * Look up 3-letter ISO codes for currencies here
 * http://www.iso.org/iso/support/currency_codes_list-1.htm
 * 
 * @author StefanT, StefanZ
 */
public interface CurrencyService {

	/**
	 * Determine exchange rate between to currencies.
	 * 
	 * @param currencyFrom
	 *            3-letter ISO code of first currency
	 * @param currencyTo
	 *            3-letter ISO code of first currency
	 * @return the rate of the to currencies
	 */
	double getRate(String currencyFrom, String currencyTo);

}