package com.sxxh.linghuo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.frame.BaseActivity;
import com.sxxh.linghuo.login.LoginActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntroduceActivity extends BaseActivity {

    @BindView(R.id.introduce_banner)
    Banner introduceBanner;
    @BindView(R.id.skip)
    TextView skip;
    @BindView(R.id.atonce)
    TextView atonce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        ButterKnife.bind(this);
        InitView();
    }

    private void InitView() {
        final List<Integer> images = new ArrayList<>();
        images.add(R.mipmap.start_one);
        images.add(R.mipmap.start_two);
        images.add(R.mipmap.start_three);
        introduceBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(IntroduceActivity.this).load(path).into(imageView);
            }
        });
        introduceBanner.setImages(images);
        introduceBanner.start();
        introduceBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pI, float pV, int pI1) {

            }

            @Override
            public void onPageSelected(int pI) {
                skip.setVisibility(View.INVISIBLE);
                atonce.setVisibility(View.INVISIBLE);
                if (pI == 0) {
                    skip.setVisibility(View.VISIBLE);
                } else if (pI == images.size()-1){
                    atonce.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int pI) {

            }
        });
    }

    @OnClick({R.id.skip, R.id.atonce})
    public void onClick(View view) {
        Intent mIntent = new Intent(this, LoginActivity.class);
        switch (view.getId()) {
            case R.id.skip:
                startActivity(mIntent);
                finish();
                break;
            case R.id.atonce:
                startActivity(mIntent);
                finish();
                break;
        }
    }
}
