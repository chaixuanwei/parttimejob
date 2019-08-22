package com.example.myapplication.login;

import android.content.Intent;
import android.graphics.Paint;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.model.LoginModel;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpActivity<CommonPresenter, LoginModel> {

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
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick({R.id.forget_password, R.id.bt_login, R.id.register, R.id.weibo, R.id.weixin, R.id.zhifubao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forget_password:
                startActivity(new Intent(this,FindPasswordActivity.class));
                finish();
                break;
            case R.id.bt_login:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.register:
                startActivity(new Intent(this,RegisterActivity.class));
                finish();
                break;
            case R.id.weibo:
                break;
            case R.id.weixin:
                break;
            case R.id.zhifubao:
                break;
        }
    }
}
