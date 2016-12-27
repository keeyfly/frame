package com.mylibrary.picture;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.mylibrary.R;
import com.tangxiaolv.telegramgallery.GalleryActivity;
import com.tangxiaolv.telegramgallery.GalleryConfig;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;


/**
 * Created by Administrator on 2016/11/24.
 */
public class PictureView extends Dialog {

    private Activity mContext;
    private String ALBUM_NAME = this.getContext().getPackageName();
    private int CAPTURE_TAKE_PHOTO_REQUEST = -1;
    private int CAPTURE_SELECT_PHOTO_REQUEST = -2;
    private String filePath = "";

    public PictureView(Activity context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_pick_picture);
        TextView take_from_camera = (TextView) findViewById(R.id.user_auth_fail_description);
        TextView take_from_album = (TextView) findViewById(R.id.pic_album_take);
        TextView cancel = (TextView) findViewById(R.id.pic_cancel);
        getWindow().setWindowAnimations(R.style.picture_dialog_style);
        getWindow().setGravity(Gravity.BOTTOM);// 靠底部显示
        getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);// 设置dialog的宽度和高度 我设的宽度是填充父窗口，高度内容包裹
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(lp);
        take_from_camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (CAPTURE_TAKE_PHOTO_REQUEST == -1) {
                    Toast.makeText(mContext, "请设置相机请求码", Toast.LENGTH_SHORT).show();
                } else {
                    filePath = generatePhotoFilePath();
                    PictureConfig config = new PictureConfig.Build()
                            .imageFilePath(filePath)
                            .requestCode(CAPTURE_TAKE_PHOTO_REQUEST)
                            .build();
                    PicturePickActivity.openActivity(mContext, config);
                }
                PictureView.this.dismiss();
            }
        });

        take_from_album.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (CAPTURE_SELECT_PHOTO_REQUEST == -2) {
                    Toast.makeText(mContext, "请设置选择图片请求码", Toast.LENGTH_SHORT).show();
                } else {
                    GalleryConfig config = new GalleryConfig.Build()
                            .limitPickPhoto(6)
                            .singlePhoto(false)
                            .hintOfPick("最多只能上传6张图片")
                            //  .filterMimeTypes(new String[]{"image/jpg"})
                            .build();
                    GalleryActivity.openActivity(mContext, CAPTURE_SELECT_PHOTO_REQUEST, config);
                }
                PictureView.this.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PictureView.this.dismiss();
            }
        });
    }

    /**
     * 拍照code
     *
     * @param requestCode
     */
    public void setTakePhotoRequestCode(int requestCode) {
        this.CAPTURE_TAKE_PHOTO_REQUEST = requestCode;
    }

    /**
     * 图库code
     *
     * @param requestCode
     */
    public void setSelectPhotoRequestCode(int requestCode) {
        this.CAPTURE_SELECT_PHOTO_REQUEST = requestCode;
    }

    /**
     * 设置图片存储路径
     *
     * @param filePath
     */
    public void setPhotoFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 生成文件绝对路径
     *
     * @return String
     */
    public String generatePhotoFilePath() {
        return getAlbumStoragePath() + "/" + generatePhotoFileName();
    }

    public String getAlbumStoragePath() {
        return getAlbumStoragePath(ALBUM_NAME);
    }

    /**
     * 生成相册路径
     *
     * @param albumName
     * @return String
     */
    public String getAlbumStoragePath(String albumName) {
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + albumName;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    /**
     * @return String
     */
    public String generatePhotoFileName() {
        return getTime() + ".png";
    }

    public String getTime() {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date curDate = new Date(System.currentTimeMillis());
        return timeFormatter.format(curDate);
    }
}
