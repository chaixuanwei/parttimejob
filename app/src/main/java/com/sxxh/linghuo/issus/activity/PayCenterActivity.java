package com.sxxh.linghuo.issus.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.ToastUtils;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.issus.bean.WeiXinBean;
import com.sxxh.linghuo.issus.bean.ZFBPayBean;
import com.sxxh.linghuo.local_utils.SharedPrefrenceUtils;
import com.sxxh.linghuo.model.IssusModel;
import com.sxxh.linghuo.zhifubao.PayResult;
import com.tencent.mm.opensdk.modelpay.PayReq;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayCenterActivity extends BaseMvpActivity<CommonPresenter, IssusModel> {

    @BindView(R.id.pay_number)
    TextView payNumber;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Config.PAY_ZFB: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    String result = "";
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        //支付成功
                        result = "支付成功";
//                        aliPaySuccess();
                    } else if ("6001".equals(resultStatus)) {
                        result = "您取消了支付";
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        result = "支付失败";
                    }
                    ToastUtils.showShort(result);
                    break;
                }
            }
        }
    };

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
    private String mZFBPayInfo;
    private String mTask_id;
    private String mMoney;
    private String mPay_type;
    private String mMy_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay_center;
    }

    @Override
    public void initView() {
        Intent mIntent = getIntent();
        mTask_id = mIntent.getStringExtra("task_id");
        mMoney = mIntent.getStringExtra("money");
        mMy_id = SharedPrefrenceUtils.getString(this, "my_id");
        payNumber.setText(mMoney);
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
    public void onError(int whichApi, Throwable e) {
        Log.e("PayCenter", "onError: " + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.PAY:
                ZFBPayBean mPayBeans = (ZFBPayBean) t[0];
                mZFBPayInfo = mPayBeans.getData().getResponse();
                aliPayData(mZFBPayInfo);
                break;
            case ApiConfig.WXPAY:
                WeiXinBean mWeiXinBeans = (WeiXinBean) t[0];
                WeiXinBean.DataBean.AppResponseBean mApp_response = mWeiXinBeans.getData().getApp_response();
                PayReq payReq = new PayReq();
                payReq.appId = mApp_response.getAppid(); //应用ID
                payReq.partnerId = mApp_response.getPartnerid(); //商户号 即微信支付分配的商户号WeChatPayEntity .getPartnerid();
                payReq.prepayId = mApp_response.getPrepayid(); //预支付交易会话ID.getPrepayid();
                payReq.packageValue = mApp_response.getPackagestr(); //扩展字段.getPackageX();
                payReq.nonceStr = mApp_response.getNoncestr(); //随机字符串不长于32位。.getNoncestr();
                payReq.timeStamp = mApp_response.getTimestamp() + ""; //时间戳.getTimestamp();
                payReq.sign = mApp_response.getSign();  //签名.getPaySign();
                api.sendReq(payReq);
                break;
        }
    }

    @OnClick({R.id.back, R.id.pay, R.id.pay_rl, R.id.pay_rb_balance, R.id.pay_rb_weixin, R.id.pay_rb_zhifubao, R.id.pay_rl_yue, R.id.pay_rl_weixin, R.id.pay_rl_zhifubao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.pay:
                if (payModepay.getText().toString().equals("账户余额")) {

                } else if (payModepay.getText().toString().equals("微信")) {
                    mPay_type = "1";
                    mPresenter.getData(ApiConfig.WXPAY, LoadConfig.NORMAL, mTask_id, mMoney, mPay_type, mMy_id);
                } else if (payModepay.getText().toString().equals("支付宝")) {
                    mPay_type = "2";
                    mPresenter.getData(ApiConfig.PAY, LoadConfig.NORMAL, mTask_id, mMoney, mPay_type, mMy_id);
                }
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

    private void aliPayData(final String orderInfo) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayCenterActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = Config.PAY_ZFB;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
