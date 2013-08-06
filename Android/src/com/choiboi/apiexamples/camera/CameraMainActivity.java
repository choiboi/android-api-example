package com.choiboi.apiexamples.camera;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.choiboi.apiexamples.R;

public class CameraMainActivity extends Activity {
    
    // Member fields.
    private ImageView mImage;
    
    // Constants.
    public final int CAMERA_NO_FILEPATH = 0;
    public final int CAMERA_WITH_FILEPATH = 1;
    public final String TEMP_JPEG_FILENAME = "temp.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_main);
        
        mImage = (ImageView) findViewById(R.id.camera_image);
    }
    
    public void onButtonClick(View v) {
        if (v.getId() == R.id.camera_no_filepath_button) {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_NO_FILEPATH);
        } else if (v.getId() == R.id.camera_filepath_button) {
            File file = new File(getOutputLink(TEMP_JPEG_FILENAME));
            
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            startActivityForResult(cameraIntent, CAMERA_WITH_FILEPATH);
        }
    }

    private String getOutputLink(String filename) {
        String directory = "";

        // Check if storage is mounted.
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), 
                                                    getResources().getString(R.string.app_name));
            // Create the storage directory if it does not exist
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    return null;
                }
            }
            directory = mediaStorageDir.getPath() + File.separator + filename;
        }
        return directory;
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_NO_FILEPATH) {
                String filepath = data.getData().toString();
                Toast.makeText(this, "image: " + filepath, Toast.LENGTH_SHORT).show();
            } else if (requestCode == CAMERA_WITH_FILEPATH) {
                String filepath = getOutputLink(TEMP_JPEG_FILENAME);
                Bitmap img = BitmapFactory.decodeFile(filepath);
                mImage.setImageBitmap(img);
            }
        }
    }
}
