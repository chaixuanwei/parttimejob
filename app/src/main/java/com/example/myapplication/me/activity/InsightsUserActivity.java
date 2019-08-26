package com.example.myapplication.me.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.me.fragment.BasicInformationFragment;
import com.example.myapplication.me.fragment.OrderFragment;
import com.example.myapplication.me.fragment.ProjectExperienceFragment;
import com.example.myapplication.me.fragment.YetOrderFragment;
import com.example.myapplication.message.adapter.MessageVpAdapter;
import com.example.myapplication.model.MeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    @Override
    public int getLayoutId() {
        return R.layout.activity_insights_user;
    }

    @Override
    public void initView() {
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
            mFragmentList.add(ProjectExperienceFragment.newInstance());
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

    }

    @OnClick({R.id.back, R.id.insights_user_refuse, R.id.insights_user_pass})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.insights_user_refuse:
                break;
            case R.id.insights_user_pass:
                break;
        }
    }
}
