package com.mylibrary.picture;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 李贺翔 on 2016/12/16.
 * Description:
 */
public class PictureConfig implements Parcelable {

    private String imageFilePath;

    private int requestCode;

    public PictureConfig(int requestCode, String imageFilePath) {
        this.requestCode = requestCode;
        this.imageFilePath = imageFilePath;
    }

    protected PictureConfig(Parcel in) {
        imageFilePath = in.readString();
        requestCode = in.readInt();
    }

    public static final Creator<PictureConfig> CREATOR = new Creator<PictureConfig>() {
        @Override
        public PictureConfig createFromParcel(Parcel in) {
            return new PictureConfig(in);
        }

        @Override
        public PictureConfig[] newArray(int size) {
            return new PictureConfig[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imageFilePath);
        parcel.writeInt(requestCode);
    }

    public static class Build {

        private String imageFilePath;

        /**
         * @param imageFilePath
         * @return
         */
        public Build imageFilePath(String imageFilePath) {
            this.imageFilePath = imageFilePath;
            return this;
        }

        private int requestCode;

        /**
         * @param requestCode
         * @return
         */
        public Build requestCode(int requestCode) {
            this.requestCode = requestCode;
            return this;
        }

        public PictureConfig build() {
            return new PictureConfig(requestCode, imageFilePath);
        }


    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public int getRequestCode() {
        return requestCode;
    }


}
