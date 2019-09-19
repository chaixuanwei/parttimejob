package com.sxxh.linghuo.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.app.AuthTask;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.switfpass.pay.utils.Constants;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.activity.MainActivity;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.local_utils.SharedPrefrenceUtils;
import com.sxxh.linghuo.login.bean.LoginBean;
import com.sxxh.linghuo.login.bean.WBTokenBean;
import com.sxxh.linghuo.login.bean.WXLoginBean;
import com.sxxh.linghuo.login.bean.WXTokenBean;
import com.sxxh.linghuo.login.bean.ZFBLoginBean;
import com.sxxh.linghuo.login.bean.ZFBTokenBean;
import com.sxxh.linghuo.model.LoginModel;
import com.sxxh.linghuo.zhifubao.AuthResult;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpActivity<CommonPresenter, LoginModel> {

    private static final String TAG = "LoginActivity";
    @BindView(R.id.user_phone)
    EditText userPhone;
    @BindView(R.id.user_password)
    EditText userPassword;
    @BindView(R.id.forget_password)
    TextView forgetPassword;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.bt_login)
    TextView btLogin;
    @BindView(R.id.weibo)
    ImageView weibo;
    @BindView(R.id.weixin)
    ImageView weixin;
    @BindView(R.id.zhifubao)
    ImageView zhifubao;
    private IWXAPI api;
    private static final int SDK_ZFB_LOGIN = 2;
    private static final String APP_ID = "wx09fddc4711f09625";
    Boolean isWx = false;
    Boolean isWb = false;
    private SsoHandler mSsoHandler;
    private Oauth2AccessToken mAccessToken;
    private String mWBOpenId = "";

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_ZFB_LOGIN: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    String mAuthCode = authResult.getAuthCode();
                    mPresenter.getData(ApiConfig.ZFB_LOGIN, LoadConfig.NORMAL, mAuthCode, "login");
                    break;
                }
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WbSdk.install(this, new AuthInfo(this, Config.APP_ID_WB, Config.REDIRECT_URL, ""));
        mSsoHandler = new SsoHandler(LoginActivity.this);
        regToWx();
    }

    private void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, Config.APP_ID_WX, true);

        // 将应用的appId注册到微信
        api.registerApp(Config.APP_ID_WX);

        //建议动态监听微信启动广播进行注册到微信
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // 将该app注册到微信
                api.registerApp(Constants.APP_ID);
            }
        }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));
    }

    @Override
    public void initView() {
        register.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        register.setTextColor(getResources().getColor(R.color.app_theme_color));
        userPhone.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    @Override
    public void initData() {

    }

    @Override
    public CommonPresenter getPresenter() {
        return new CommonPresenter();
    }

    @Override
    public LoginModel getModel() {
        return new LoginModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        ToastUtils.showShort("登录失败");
        Log.e(TAG, "onError: " + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GET_LOGIN:
                LoginBean mLoginBeans = (LoginBean) t[0];
                SharedPrefrenceUtils.saveString(LoginActivity.this, Config.TOKEN, mLoginBeans.getData().getToken());
                String mString = SharedPrefrenceUtils.getString(this, Config.TOKEN);
                if (mLoginBeans.getCode() == 1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                    });
                } else {
                    ToastUtils.showShort(mLoginBeans.getMsg());
                }
                break;
            case ApiConfig.GET_ZFB_LOGIN:
                ZFBLoginBean mZFBLoginBeans = (ZFBLoginBean) t[0];
                final String authInfo = mZFBLoginBeans.getData().getUrl();
                Runnable authRunnable = new Runnable() {

                    @Override
                    public void run() {
                        // 构造AuthTask 对象
                        AuthTask authTask = new AuthTask(LoginActivity.this);
                        // 调用授权接口，获取授权结果
                        Map<String, String> result = authTask.authV2(authInfo, true);

                        Message msg = new Message();
                        msg.what = SDK_ZFB_LOGIN;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };

                // 必须异步调用
                Thread authThread = new Thread(authRunnable);
                authThread.start();
                break;
            case ApiConfig.GET_WX_LOGIN:
                WXLoginBean mWXLoginBeans = (WXLoginBean) t[0];
                WXLogin();
                break;
            case ApiConfig.ZFB_LOGIN:
                ZFBTokenBean mZFBTokenBeans = (ZFBTokenBean) t[0];
                Log.e(TAG, "onResponse: " + mZFBTokenBeans);
                if (!TextUtils.isEmpty(mZFBTokenBeans.getData().toString())) {
                    Gson mGson = new Gson();
                    String mZFBDatas = mGson.toJson(mZFBTokenBeans.getData());
                    final ZFBTokenBean.DataBean mZFBData = mGson.fromJson(mZFBDatas, ZFBTokenBean.DataBean.class);
                    if (!mZFBData.getToken().equals("")) {
                        SharedPrefrenceUtils.saveString(LoginActivity.this, Config.TOKEN, mZFBData.getToken());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent mIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                                String mAlipay_user_id = mZFBData.getAlipay_user_id();
                                mIntent.putExtra("id", mAlipay_user_id);
                                startActivity(mIntent);
                            }
                        });
                    }
                }
                break;
            case ApiConfig.WX_LOGIN:
                WXTokenBean mWXTokenBean = (WXTokenBean) t[0];
                if (!TextUtils.isEmpty(mWXTokenBean.getData().toString())) {
                    Gson mGson = new Gson();
                    String mWXDatas = mGson.toJson(mWXTokenBean.getData());
                    final WXTokenBean.DataBean mWXData = mGson.fromJson(mWXDatas, WXTokenBean.DataBean.class);
                    if (!mWXData.getToken().equals("")) {
                        SharedPrefrenceUtils.saveString(LoginActivity.this, Config.TOKEN, mWXData.getToken());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent mIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                                String mWx_user_id = mWXData.getWx_user_id();
                                mIntent.putExtra("id", mWx_user_id);
                                startActivity(mIntent);
                            }
                        });
                    }
                }
                break;
            case ApiConfig.WB_LOGIN:
                WBTokenBean mWBTokenBeans = (WBTokenBean) t[0];
                if (!TextUtils.isEmpty(mWBTokenBeans.getData().toString())) {
                    Gson mGson = new Gson();
                    String mWBDatas = mGson.toJson(mWBTokenBeans.getData());
                    final WBTokenBean.DataBean mWBData = mGson.fromJson(mWBDatas, WBTokenBean.DataBean.class);
                    if (!mWBData.getToken().equals("")) {
                        SharedPrefrenceUtils.saveString(LoginActivity.this, Config.TOKEN, mWBData.getToken());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent mIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                                String mWb_user_id = mWBData.getSina_user_id();
                                mIntent.putExtra("id", mWb_user_id);
                                startActivity(mIntent);
                            }
                        });
                    }
                }
                break;
        }
    }

    @OnClick({R.id.forget_password, R.id.bt_login, R.id.register, R.id.weibo, R.id.weixin, R.id.zhifubao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forget_password:
                startActivity(new Intent(this, FindPasswordActivity.class));
                break;
            case R.id.bt_login:
                if (!userPhone.getText().toString().equals("") && !userPassword.getText().toString().equals("")) {
                    mPresenter.getData(ApiConfig.GET_LOGIN, LoadConfig.NORMAL, userPhone.getText().toString(), userPassword.getText().toString());
                } else {
                    ToastUtils.showShort("请填写信息！");
                }
                break;
            case R.id.register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.weibo:
                isWb = true;
                loginToSina();
                break;
            case R.id.weixin:
                isWx = true;
                mPresenter.getData(ApiConfig.GET_WX_LOGIN, LoadConfig.NORMAL);
                break;
            case R.id.zhifubao:
                mPresenter.getData(ApiConfig.GET_ZFB_LOGIN, LoadConfig.NORMAL);
                break;
        }
    }

    //吊起新浪微博客户端授权，如果未安装这使用web授权
    private void loginToSina() {
        mSsoHandler.authorize(new SelfWbAuthListener());
    }

    private class SelfWbAuthListener implements com.sina.weibo.sdk.auth.WbAuthListener {
        @Override
        public void onSuccess(final Oauth2AccessToken token) {
            LoginActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAccessToken = token;
                    if (mAccessToken.isSessionValid()) {
                        mWBOpenId = mAccessToken.getUid();
                        AccessTokenKeeper.writeAccessToken(LoginActivity.this, mAccessToken);
                        ToastUtils.showShort("授权成功");
                    }
                }
            });
        }

        @Override
        public void cancel() {
            ToastUtils.showShort("取消授权");
        }

        @Override
        public void onFailure(WbConnectErrorMessage errorMessage) {
            ToastUtils.showShort(errorMessage.getErrorMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    /**
     * 登录微信
     */
    private void WXLogin() {
        if (!api.isWXAppInstalled()) {
            ToastUtils.showShort("您的设备未安装微信客户端");
        } else {
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            api.sendReq(req);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String mOpenId = SharedPrefrenceUtils.getString(LoginActivity.this, Config.OPENID);
        String mNickName = SharedPrefrenceUtils.getString(LoginActivity.this, Config.NICKNAME, "");
        String mSex = SharedPrefrenceUtils.getString(LoginActivity.this, Config.SEX, "");
        String mCity = SharedPrefrenceUtils.getString(LoginActivity.this, Config.CITY, "");
        String mProvince = SharedPrefrenceUtils.getString(LoginActivity.this, Config.PROVINCE, "");
        String mCountry = SharedPrefrenceUtils.getString(LoginActivity.this, Config.COUNTRY, "");
        String mHeadimgurl = SharedPrefrenceUtils.getString(LoginActivity.this, Config.HEADIMGURL, "");
        if (!mOpenId.equals("") && isWx) {
            isWx = false;
            mPresenter.getData(ApiConfig.WX_LOGIN, LoadConfig.NORMAL, mNickName, mOpenId, mHeadimgurl, mProvince, mCity, "login");
        }
        if (!mWBOpenId.equals("") && isWb) {
            isWb = false;
            mPresenter.getData(ApiConfig.WB_LOGIN, LoadConfig.NORMAL, "", mWBOpenId, "", "", "", "login");
        }
    }
}
