package de.oose.wechselkurs;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;

public class main extends Activity {
	private TextView value1, value2;
	private double rate;
	public static final String Currency1 = "EUR";
	public static final String Currency2 = "NZD";
	public static final double NORMALIZED_RATE = 1;

	private OnFocusChangeListener value1Listener= new OnFocusChangeListener(){
		public void onFocusChange(View v, boolean hasFocus) {
			if (hasFocus){setValue1(1);}
		}
	};	
	private OnFocusChangeListener value2Listener= new OnFocusChangeListener(){
		public void onFocusChange(View v, boolean hasFocus) {
			if (hasFocus){setValue2(1);}
		}
	};	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		value1 = (TextView) findViewById(R.id.value1);
		value2 = (TextView) findViewById(R.id.value2);
		CurrencyService currencyService = new YahooFinance();
		rate=currencyService.getRate(Currency1, Currency2);
		value1.setText(formatCurrency(NORMALIZED_RATE,Currency1));
		value2.setText(formatCurrency(rate,Currency2));
		value1.requestFocus();
		value1.setOnFocusChangeListener(value1Listener);
		value2.setOnFocusChangeListener(value2Listener);
		}
	
	String formatCurrency(double aValue, String currency){
		NumberFormat nf = NumberFormat.getInstance();
		try {
			((DecimalFormat)nf).setMaximumFractionDigits(2);
			((DecimalFormat)nf).setMinimumFractionDigits(2);
		} catch (Exception e) {
			// ignore		
			}
		return nf.format(aValue) + " " + currency;
	}

	private void setValue1(double aValue) {
		value1.setText(formatCurrency(aValue,Currency1));
		value2.setText(formatCurrency(rate*aValue,Currency2));
	}

	private void setValue2(double aValue) {
		value1.setText(formatCurrency(aValue/rate,Currency1));
		value2.setText(formatCurrency(aValue,Currency2));	
	}
}