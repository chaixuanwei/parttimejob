package com.example.myapplication.me;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.home.HomeModel;
import com.example.myapplication.local_utils.statusbar.StatusBarCompat;

public class AmendActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_amend;
    }

    @Override
    public void initView() {
        StatusBarCompat.setStatusBarColor(this,mAppColor);
    }

    @Override
    public void initData() {

    }

    @Override
    public CommonPresenter getPresenter() {
        return new CommonPresenter();
    }

    @Override
    public MeModel getModel() {
        return new MeModel();
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }
}
