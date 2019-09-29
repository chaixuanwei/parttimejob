package com.sxxh.linghuo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.frame.BaseActivity;
import com.sxxh.linghuo.local_utils.SharedPrefrenceUtils;

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
        if (mIsFirst) SharedPrefrenceUtils.saveBoolean(this, Config.ISFIRST, false);
        mSplashThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsFirst) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    overridePendingTransition(R.anim.right_enter,R.anim.left_exit);
                    SplashActivity.this.finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, IntroduceActivity.class);
                    overridePendingTransition(R.anim.right_enter,R.anim.left_exit);
                    startActivity(intent);
                    SplashActivity.this.finish();
                }
            }
        });
    }
}
