package com.sxxh.linghuo.me.activity;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.model.MeModel;

import butterknife.BindView;
import butterknife.OnClick;

public class FeedbackSuccessActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tbr_name)
    TextView tbrName;
    @BindView(R.id.success)
    TextView msuccess;

    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback_success;
    }

    @Override
    public void initView() {
        Intent mIntent = getIntent();
        String success = mIntent.getStringExtra(Config.SUCCESS);
        if (success.equals(Config.APPLY)){
            tbrName.setText("报名成功");
            msuccess.setText("报名成功");
        } else {
            tbrName.setText("反馈成功");
            msuccess.setText("反馈成功");
        }
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
    public void onError(int whichApi,Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
