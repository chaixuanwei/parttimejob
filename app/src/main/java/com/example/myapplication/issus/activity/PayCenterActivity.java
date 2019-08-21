package com.example.myapplication.issus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
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
    @BindView(R.id.pay_topay)
    TextView payTopay;
    @BindView(R.id.pay_modepay)
    TextView payModepay;
    @BindView(R.id.pay_rl)
    RelativeLayout payRl;
    @BindView(R.id.pay_txt_balance)
    TextView payTxtBalance;
    @BindView(R.id.pay_rb_balance)
    RadioButton payRbBalance;
    @BindView(R.id.pay_rb_weixin)
    RadioButton payRbWeixin;
    @BindView(R.id.pay_rb_zhifubao)
    RadioButton payRbZhifubao;
    @BindView(R.id.pay_mode_ll)
    LinearLayout payModeLl;
    @BindView(R.id.pay_rl_yue)
    RelativeLayout payRlYue;
    @BindView(R.id.pay_rl_weixin)
    RelativeLayout payRlWeixin;
    @BindView(R.id.pay_rl_zhifubao)
    RelativeLayout payRlZhifubao;

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

    @OnClick({R.id.back, R.id.pay, R.id.pay_rl, R.id.pay_rb_balance, R.id.pay_rb_weixin, R.id.pay_rb_zhifubao, R.id.pay_rl_yue, R.id.pay_rl_weixin, R.id.pay_rl_zhifubao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.pay:
                Intent mIntent = new Intent(this, SubmitPayActivity.class);
                startActivity(mIntent);
                break;
            case R.id.pay_rl:
                if (payModeLl.getVisibility() == View.VISIBLE) {
                    payModeLl.setVisibility(View.GONE);
                } else {
                    payModeLl.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.pay_rb_balance:
                payModepay.setText("账户余额");
                break;
            case R.id.pay_rb_weixin:
                payModepay.setText("微信");
                break;
            case R.id.pay_rb_zhifubao:
                payModepay.setText("支付宝");
                break;
            case R.id.pay_rl_yue:
                payModepay.setText("账户余额");
                payRbBalance.setChecked(true);
                break;
            case R.id.pay_rl_weixin:
                payModepay.setText("微信");
                payRbWeixin.setChecked(true);
                break;
            case R.id.pay_rl_zhifubao:
                payModepay.setText("支付宝");
                payRbZhifubao.setChecked(true);
                break;
        }
    }
}
