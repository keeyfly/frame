package com.mylibrary.picture;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.mylibrary.R;

import java.util.ArrayList;


/**
 * Created by 李贺翔 on 2016/12/2.
 * 查看大图
 */
public class PicViewerActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TextView tv_indicator;
    private ArrayList<String> urlList;
    private int index = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏幕显示
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ArrayList<String> imageUrl = getIntent().getStringArrayListExtra("imageUrl");
        index = getIntent().getIntExtra("index", -1);
        setContentView(R.layout.activity_pic_viewer);
        urlList = new ArrayList<>();
        urlList.addAll(imageUrl);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tv_indicator = (TextView) findViewById(R.id.tv_indicator);
        viewPager.setAdapter(new PictureSlidePagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tv_indicator.setText(String.valueOf(position + 1) + "/" + urlList.size());
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(index);
    }

    private class PictureSlidePagerAdapter extends FragmentStatePagerAdapter {

        public PictureSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PictureSlideFragment.newInstance(urlList.get(position));
        }

        @Override
        public int getCount() {
            return urlList.size();
        }
    }

}
