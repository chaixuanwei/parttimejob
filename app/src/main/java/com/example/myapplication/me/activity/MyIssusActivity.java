package com.example.myapplication.me.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.me.fragment.OrderFragment;
import com.example.myapplication.me.fragment.YetOrderFragment;
import com.example.myapplication.message.adapter.MessageVpAdapter;
import com.example.myapplication.model.MeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyIssusActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.myissus_table)
    TabLayout myissusTable;
    @BindView(R.id.myissus_vp)
    ViewPager myissusVp;
    List<String> mTitleList = new ArrayList<>();
    List<Fragment> mFragmentList = new ArrayList<>();
    @BindView(R.id.back)
    ImageView back;
    private MessageVpAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_issus;
    }

    @Override
    public void initView() {
        mAdapter = new MessageVpAdapter(getSupportFragmentManager(), mFragmentList, mTitleList);
        myissusVp.setAdapter(mAdapter);
        myissusTable.setupWithViewPager(myissusVp);
    }

    @Override
    public void initData() {
        if (mTitleList.size() == 0 && mFragmentList.size() == 0) {
            mTitleList.add("未接单");
            mFragmentList.add(OrderFragment.newInstance());
            mTitleList.add("已接单");
            mFragmentList.add(YetOrderFragment.newInstance());
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
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
