package com.example.myapplication.frame;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.customs.LoadingDialogWithContent;
import com.example.myapplication.local_utils.statusbar.StatusBarCompat;


/**
 * Created by 任小龙 on 2019/3/29.
 */
public class BaseActivity extends AppCompatActivity {

    public ApplicationJob mApplication;
    public int mAppColor;
    private LoadingDialogWithContent mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (ApplicationJob) getApplication();
        Log.e("我是华丽丽的类名-----", this.getClass().getSimpleName());
        mAppColor = ContextCompat.getColor(this, R.color.app_theme_color);
        StatusBarCompat.setTranslucent(getWindow(),true);
        mDialog = new LoadingDialogWithContent(this,getString(R.string.loading));
    }

    public void showLoadingDialog(){
        if (!mDialog.isShowing())mDialog.show();
    }

    public void hideLoadingDialog(){
        if (mDialog.isShowing())mDialog.dismiss();
    }

    public void showToast(String content) {
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String content) {
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void showLog(String content){
        Log.e("睚眦",content);
    }
}
