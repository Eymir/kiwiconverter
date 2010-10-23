package de.oose.wechselkurs;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class main extends Activity {
	public static final String EURO = "EUR";
	public static final String NZD = "NZD";
	public static final String NORMALIZED_RATE = "1";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		String text = currencyStringBuilder(EURO, NZD);
		TextView view = (TextView) findViewById(R.id.conversion1);
		view.setText(text);

		text = currencyStringBuilder(NZD, EURO);
		view = (TextView) findViewById(R.id.conversion2);
		view.setText(text);

	}

	private String currencyStringBuilder(String from, String to) {
		CurrencyService currencyService = new YahooFinance();
		String text = NORMALIZED_RATE + from + " " + getText(R.string.ist)
				+ " " + currencyService.getRate(from, to) + to;
		return text;
	}
}