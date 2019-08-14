package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.config.Config;
import com.example.myapplication.frame.BaseActivity;
import com.example.myapplication.local_utils.SharedPrefrenceUtils;
import com.example.myapplication.local_utils.statusbar.StatusBarCompat;

public class SplashActivity extends BaseActivity {

    private TextView mSplashThree;
    private boolean mIsFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsFirst = SharedPrefrenceUtils.getBoolean(this, Config.ISFIRST, true);
        if (mIsFirst)SharedPrefrenceUtils.saveBoolean(this,Config.ISFIRST,false);
        if (!mIsFirst) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            SplashActivity.this.finish();
        }else {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            StatusBarCompat.setStatusBarColor(this, mAppColor);
            setContentView(R.layout.activity_splash);
            mSplashThree = findViewById(R.id.splash_three);
            mSplashThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                }
            });
        }
    }
}
