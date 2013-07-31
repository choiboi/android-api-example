package com.choiboi.apiexamples.toast;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.choiboi.apiexamples.R;

public class ToastMainActivity extends Activity {
    
    // Constants.
    private final String TOAST_SHORT_MSG = "This is a Toast with LENGTH_SHORT duration.";
    private final String TOAST_LONG_MSG = "This is a Toast with LENGTH_LONG duration.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
    }
    
    public void onButtonClick(View v) {
        if (v.getId() == R.id.toast_short_button) {
            Toast.makeText(this, TOAST_SHORT_MSG, Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.toast_long_button) {
            Toast.makeText(this, TOAST_LONG_MSG, Toast.LENGTH_LONG).show();
        }
    }
}
