package de.oose.wechselkurs;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView wert1 = (TextView)findViewById(R.id.wert1);
        wert1.setText("1 €");
        
        
    }
}