package de.oose.wechselkurs;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;

/**
 * Gets currencies from Yahoo Finance
 * 
 * @author StefanZ, StefanT
 */
public class YahooFinance implements CurrencyService {

	private static final String FINANCE_YAHOO = "http://download.finance.yahoo.com/d/quotes.csv?s={0}{1}=X&f=sl1d1t1ba&e=.csv";

	public double getRate(String currencyFrom, String currencyTo) {

		double result = 0;
		String urlWithCurrencies = MessageFormat.format(FINANCE_YAHOO,
				currencyFrom, currencyTo);

		try {

			URL url = new URL(urlWithCurrencies);
			URLConnection conn = url.openConnection();

			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream);
			BufferedReader rd = new BufferedReader(inputStreamReader);
			String line;
			while ((line = rd.readLine()) != null) {
				if (line != null) {
					String[] token = line.split(",");
					String sValue = token[1];
					result = Double.parseDouble(sValue);
				}
			}
			inputStream.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return result;
	}

}
