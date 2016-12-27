package com.mylibrary.picture;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;


/**
 * Created by 李贺翔 on 2016/12/16.
 * Description:拍照获取图片路径
 */
public class PicturePickActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    private int CAPTURE_TAKE_PHOTO_REQUEST = 101;

    private PictureConfig pictureConfig;

    private String imagePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            imagePath = savedInstanceState.getString("filePath");
            Intent intent = new Intent();
            if (imagePath != null && !imagePath.equals("")) {
                intent.putExtra("imagePath", imagePath);
            }
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Intent intent = getIntent();
            pictureConfig = intent.getParcelableExtra("PICTURE_CONFIG");
            takePhoto();
        }
    }

    /**
     * 相机拍照
     */
    public void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        CAPTURE_TAKE_PHOTO_REQUEST = pictureConfig.getRequestCode();
        if (isExternalStorageWritable()) {
            try {
                imagePath = pictureConfig.getImageFilePath();
                Uri uri_photo = Uri.fromFile(new File(imagePath));
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri_photo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.e(TAG, "external storage is not ready!");
        }
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, CAPTURE_TAKE_PHOTO_REQUEST);
        } else {
            Toast.makeText(this, "该设备不支持照相功能", Toast.LENGTH_SHORT).show();
        }
    }

    public static void openActivity(Activity activity, PictureConfig config) {
        Intent intent = new Intent(activity, PicturePickActivity.class);
        intent.putExtra("PICTURE_CONFIG", config);
        activity.startActivityForResult(intent, config.getRequestCode());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("filePath", imagePath);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        try {
            if (imagePath == null || imagePath.equals("")) {
                imagePath = savedInstanceState.getString("filePath");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent intent = new Intent();
        if (resultCode == RESULT_OK) {
            if (requestCode == CAPTURE_TAKE_PHOTO_REQUEST) {
                if (imagePath != null && !imagePath.equals("")) {
                    intent.putExtra("imagePath", imagePath);
                }
            }
        }
        setResult(RESULT_OK, intent);
        finish();
        super.onActivityResult(requestCode, resultCode, data);
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state))
            return true;
        return false;
    }
}
