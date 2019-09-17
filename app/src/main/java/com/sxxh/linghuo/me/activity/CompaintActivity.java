package com.sxxh.linghuo.me.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.login.bean.AuthCodeBean;
import com.sxxh.linghuo.model.MeModel;

import butterknife.BindView;
import butterknife.OnClick;

public class CompaintActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.compaint)
    TextView compaint;
    @BindView(R.id.cb_fake)
    CheckBox cbFake;
    @BindView(R.id.cb_break)
    CheckBox cbBreak;
    @BindView(R.id.cb_harass)
    CheckBox cbHarass;
    @BindView(R.id.cb_menoy)
    CheckBox cbMenoy;
    @BindView(R.id.cb_other)
    CheckBox cbOther;
    @BindView(R.id.compaint_content)
    EditText compaintContent;
    private String mComplaint;
    private String mReportType = "";
    private int mUid = 0;
    private String mContent;
    private Intent mFeedbackSuccessIntent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_compaint;
    }

    @Override
    public void initView() {
        Intent mIntent = getIntent();
        mComplaint = mIntent.getStringExtra(Config.COMPLAINT);
        mUid = mIntent.getIntExtra(Config.USER_ID,0);
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
    public void onError(int whichApi, Throwable e) {
        Log.e("投诉", "onError: 投诉" + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.COMPAINT_FIRM:
                AuthCodeBean mCompaintFirm = (AuthCodeBean) t[0];
                mFeedbackSuccessIntent = new Intent(this, FeedbackSuccessActivity.class);
                mFeedbackSuccessIntent.putExtra(Config.SUCCESS, Config.FEEDBACK);
                startActivity(mFeedbackSuccessIntent);
                finish();
                break;
            case ApiConfig.COMPAINT_PEOPLE:
                AuthCodeBean mCompaintPeople = (AuthCodeBean) t[0];
                mFeedbackSuccessIntent = new Intent(this, FeedbackSuccessActivity.class);
                mFeedbackSuccessIntent.putExtra(Config.SUCCESS, Config.FEEDBACK);
                startActivity(mFeedbackSuccessIntent);
                finish();
                break;
        }
    }

    @OnClick({R.id.back, R.id.compaint})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.compaint:
                mReportType = "";
                if (cbBreak.isChecked()) {
                    if (!mReportType.equals("")) {
                        mReportType = mReportType + "," + cbBreak.getText().toString();
                    } else {
                        mReportType = cbBreak.getText().toString();
                    }
                }
                if (cbFake.isChecked()) {
                    if (!mReportType.equals("")) {
                        mReportType = mReportType + "," + cbFake.getText().toString();
                    } else {
                        mReportType = cbFake.getText().toString();
                    }
                }
                if (cbHarass.isChecked()) {
                    if (!mReportType.equals("")) {
                        mReportType = mReportType + "," + cbHarass.getText().toString();
                    } else {
                        mReportType = cbHarass.getText().toString();
                    }
                }
                if (cbMenoy.isChecked()) {
                    if (!mReportType.equals("")) {
                        mReportType = mReportType + "," + cbMenoy.getText().toString();
                    } else {
                        mReportType = cbMenoy.getText().toString();
                    }
                }
                if (cbOther.isChecked()) {
                    if (!mReportType.equals("")) {
                        mReportType = mReportType + "," + cbOther.getText().toString();
                    } else {
                        mReportType = cbOther.getText().toString();
                    }
                }
                mContent = compaintContent.getText().toString();
                if (mReportType.equals("") && mContent.equals("")) {
                    ToastUtils.showShort("请填写完整信息！");
                } else {
                    if (mComplaint.equals("1")) {
                        mPresenter.getData(ApiConfig.COMPAINT_FIRM, LoadConfig.NORMAL, mUid, mReportType, mContent);
                    } else if (mComplaint.equals("2")) {
                        mPresenter.getData(ApiConfig.COMPAINT_PEOPLE, LoadConfig.NORMAL, mUid, mReportType, mContent);
                    }
                }
                break;
        }
    }
}
