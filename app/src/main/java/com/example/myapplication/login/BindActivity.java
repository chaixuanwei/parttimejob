package com.example.myapplication.login;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.model.LoginModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindActivity extends BaseMvpActivity<CommonPresenter, LoginModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.register)
    TextView register;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bind;
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
    public LoginModel getModel() {
        return null;
    }

    @Override
    public void onError(int whichApi,Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick({R.id.back, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.register:
                break;
        }
    }
}
