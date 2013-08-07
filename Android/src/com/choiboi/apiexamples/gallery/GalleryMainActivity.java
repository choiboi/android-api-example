package com.choiboi.apiexamples.gallery;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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
            // Start intent to get image using device's default gallery app.
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, GALLERY_INTENT);
        } else if (v.getId() == R.id.gallery_chooser_intent_button) {
            // Start intent to get image using using any image viewing app available on the device.
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, GALLERY_CHOOSER_INTENT);
        }
    }
    
    /*
     * Using the Intent data that the Gallery app returns, this method will
     * retrive the filepath location of the image that the user have
     * selected.
     * 
     * Known issues: This does not work for getting images from Google Drive and
     *               Pisca. 
     */
    public String getGalleryImagePath(Intent data) {
        Uri imgUri = data.getData();
        String filePath = "";
        if (data.getType() == null) {
            // For getting images from default gallery app.
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(imgUri, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            filePath = cursor.getString(columnIndex);
            cursor.close();
        } else if (data.getType().equals("image/jpeg") || data.getType().equals("image/png")) {
            // For getting images from dropbox or any other gallery apps.
            filePath = imgUri.getPath();
        }
        return filePath;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_INTENT || requestCode == GALLERY_CHOOSER_INTENT) {
                // Get the filepath and display on imageview.
                String filepath = getGalleryImagePath(data);
                // Check if the specified image exists.
                if (!new File(filepath).exists()) {
                    Toast.makeText(this, "Image does not exist.", Toast.LENGTH_SHORT).show();
                } else {
                    Bitmap img = BitmapFactory.decodeFile(filepath);
                    mImage.setImageBitmap(img);
                }
            } 
        }
    }
}
