package com.example.myapplication.me.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.model.MeModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WaitAuditActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tbr_name)
    TextView tbrName;
    @BindView(R.id.wait_audit)
    TextView waitAudit;

    @Override
    public int getLayoutId() {
        return R.layout.activity_wait_audit;
    }

    @Override
    public void initView() {

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
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
