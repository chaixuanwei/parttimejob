package com.example.myapplication.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.config.Config;
import com.example.myapplication.frame.BaseActivity;
import com.example.myapplication.local_utils.SharedPrefrenceUtils;
import com.example.myapplication.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends BaseActivity {

    private TextView mSplashThree;
    private boolean mIsFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        mSplashThree = findViewById(R.id.splash_three);
        mIsFirst = SharedPrefrenceUtils.getBoolean(this, Config.ISFIRST, true);
        if (mIsFirst)SharedPrefrenceUtils.saveBoolean(this,Config.ISFIRST,false);
        mSplashThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsFirst) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    SplashActivity.this.finish();
                }else {
                    Intent intent = new Intent(SplashActivity.this, IntroduceActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                }
            }
        });
    }
}
