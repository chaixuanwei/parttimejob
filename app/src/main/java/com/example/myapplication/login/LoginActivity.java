package com.example.myapplication.login;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.app.AuthTask;
import com.blankj.utilcode.util.ToastUtils;
import com.example.myapplication.R;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.config.ApiConfig;
import com.example.myapplication.config.Config;
import com.example.myapplication.config.LoadConfig;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.local_utils.SharedPrefrenceUtils;
import com.example.myapplication.login.bean.LoginBean;
import com.example.myapplication.login.bean.ZFBLoginBean;
import com.example.myapplication.login.bean.ZFBTokenBean;
import com.example.myapplication.model.LoginModel;
import com.example.myapplication.zhifubao.AuthResult;

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
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    String mAuthCode = authResult.getAuthCode();
                    mPresenter.getData(ApiConfig.ZFB_LOGIN, LoadConfig.NORMAL, mAuthCode);
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
            case ApiConfig.GET_Login:
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
                final ZFBTokenBean.DataBean mData = mZFBTokenBeans.getData();
                if (!mData.getToken().equals("")) {
                    SharedPrefrenceUtils.saveString(LoginActivity.this,Config.TOKEN,mData.getToken());
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
                            String mAlipay_user_id = mData.getAlipay_user_id();
                            mIntent.putExtra("id",mAlipay_user_id);
                            startActivity(mIntent);
                        }
                    });
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
                    mPresenter.getData(ApiConfig.GET_Login, LoadConfig.NORMAL, userPhone.getText().toString(), userPassword.getText().toString());
                } else {
                    ToastUtils.showShort("请填写信息！");
                }
                break;
            case R.id.register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.weibo:
                break;
            case R.id.weixin:
                break;
            case R.id.zhifubao:
                mPresenter.getData(ApiConfig.GET_ZFB_LOGIN, LoadConfig.NORMAL);
                break;
        }
    }
}
