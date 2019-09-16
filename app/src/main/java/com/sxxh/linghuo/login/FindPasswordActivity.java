package com.sxxh.linghuo.login;

import android.os.CountDownTimer;
import android.view.View;
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

public class FindPasswordActivity extends BaseMvpActivity<CommonPresenter, LoginModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.bt_login)
    TextView btLogin;
    @BindView(R.id.find_user_phone)
    EditText findUserPhone;
    @BindView(R.id.find_code)
    EditText findCode;
    @BindView(R.id.getcord)
    TextView getcord;
    @BindView(R.id.find_password)
    EditText findPassword;
    private CountDownTimer mCountDownTimer;

    @Override
    public int getLayoutId() {
        return R.layout.activity_find_password;
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
    public LoginModel getModel() {
        return new LoginModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GET_FindPassword:
                AuthCodeBean mAuthCodeBeans = (AuthCodeBean) t[0];
                ToastUtils.showShort(mAuthCodeBeans.getMsg());
                break;
            case ApiConfig.GET_AUTH_CODE:
                AuthCodeBean mAuthCodeBean = (AuthCodeBean) t[0];
                if (mAuthCodeBean.getCode() == 1) {
                    ToastUtils.showShort(mAuthCodeBean.getMsg());
                    finish();
                }
                break;
        }
    }

    @OnClick({R.id.back, R.id.bt_login, R.id.getcord})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.bt_login:
                if (findPassword.getText().toString().equals("") || findCode.getText().toString().equals("") || findUserPhone.getText().toString().equals("")) {
                    ToastUtils.showShort("请补全信息");
                } else {
                    mPresenter.getData(ApiConfig.GET_FindPassword, LoadConfig.NORMAL, findUserPhone.getText().toString(), findPassword.getText().toString(), findCode.getText().toString());
                }
                break;
            case R.id.getcord:
                if (!findUserPhone.getText().toString().equals("")) {
                    mCountDownTimer = new CountDownTimer(180000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            getcord.setText("获取验证码("+(millisUntilFinished / 1000) + " s)");
                            getcord.setClickable(false);
                        }

                        @Override
                        public void onFinish() {
                            getcord.setText("获取验证码");
                            getcord.setClickable(false);
                        }
                    }.start();
                    mPresenter.getData(ApiConfig.GET_AUTH_CODE, LoadConfig.NORMAL, findUserPhone.getText().toString());
                } else {
                    ToastUtils.showShort("请输入手机号");
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        mCountDownTimer.cancel();
        super.onDestroy();
    }
}
