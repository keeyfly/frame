package com.hx.novel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mylibrary.picture.PictureView;
import com.tangxiaolv.telegramgallery.GalleryActivity;

import java.util.List;


public class MainActivity extends AppCompatActivity {


    private Button takePhoto;
    private SimpleDraweeView imageView;
    public final int CAPTURE_TAKE_PHOTO_REQUEST = 400;
    public final int CAPTURE_SELECT_PHOTO_REQUEST = 401;
    private PictureView pictureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        pictureView = new PictureView(this);
        pictureView.setTakePhotoRequestCode(CAPTURE_TAKE_PHOTO_REQUEST);
        pictureView.setSelectPhotoRequestCode(CAPTURE_SELECT_PHOTO_REQUEST);
        takePhoto = (Button) findViewById(R.id.take_photo);
        imageView = (SimpleDraweeView) findViewById(R.id.iv_draw);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pictureView.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CAPTURE_TAKE_PHOTO_REQUEST:
                    if (data != null) {
                        String imagePath = data.getStringExtra("imagePath");
                        imageView.setImageURI((new Uri.Builder()).scheme("file").path(imagePath).build());
                    }
                    break;
                case CAPTURE_SELECT_PHOTO_REQUEST:
                    if (data != null) {
                        List<String> photos = (List<String>) data.getSerializableExtra(GalleryActivity.PHOTOS);
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
