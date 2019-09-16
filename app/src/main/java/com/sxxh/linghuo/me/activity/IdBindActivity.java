package com.sxxh.linghuo.me.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.AuthTask;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.switfpass.pay.utils.Constants;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.activity.MainActivity;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.local_utils.SharedPrefrenceUtils;
import com.sxxh.linghuo.login.LoginActivity;
import com.sxxh.linghuo.login.RegisterActivity;
import com.sxxh.linghuo.login.bean.WXLoginBean;
import com.sxxh.linghuo.login.bean.WXTokenBean;
import com.sxxh.linghuo.login.bean.ZFBLoginBean;
import com.sxxh.linghuo.login.bean.ZFBTokenBean;
import com.sxxh.linghuo.me.bean.IdBindBean;
import com.sxxh.linghuo.model.MeModel;
import com.sxxh.linghuo.zhifubao.AuthResult;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

import butterknife.BindView;
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
    @BindView(R.id.wx_ll)
    LinearLayout wxLl;
    @BindView(R.id.wb_ll)
    LinearLayout wbLl;
    @BindView(R.id.zfb_ll)
    LinearLayout zfbLl;
    @BindView(R.id.wx_bind)
    TextView wxBind;
    @BindView(R.id.wb_bind)
    TextView wbBind;
    @BindView(R.id.zfb_bind)
    TextView zfbBind;
    private static final int SDK_AUTH_FLAG = 2;
    private IWXAPI api;
    private static final String APP_ID = "wx09fddc4711f09625";
    Boolean isWx = false;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    String mAuthCode = authResult.getAuthCode();
                    mPresenter.getData(ApiConfig.ZFB_LOGIN, LoadConfig.NORMAL, mAuthCode, "binding");
                    break;
                }
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_id_bind;
    }

    @Override
    public void initView() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        regToWx();
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.ID_BIND, LoadConfig.NORMAL);
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
        Log.e("账号绑定", "onError: 账号绑定" );
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.ID_BIND:
                IdBindBean mIdBindBeans = (IdBindBean) t[0];
                IdBindBean.DataBean mData = mIdBindBeans.getData();
                if (mData.getWx().getBinding() == 1) {
                    wxLl.setVisibility(View.GONE);
                } else {
                    Glide.with(this).load(R.mipmap.bind_weixin).into(nowImgPhoto);
                    nowTxtName.setText("微信");
                    nowTxtId.setText(mData.getWx().getNick_name());
                }
                if (mData.getAlipay().getBinding() == 1) {
                    zfbLl.setVisibility(View.GONE);
                } else {
                    Glide.with(this).load(R.mipmap.bind_zhifubao).into(nowImgPhoto);
                    nowTxtName.setText("支付宝");
                    nowTxtId.setText(mData.getAlipay().getNick_name());
                }
                if (mData.getSina_blog().getBinding() == 1) {
                    wbLl.setVisibility(View.GONE);
                } else {
                    Glide.with(this).load(R.mipmap.bind_weibo).into(nowImgPhoto);
                    nowTxtName.setText("微博");
                    nowTxtId.setText(mData.getSina_blog().getNick_name());
                }
                if (mData.getSina_blog().getBinding() == 0 && mData.getAlipay().getBinding() == 0 && mData.getWx().getBinding() == 0) {
                    nowLogin.setVisibility(View.GONE);
                }
                break;
            case ApiConfig.GET_ZFB_LOGIN:
                ZFBLoginBean mZFBLoginBeans = (ZFBLoginBean) t[0];
                final String authInfo = mZFBLoginBeans.getData().getUrl();
                Runnable authRunnable = new Runnable() {

                    @Override
                    public void run() {
                        // 构造AuthTask 对象
                        AuthTask authTask = new AuthTask(IdBindActivity.this);
                        // 调用授权接口，获取授权结果
                        Map<String, String> result = authTask.authV2(authInfo, true);

                        Message msg = new Message();
                        msg.what = SDK_AUTH_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };

                // 必须异步调用
                Thread authThread = new Thread(authRunnable);
                authThread.start();
                break;
            case ApiConfig.ZFB_LOGIN:
                ZFBTokenBean mZFBTokenBeans = (ZFBTokenBean) t[0];
                if (!TextUtils.isEmpty(mZFBTokenBeans.getData().toString())) {
                    Gson mGson = new Gson();
                    String mZFBDatas = mGson.toJson(mZFBTokenBeans.getData());
                    final ZFBTokenBean.DataBean mZFBData = mGson.fromJson(mZFBDatas, ZFBTokenBean.DataBean.class);
                    ToastUtils.showShort(mZFBTokenBeans.getMsg());
                    SharedPrefrenceUtils.saveString(IdBindActivity.this, Config.TOKEN, mZFBData.getToken());
                } else {
                    ToastUtils.showShort(mZFBTokenBeans.getMsg());
                }
                break;
            case ApiConfig.GET_WX_LOGIN:
                WXLoginBean mWXLoginBeans = (WXLoginBean) t[0];
                WXLogin();
                break;
            case ApiConfig.WX_LOGIN:
                WXTokenBean mWXTokenBean = (WXTokenBean) t[0];
                if (!TextUtils.isEmpty(mWXTokenBean.getData().toString())) {
                    Gson mGson = new Gson();
                    String mWXDatas = mGson.toJson(mWXTokenBean.getData());
                    WXTokenBean.DataBean mDataBean = mGson.fromJson(mWXDatas, WXTokenBean.DataBean.class);

                }
                break;
        }
    }

    /**
     * 登录微信
     */
    private void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);

        // 将应用的appId注册到微信
        api.registerApp(APP_ID);

        //建议动态监听微信启动广播进行注册到微信
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // 将该app注册到微信
                api.registerApp(Constants.APP_ID);
            }
        }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));
    }

    private void WXLogin() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        api.sendReq(req);
    }

    @OnClick({R.id.back, R.id.wx_bind, R.id.wb_bind, R.id.zfb_bind})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.wx_bind:
                isWx = true;
                mPresenter.getData(ApiConfig.GET_WX_LOGIN, LoadConfig.NORMAL);
                break;
            case R.id.wb_bind:
                break;
            case R.id.zfb_bind:
                mPresenter.getData(ApiConfig.GET_ZFB_LOGIN, LoadConfig.NORMAL);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String mOpenId = SharedPrefrenceUtils.getString(IdBindActivity.this, Config.OPENID);
        String mNickName = SharedPrefrenceUtils.getString(IdBindActivity.this, Config.NICKNAME,"");
        String mSex = SharedPrefrenceUtils.getString(IdBindActivity.this, Config.SEX,"");
        String mCity = SharedPrefrenceUtils.getString(IdBindActivity.this, Config.CITY,"");
        String mProvince = SharedPrefrenceUtils.getString(IdBindActivity.this, Config.PROVINCE,"");
        String mCountry = SharedPrefrenceUtils.getString(IdBindActivity.this, Config.COUNTRY,"");
        String mHeadimgurl = SharedPrefrenceUtils.getString(IdBindActivity.this, Config.HEADIMGURL,"");
        if (!mOpenId.equals("") && isWx) {
            isWx = false;
            mPresenter.getData(ApiConfig.WX_LOGIN, LoadConfig.NORMAL, mNickName, mOpenId, mHeadimgurl, mProvince, mCity, "binding");
        }
    }
}
