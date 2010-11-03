package de.oose.wechselkurs;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class Ruler extends ScrollView {
private OnValueChangedListener onValueChangedListener;
private LinearLayout scale;	
  public interface OnValueChangedListener{
		void onValueChanged(View v, double aValue);
	}

	public Ruler(Context context) {
		super(context);
	}

	public Ruler(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setHorizontalScrollBarEnabled(false);
		this.setVerticalScrollBarEnabled(false);
		this.setWillNotDraw(false);
		Log.d("Ruler","construction2");
		scale = new LinearLayout(context);
		scale.setPadding(0, 149, 0, 149);
		scale.setOrientation(LinearLayout.VERTICAL);
		scale.setBackgroundColor(R.color.lightgrey);
		TextView t=new TextView(context);
		scale.addView(t);
		/*for (int i = 10; i >= 0; i--) {
			TextView t= createScalepoint(context, Long.toString((Math.round(Math.pow(2, i)))));
			Log.d("Ruler","text " + t.getText());
			//scale.addView(t);		
		}*/
		this.addView(scale,LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
//		invalidate();
	}

	public Ruler(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}; 
	

	private TextView createScalepoint(Context context, String aValue) {
		TextView t= new TextView(context);
		/*t.setText(aValue);
		t.setWidth(LayoutParams.MATCH_PARENT);
		t.setHeight(LayoutParams.WRAP_CONTENT);
		t.setGravity(Gravity.CENTER_HORIZONTAL);
		t.setTextSize(TypedValue.COMPLEX_UNIT_PX, 60);
		t.setTextColor(R.color.black);*/
		return t;
	}
	
	public void setOnValueChangedListener(OnValueChangedListener l){
		onValueChangedListener=l;
	}
}
