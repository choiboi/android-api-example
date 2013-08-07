package com.choiboi.apiexamples.gallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.choiboi.apiexamples.R;

public class GalleryMainActivity extends Activity {
    
    // Member fields.
    private ImageView mImage;
    
    // Constants.
    public static final int GALLERY_INTENT = 0;
    public static final int GALLERY_CHOOSER_INTENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_main);
        
        mImage = (ImageView) findViewById(R.id.gallery_image);
    }
    
    public void onButtonClick(View v) {
        if (v.getId() == R.id.gallery_intent_button) {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, GALLERY_INTENT);
        } else if (v.getId() == R.id.gallery_chooser_intent_button) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, GALLERY_CHOOSER_INTENT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_INTENT) {
                
            } else if (requestCode == GALLERY_CHOOSER_INTENT) {
                
            }
        }
    }
}
