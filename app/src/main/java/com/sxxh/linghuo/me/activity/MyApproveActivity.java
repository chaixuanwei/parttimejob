package com.sxxh.linghuo.me.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.login.bean.AuthCodeBean;
import com.sxxh.linghuo.model.MeModel;

import butterknife.BindView;
import butterknife.OnClick;

public class MyApproveActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.people)
    LinearLayout people;
    @BindView(R.id.firm)
    LinearLayout firm;
    @BindView(R.id.get_people_result)
    TextView getPeopleResult;
    @BindView(R.id.get_frim_result)
    TextView getFrimResult;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_approve;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.GET_PEOPLE, LoadConfig.NORMAL);
        mPresenter.getData(ApiConfig.GET_FIRM, LoadConfig.NORMAL);
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

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GET_PEOPLE:
                AuthCodeBean mPeopleResult = (AuthCodeBean) t[0];
                getPeopleResult.setText(mPeopleResult.getMsg());
                break;
            case ApiConfig.GET_FIRM:
                AuthCodeBean mFirmResult = (AuthCodeBean) t[0];
                getFrimResult.setText(mFirmResult.getMsg());
                break;
        }
    }

    @OnClick({R.id.back, R.id.people, R.id.firm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.people:
                String mPeopleResult = getPeopleResult.getText().toString();
                if (mPeopleResult.equals("未认证")) {
                    Intent mIntentPeople = new Intent(this, PeopleActivity.class);
                    startActivity(mIntentPeople);
                } else if (mPeopleResult.equals("审核中")) {
                    startActivity(new Intent(MyApproveActivity.this,WaitAuditActivity.class));
                    finish();
                } else {
                    ToastUtils.showShort("已认证");
                }

                break;
            case R.id.firm:
                String mFrimResult = getFrimResult.getText().toString();
                if (mFrimResult.equals("未认证")) {
                    Intent mIntentFirm = new Intent(this, FirmActivity.class);
                    startActivity(mIntentFirm);
                } else if (mFrimResult.equals("审核中")) {
                    startActivity(new Intent(MyApproveActivity.this,WaitAuditActivity.class));
                    finish();
                } else {
                    ToastUtils.showShort("已认证");
                }
                break;
        }
    }
}
