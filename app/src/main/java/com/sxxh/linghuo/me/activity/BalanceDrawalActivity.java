package com.sxxh.linghuo.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.model.MeModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BalanceDrawalActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.pay)
    TextView pay;
    @BindView(R.id.balancedrawal_mode)
    TextView balancedrawalMode;
    @BindView(R.id.balance_rl_weixin)
    RelativeLayout balanceRlWeixin;
    @BindView(R.id.balance_rl_zhifubao)
    RelativeLayout balanceRlZhifubao;
    @BindView(R.id.balance_rl_yinhang)
    RelativeLayout balanceRlYinhang;
    @BindView(R.id.pay_mode_ll)
    LinearLayout payModeLl;
    @BindView(R.id.total_balance)
    TextView totalBalance;
    @BindView(R.id.balance_sum)
    EditText balanceSum;

    @Override
    public int getLayoutId() {
        return R.layout.activity_balance_drawal;
    }

    @Override
    public void initView() {
        Intent mIntent = getIntent();
        String mBalance = mIntent.getStringExtra("balance");
        totalBalance.setText(mBalance);
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

    @OnClick({R.id.back, R.id.pay, R.id.balancedrawal_mode, R.id.balance_rl_weixin, R.id.balance_rl_zhifubao, R.id.balance_rl_yinhang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.pay:
                break;
            case R.id.balancedrawal_mode:
                payModeLl.setVisibility(View.VISIBLE);
                break;
            case R.id.balance_rl_weixin:
                payModeLl.setVisibility(View.GONE);
                balancedrawalMode.setText("微信");
                break;
            case R.id.balance_rl_zhifubao:
                payModeLl.setVisibility(View.GONE);
                balancedrawalMode.setText("支付宝");
                break;
            case R.id.balance_rl_yinhang:
                Intent mIntent = new Intent(this, BindbankcardActivity.class);
                startActivityForResult(mIntent, Config.BINDBACK);
                payModeLl.setVisibility(View.GONE);
                balancedrawalMode.setText("银行卡");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Config.BINDBACK && resultCode == Config.FINISH) {
            Bundle mBundle = data.getBundleExtra("bundle");
            String mName = mBundle.getString("name");
            String mIdnumber = mBundle.getString("idnumber");
            String mIdcard = mBundle.getString("idcard");
            String mIdphoto = mBundle.getString("idphoto");
            balancedrawalMode.setText("银行卡(" + mIdcard.substring(mIdcard.length() - 4) + ")");
        }
    }
}
