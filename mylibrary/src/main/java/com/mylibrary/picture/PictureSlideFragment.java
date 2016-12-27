package com.mylibrary.picture;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.mylibrary.R;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 李贺翔
 * 查看大图
 */
public class PictureSlideFragment extends Fragment {
    private String url;
    private SimpleDraweeView imageView;
    private PhotoViewAttacher photoViewAttacher;

    public static PictureSlideFragment newInstance(String url) {
        PictureSlideFragment f = new PictureSlideFragment();
        Bundle args = new Bundle();
        args.putString("url", url);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments() != null ? getArguments().getString("url") : "";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_picture_slide, container, false);
        imageView = (SimpleDraweeView) v.findViewById(R.id.iv_main_pic);
        photoViewAttacher = new PhotoViewAttacher(imageView);
        imageView.setController(controller(imageView, Uri.parse(url)));
        return v;
    }

    //渐进式JPEG图,图会从模糊到清晰渐渐呈现。
    public DraweeController controller(DraweeView<GenericDraweeHierarchy> view, Uri uri) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setTapToRetryEnabled(true)
                .setOldController(view.getController())
                .setControllerListener(listener)
                .build();
        return controller;
    }

    private ControllerListener listener = new BaseControllerListener() {
        @Override
        public void onSubmit(String id, Object callerContext) {

        }

        @Override
        public void onFinalImageSet(String id, Object imageInfo, Animatable animatable) {
            //图片下载成功时调用
            photoViewAttacher.update();
        }

        @Override
        public void onIntermediateImageSet(String id, Object imageInfo) {
            //如果允许呈现渐进式JPEG，同时图片也是渐进式图片，onIntermediateImageSet会在每个扫描被解码后回调
        }

        @Override
        public void onIntermediateImageFailed(String id, Throwable throwable) {

        }

        @Override
        public void onFailure(String id, Throwable throwable) {
            //图片下载失败时调用
        }

        @Override
        public void onRelease(String id) {

        }
    };
}