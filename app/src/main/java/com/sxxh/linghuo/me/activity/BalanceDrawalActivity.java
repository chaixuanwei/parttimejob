package com.sxxh.linghuo.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.local_utils.ConvertUtil;
import com.sxxh.linghuo.login.bean.AuthCodeBean;
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
//    @BindView(R.id.balance_rl_yinhang)
//    RelativeLayout balanceRlYinhang;
    @BindView(R.id.pay_mode_ll)
    LinearLayout payModeLl;
    @BindView(R.id.total_balance)
    TextView totalBalance;
    @BindView(R.id.balance_sum)
    EditText balanceSum;
    int type = 0;
    private String mBalance;

    @Override
    public int getLayoutId() {
        return R.layout.activity_balance_drawal;
    }

    @Override
    public void initView() {
        Intent mIntent = getIntent();
        mBalance = mIntent.getStringExtra("balance");
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
        Log.e("提现", "onError: 提现" + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.SET_WITHDRAW:
                AuthCodeBean mAuthCodeBeans = (AuthCodeBean) t[0];
                if (mAuthCodeBeans.getMsg().equals("申请成功")) {
                    Intent mSubmitPayIntent = new Intent(this, SubmitPayActivity.class);
                    startActivity(mSubmitPayIntent);
                } else {
                    ToastUtils.showShort(mAuthCodeBeans.getMsg());
                }
                break;
        }
    }

    @OnClick({R.id.back, R.id.pay, R.id.balancedrawal_mode, R.id.balance_rl_weixin, R.id.balance_rl_zhifubao/*, R.id.balance_rl_yinhang*/})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.pay:
                String mPrice = balanceSum.getText().toString();
                float mBalanceNum = ConvertUtil.convertToFloat(this.mBalance, 0);
                float mPayPrice = ConvertUtil.convertToFloat(mPrice, 0);
                if (type != 0 && mPayPrice <= mBalanceNum) {
                    mPresenter.getData(ApiConfig.SET_WITHDRAW, LoadConfig.NORMAL, type, mPayPrice);
                } else {
                    ToastUtils.showShort("请填写正确信息！");
                }
                break;
            case R.id.balancedrawal_mode:
                payModeLl.setVisibility(View.VISIBLE);
                break;
            case R.id.balance_rl_weixin:
                payModeLl.setVisibility(View.GONE);
                balancedrawalMode.setText("微信");
                type = 2;
                break;
            case R.id.balance_rl_zhifubao:
                payModeLl.setVisibility(View.GONE);
                balancedrawalMode.setText("支付宝");
                type = 1;
                break;
//            case R.id.balance_rl_yinhang:
//                Intent mIntent = new Intent(this, BindbankcardActivity.class);
//                startActivityForResult(mIntent, Config.BINDBACK);
//                payModeLl.setVisibility(View.GONE);
//                balancedrawalMode.setText("银行卡");
//                break;
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == Config.BINDBACK && resultCode == Config.FINISH) {
//            Bundle mBundle = data.getBundleExtra("bundle");
//            String mName = mBundle.getString("name");
//            String mIdnumber = mBundle.getString("idnumber");
//            String mIdcard = mBundle.getString("idcard");
//            String mIdphoto = mBundle.getString("idphoto");
//            balancedrawalMode.setText("银行卡(" + mIdcard.substring(mIdcard.length() - 4) + ")");
//        }
//    }
}
