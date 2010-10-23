package de.oose.wechselkurs;

public interface CurrencyService {

	double getRate(String currencyFrom, String currencyTo);

}