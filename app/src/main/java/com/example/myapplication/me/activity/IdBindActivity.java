package com.example.myapplication.me.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.model.MeModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IdBindActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.now_img_photo)
    ImageView nowImgPhoto;
    @BindView(R.id.now_txt_name)
    TextView nowTxtName;
    @BindView(R.id.now_txt_id)
    TextView nowTxtId;
    @BindView(R.id.now_login)
    RelativeLayout nowLogin;
    @BindView(R.id.now_ll)
    LinearLayout nowLl;
    @BindView(R.id.grey_line)
    TextView greyLine;

    @Override
    public int getLayoutId() {
        return R.layout.activity_id_bind;
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

    @OnClick({R.id.back, R.id.grey_line})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.grey_line:
                break;
        }
    }
}
