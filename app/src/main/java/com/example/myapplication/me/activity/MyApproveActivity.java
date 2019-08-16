package com.example.myapplication.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.model.MeModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyApproveActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.people)
    LinearLayout people;
    @BindView(R.id.firm)
    LinearLayout firm;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_approve;
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
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick({R.id.back, R.id.people, R.id.firm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.people:
                Intent mIntentPeople = new Intent(this, PeopleActivity.class);
                startActivity(mIntentPeople);
                break;
            case R.id.firm:
                Intent mIntentFirm = new Intent(this, FirmActivity.class);
                startActivity(mIntentFirm);
                break;
        }
    }
}
