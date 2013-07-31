package com.choiboi.apiexamples.toast;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.choiboi.apiexamples.R;

public class ToastMainActivity extends Activity {
    
    // Constants.
    private final String TOAST_SHORT_MSG = "This is a Toast with LENGTH_SHORT duration.";
    private final String TOAST_LONG_MSG = "This is a Toast with LENGTH_LONG duration.";
    private final String TOAST_4_SHORT_MSG = "This is a Toast with 4 times LENGTH_SHORT duration.";
    private final String TOAST_4_LONG_MSG = "This is a Toast with 4 times LENGTH_LONG duration.";
    private final String TOAST_CUSTOM_BG_MSG = "This is a Toast with custom background.";

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
        } else if (v.getId() == R.id.toast_4_short_button) {
            // Start loop to call toast 4 times.
            for (int i = 1; i <= 4; i++) {
                Toast.makeText(this, TOAST_4_SHORT_MSG, Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.toast_4_long_button) {
            // Start loop to call toast 4 times.
            for (int i = 1; i <= 4; i++) {
                Toast.makeText(this, TOAST_4_LONG_MSG, Toast.LENGTH_LONG).show();
            }
        } else if (v.getId() == R.id.toast_custom_bg_button) {
            View toastRoot = getLayoutInflater().inflate(R.layout.activity_toast_custom_toast, null);
            TextView tvToast = (TextView) toastRoot.findViewById(R.id.toast_textview);
            tvToast.setText(TOAST_CUSTOM_BG_MSG);
            
            Toast toast = new Toast(getApplicationContext());
            toast.setView(toastRoot);
            toast.show();
        }
    }
}
