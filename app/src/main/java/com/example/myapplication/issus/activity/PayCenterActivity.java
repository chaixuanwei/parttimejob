package com.example.myapplication.issus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.me.activity.SubmitPayActivity;
import com.example.myapplication.model.IssusModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayCenterActivity extends BaseMvpActivity<CommonPresenter, IssusModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.pay)
    TextView pay;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay_center;
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
    public IssusModel getModel() {
        return new IssusModel();
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick({R.id.back, R.id.pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.pay:
                Intent mIntent = new Intent(this, SubmitPayActivity.class);
                startActivity(mIntent);
                break;
        }
    }
}
