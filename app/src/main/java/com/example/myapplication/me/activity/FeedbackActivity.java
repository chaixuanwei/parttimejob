package com.example.myapplication.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.config.Config;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.model.MeModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.feedback_id)
    RadioButton feedbackId;
    @BindView(R.id.feedback_pay)
    RadioButton feedbackPay;
    @BindView(R.id.feedback_other)
    RadioButton feedbackOther;
    @BindView(R.id.feedback_ed_content)
    EditText feedbackEdContent;
    @BindView(R.id.feedback_num)
    TextView feedbackNum;
    @BindView(R.id.feedback_bt_login)
    TextView feedbackBtLogin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        feedbackEdContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                feedbackNum.setText(s.length()+"/200");
            }
        });
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

    @OnClick({R.id.back, R.id.feedback_id, R.id.feedback_pay, R.id.feedback_other, R.id.feedback_bt_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.feedback_id:
                break;
            case R.id.feedback_pay:
                break;
            case R.id.feedback_other:
                break;
            case R.id.feedback_bt_login:
                Intent mFeedbackSuccessIntent = new Intent(this, FeedbackSuccessActivity.class);
                mFeedbackSuccessIntent.putExtra(Config.SUCCESS,Config.FEEDBACK);
                startActivity(mFeedbackSuccessIntent);
                break;
        }
    }

}
