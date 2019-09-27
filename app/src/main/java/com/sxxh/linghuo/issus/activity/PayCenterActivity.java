package com.sxxh.linghuo.issus.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.ToastUtils;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.model.IssusModel;
import com.sxxh.linghuo.zhifubao.PayResult;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class PayCenterActivity extends BaseMvpActivity<CommonPresenter, IssusModel> {

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
    public void onError(int whichApi, Throwable e) {

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
//                Intent mIntent = new Intent(this, SubmitPayActivity.class);
//                startActivity(mIntent);
                if (payModepay.getText().toString().equals("账户余额")) {

                } else if (payModepay.getText().toString().equals("微信")) {
                    PayReq payReq = new PayReq();
                    payReq.appId = Config.APP_ID_WX; //应用ID
                    payReq.partnerId = ""; //商户号 即微信支付分配的商户号WeChatPayEntity .getPartnerid();
                    payReq.prepayId = ""; //预支付交易会话ID.getPrepayid();
                    payReq.packageValue = ""; //扩展字段.getPackageX();
                    payReq.nonceStr = ""; //随机字符串不长于32位。.getNoncestr();
                    payReq.timeStamp = "" ; //时间戳.getTimestamp();
                    payReq.sign = "";  //签名.getPaySign();
                    api.sendReq(payReq);
                } else if (payModepay.getText().toString().equals("支付宝")) {
                    mZFBPayInfo = null;
                    aliPayData(mZFBPayInfo);
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
