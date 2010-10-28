package de.oose.wechselkurs.prefs;

import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import de.oose.wechselkurs.R;

/**
 * Activity zur Einstellung der Kiwi-Applikation.
 * 
 * @author StefanZ
 */
public class KiwiPreferences extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.addPreferencesFromResource(R.xml.kiwi_preferences);
	}

	public static SharedPreferences getPreferences(ContextWrapper ctx) {
		return ctx.getSharedPreferences(ctx.getPackageName() + "_preferences",
				MODE_PRIVATE);
	}
}
