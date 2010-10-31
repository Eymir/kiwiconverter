package de.oose.wechselkurs.prefs;

import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import de.oose.wechselkurs.R;

/**
 * Activity zur Einstellung der Kiwi-Applikation.
 * 
 * @author StefanZ
 */
public class KiwiPreferences extends PreferenceActivity implements
		OnSharedPreferenceChangeListener {

	private EditTextPreference preferenceCurrency1;

	private EditTextPreference preferenceCurrency2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.addPreferencesFromResource(R.xml.kiwi_preferences);

		preferenceCurrency1 = (EditTextPreference) getPreferenceScreen()
				.findPreference("prefs_currency_1");
		preferenceCurrency2 = (EditTextPreference) getPreferenceScreen()
				.findPreference("prefs_currency_2");
	}

	@Override
	protected void onResume() {
		super.onResume();
		getPreferenceScreen().getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(this);
		setupPrefSummaries();
	}

	@Override
	protected void onPause() {
		super.onPause();
		getPreferenceScreen().getSharedPreferences()
				.unregisterOnSharedPreferenceChangeListener(this);
	}

	protected void setupPrefSummaries() {
		SharedPreferences preferences = getPreferences(this);

		String curr1 = preferences.getString("prefs_currency_1", null);
		String curr2 = preferences.getString("prefs_currency_2", null);

		preferenceCurrency1.setSummary("Aktuell: " + curr1);
		preferenceCurrency2.setSummary("Aktuell: " + curr2);
	}

	public static SharedPreferences getPreferences(ContextWrapper ctx) {
		return ctx.getSharedPreferences(ctx.getPackageName() + "_preferences",
				MODE_PRIVATE);
	}

	public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {		
		setupPrefSummaries();
	}
}
