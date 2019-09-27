package com.sxxh.linghuo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.frame.BaseActivity;
import com.sxxh.linghuo.local_utils.SharedPrefrenceUtils;

public class SplashActivity extends BaseActivity {

    private TextView mCountDown;
    private boolean mIsFirst;
    private CountDownTimer mStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        mCountDown = findViewById(R.id.count_down);
        mStart = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mCountDown.setText((int) (millisUntilFinished/1000)+"秒后跳过");
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SplashActivity.this.finish();
            }
        }.start();
        mIsFirst = SharedPrefrenceUtils.getBoolean(this, Config.ISFIRST, true);
        if (mIsFirst) SharedPrefrenceUtils.saveBoolean(this, Config.ISFIRST, false);
        mCountDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStart.cancel();
                if (!mIsFirst) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    SplashActivity.this.finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, IntroduceActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        mStart.cancel();
        super.onDestroy();
    }
}
