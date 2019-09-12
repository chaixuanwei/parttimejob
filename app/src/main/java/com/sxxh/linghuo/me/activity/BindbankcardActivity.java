package com.sxxh.linghuo.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.model.MeModel;

import butterknife.BindView;
import butterknife.OnClick;

public class BindbankcardActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_idnumber)
    EditText etIdnumber;
    @BindView(R.id.et_idcard)
    EditText etIdcard;
    @BindView(R.id.et_photo)
    EditText etPhoto;
    @BindView(R.id.rb_agree)
    RadioButton rbAgree;
    @BindView(R.id.sure_bind)
    TextView sureBind;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bindbankcard;
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
    public MeModel getModel() {
        return new MeModel();
    }

    @Override
    public void onError(int whichApi,Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick({R.id.back, R.id.sure_bind})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.sure_bind:
                String mName = etName.getText().toString();
                String mIdnumber = etIdnumber.getText().toString();
                String mIdcard = etIdcard.getText().toString();
                String mPhoto = etPhoto.getText().toString();
                if (!mName.equals("") && !mIdcard.equals("") && !mIdnumber.equals("") && !mPhoto.equals("")) {
                    Intent mIntent = getIntent();
                    Bundle mBundle = new Bundle();
                    mBundle.putString("name", mName);
                    mBundle.putString("idnumber", mIdnumber);
                    mBundle.putString("idcard", mIdcard);
                    mBundle.putString("idphoto", mPhoto);
                    mIntent.putExtra("bundle",mBundle);
                    setResult(Config.FINISH, mIntent);
                    finish();
                } else {
                    ToastUtils.showShort("请输入完整信息");
                }
                break;
        }
    }
}
