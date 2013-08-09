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
            // Start camera intent without specified filepath.
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_NO_FILEPATH);
        } else if (v.getId() == R.id.camera_filepath_button) {
            // Create a new File object with specified filepath, where the
            // captured image will be located.
            File file = new File(getOutputLink(TEMP_JPEG_FILENAME));
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            startActivityForResult(cameraIntent, CAMERA_WITH_FILEPATH);
        }
    }

    /*
     * Creates a new file path into the standard Android pictures directory.
     */
    private String getOutputLink(String filename) {
        String directory = "";

        // Check if storage is mounted.
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), 
                                                    getResources().getString(R.string.app_name));
            // Create the storage directory if it does not exist.
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
                // Get the image from intent data.
                Bundle bundle = data.getExtras();
                // This Bundle object will contain the Bitmap image, 
                // so no Bitmap decoding will be required.
                Bitmap img = (Bitmap) bundle.get("data");
                mImage.setImageBitmap(img);
            } else if (requestCode == CAMERA_WITH_FILEPATH) {
                // Get the image from the filepath you specified when you
                // started the camera intent.
                String filepath = getOutputLink(TEMP_JPEG_FILENAME);
                Bitmap img = BitmapFactory.decodeFile(filepath);
                mImage.setImageBitmap(img);
                Toast.makeText(this, "Image is saved in: " + filepath, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
