package com.example.myapplication.me.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.model.MeModel;

public class PeopleActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_people;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public CommonPresenter getPresenter() {
        return null;
    }

    @Override
    public MeModel getModel() {
        return null;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }
}
