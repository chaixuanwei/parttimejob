package com.sxxh.linghuo.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.login.bean.AuthCodeBean;
import com.sxxh.linghuo.me.fragment.BasicInformationFragment;
import com.sxxh.linghuo.me.fragment.ProjectExperienceFragment;
import com.sxxh.linghuo.message.adapter.MessageVpAdapter;
import com.sxxh.linghuo.model.MeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class InsightsUserActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.insights_user_tl)
    TabLayout insightsUserTl;
    @BindView(R.id.insights_user_vp)
    ViewPager insightsUserVp;
    @BindView(R.id.insights_user_refuse)
    TextView insightsUserRefuse;
    @BindView(R.id.insights_user_pass)
    TextView insightsUserPass;
    List<String> mTitleList = new ArrayList<>();
    List<Fragment> mFragmentList = new ArrayList<>();
    private MessageVpAdapter mAdapter;
    private int mTaskId;
    private int mUserId;
    private int status;

    @Override
    public int getLayoutId() {
        return R.layout.activity_insights_user;
    }

    @Override
    public void initView() {
        Intent mIntent = getIntent();
        Bundle mBundle = mIntent.getBundleExtra("bundle");
        mTaskId = mBundle.getInt("task_id");
        mUserId = mBundle.getInt("user_id");
        mAdapter = new MessageVpAdapter(getSupportFragmentManager(), mFragmentList, mTitleList);
        insightsUserVp.setAdapter(mAdapter);
        insightsUserTl.setupWithViewPager(insightsUserVp);
    }

    @Override
    public void initData() {
        if (mTitleList.size() == 0 && mFragmentList.size() == 0) {
            mTitleList.add("基础信息");
            mFragmentList.add(BasicInformationFragment.newInstance());
            mTitleList.add("项目经验");
            mFragmentList.add(ProjectExperienceFragment.newInstance(mUserId));
            mAdapter.notifyDataSetChanged();
        }
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
            case ApiConfig.APPLY_AUDIT:
                AuthCodeBean mAuthCodeBeans = (AuthCodeBean) t[0];
                ToastUtils.showShort(mAuthCodeBeans.getMsg());
                finish();
                break;
        }
    }

    @OnClick({R.id.back, R.id.insights_user_refuse, R.id.insights_user_pass})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.insights_user_refuse:
                status = 0;
                mPresenter.getData(ApiConfig.APPLY_AUDIT, LoadConfig.NORMAL, mTaskId, mUserId, status);
                break;
            case R.id.insights_user_pass:
                status = 1;
                mPresenter.getData(ApiConfig.APPLY_AUDIT, LoadConfig.NORMAL, mTaskId, mUserId, status);
                break;
        }
    }
}
