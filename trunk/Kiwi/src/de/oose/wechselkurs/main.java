package de.oose.wechselkurs;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import de.oose.wechselkurs.prefs.KiwiPreferences;

public class main extends Activity {
	private TextView value1, value2;
	private double rate;
	private String currency1 = "EUR";
	private String currency2 = "NZD";
	private ScrollView sliderule; 
	public static final double NORMALIZED_RATE = 1;
	private static final int ACTIVITY_PREFS=1;

	private OnFocusChangeListener value1Listener = new OnFocusChangeListener() {
		public void onFocusChange(View v, boolean hasFocus) {
			if (hasFocus) {
				setValue1(1);
			}
		}
	};
	private OnFocusChangeListener value2Listener = new OnFocusChangeListener() {
		public void onFocusChange(View v, boolean hasFocus) {
			if (hasFocus) {
				setValue2(1);
			}
		}
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
			value1 = (TextView) findViewById(R.id.value1);
			value2 = (TextView) findViewById(R.id.value2);
			sliderule = (ScrollView) findViewById(R.id.sliderule);
			//final LinearLayout scale = (LinearLayout) findViewById(R.id.scale);
			value1.requestFocus();
			value1.setOnFocusChangeListener(value1Listener);
			value2.setOnFocusChangeListener(value2Listener);
			
			initCurrencies();

			sliderule.scrollTo(0, 799); //TODO: warum geht das nicht??
			sliderule.setOnTouchListener(new View.OnTouchListener() {
				
				public boolean onTouch(View v, MotionEvent event) {
					double value =Math.pow(2,((double)(800 - sliderule.getScrollY()))/80);
					if (value1.hasFocus()){
						setValue1(value);} 
					else{
						setValue2(value);};
					return false;
				};
			});
			} catch (Exception e) {
			Log.e("KiwiApp", e.getMessage(), e);
		}
	};

	String formatCurrency(double aValue, String currency) {
		NumberFormat nf = NumberFormat.getInstance();
		try {
			((DecimalFormat) nf).setMaximumFractionDigits(2);
			((DecimalFormat) nf).setMinimumFractionDigits(2);
		} catch (Exception e) {
			// ignore
		}
		return nf.format(aValue) + " " + currency;
	}

	private void setValue1(double aValue) {
		value1.setText(formatCurrency(aValue, currency1));
		value2.setText(formatCurrency(rate * aValue, currency2));
	}

	private void setValue2(double aValue) {
		value1.setText(formatCurrency(aValue / rate, currency1));
		value2.setText(formatCurrency(aValue, currency2));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.main_menu_prefs:
			Intent intent = new Intent(this, KiwiPreferences.class);
			startActivityForResult(intent,ACTIVITY_PREFS);
			return true;
		case R.id.main_menu_close:
			this.finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d("ActivityResult" ,"");//TODO: Wird nie aufgerufen. Warum?
		if (requestCode==ACTIVITY_PREFS && resultCode != RESULT_CANCELED) {
			initCurrencies();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}


	
	private void initCurrencies() {
		SharedPreferences prefs = KiwiPreferences.getPreferences(getApplication());
		currency1 = prefs.getString("prefs_currency_1", currency1);
		currency2 = prefs.getString("prefs_currency_2", currency2);
		updateRate();
	}

	private void updateRate() {
		CurrencyService currencyService = new YahooFinance();
		rate=currencyService.getRate(currency1, currency2);
		double value =Math.pow(2,((double)(800 - sliderule.getScrollY()))/80);
		if (value1.hasFocus()){
			setValue1(value);} 
		else{
			setValue2(value);};

	}
}