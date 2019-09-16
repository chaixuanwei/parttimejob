package com.sxxh.linghuo.login;

import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.login.bean.AuthCodeBean;
import com.sxxh.linghuo.model.LoginModel;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseMvpActivity<CommonPresenter, LoginModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.bt_register)
    TextView btRegister;
    @BindView(R.id.regist_phone)
    EditText registPhone;
    @BindView(R.id.regist_getcord)
    TextView registGetcord;
    @BindView(R.id.regist_code)
    EditText registCode;
    @BindView(R.id.regist_password)
    EditText registPassword;
    @BindView(R.id.regist_cb)
    CheckBox registCb;
    private String mAuthCode;
    private String mzfbId = "";
    private String mwxId = "";
    private CountDownTimer mCountDownTimer;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        Intent mIntent = getIntent();
        mzfbId = mIntent.getStringExtra("id");
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
        switch (whichApi) {
            case ApiConfig.GET_AUTH_CODE:
                Log.e("RegisterActivity", "onError: " + e.getMessage());
                break;
            case ApiConfig.REGIST:
                Log.e("RegisterActivity", "onError: " + e.getMessage());
                break;
        }
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GET_AUTH_CODE:
                AuthCodeBean mAuthCodeBeans = (AuthCodeBean) t[0];
                if (mAuthCodeBeans.getCode() == 1) {
                    mAuthCode = mAuthCodeBeans.getData();
                    ToastUtils.showShort(mAuthCodeBeans.getMsg());
                }
                break;
            case ApiConfig.REGIST:
                AuthCodeBean mRegister = (AuthCodeBean) t[0];
                String mMsg = mRegister.getMsg();
                if (mMsg.equals("注册成功") || mMsg.equals("账号已存在")) {
                    finish();
                } else {
                    ToastUtils.showShort(mMsg);
                }
                break;
        }
    }

    @OnClick({R.id.back, R.id.bt_register, R.id.regist_getcord})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.bt_register:
                if (!registPhone.getText().toString().equals("") && registPassword.getText().length() <16 && registPassword.getText().length()>6
                        && !registPhone.getText().toString().equals("") && registCb.isChecked()) {
                    if (registPassword.getText().toString().matches("[a-zA-Z0-9]{6,16}")) {
                        mPresenter.getData(ApiConfig.REGIST, LoadConfig.NORMAL, registPhone.getText().toString(), registPassword.getText().toString(), registCode.getText().toString(), "1", mzfbId, mwxId);
                    } else {
                        ToastUtils.showShort("请输入正确的密码格式");
                    }
                } else {
                    ToastUtils.showShort("请填写完整信息");
                }
                break;
            case R.id.regist_getcord:
                if (!registPhone.getText().toString().equals("")) {
                    mCountDownTimer = new CountDownTimer(180000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            registGetcord.setText("获取验证码("+(millisUntilFinished / 1000) + " s)");
                            registGetcord.setClickable(false);
                        }

                        @Override
                        public void onFinish() {
                            registGetcord.setText("获取验证码");
                            registGetcord.setClickable(false);
                        }
                    }.start();
                    mPresenter.getData(ApiConfig.GET_AUTH_CODE, LoadConfig.NORMAL, registPhone.getText().toString());
                } else {
                    ToastUtils.showShort("请输入手机号");
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        super.onDestroy();
    }
}
