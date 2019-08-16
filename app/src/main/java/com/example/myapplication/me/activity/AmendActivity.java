package com.example.myapplication.me.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.local_utils.statusbar.StatusBarCompat;
import com.example.myapplication.model.MeModel;
import com.example.myapplication.view.RoundImage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AmendActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.headportrait)
    RoundImage headportrait;

    @Override
    public int getLayoutId() {
        return R.layout.activity_amend;
    }

    @Override
    public void initView() {
        StatusBarCompat.setStatusBarColor(this, mAppColor);
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

    @OnClick({R.id.back, R.id.headportrait})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.headportrait:
                break;
        }
    }
}
