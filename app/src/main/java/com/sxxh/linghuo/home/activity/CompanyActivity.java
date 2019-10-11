package com.sxxh.linghuo.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.home.adapter.HomeVpAdapter;
import com.sxxh.linghuo.home.bean.IssusMessageBean;
import com.sxxh.linghuo.home.fragment.CompanyAppraiseFragment;
import com.sxxh.linghuo.home.fragment.CompanyHistoryFragment;
import com.sxxh.linghuo.home.fragment.CompanyIntroFragment;
import com.sxxh.linghuo.model.HomeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompanyActivity extends BaseMvpActivity<CommonPresenter, HomeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.rb_normal)
    RatingBar rbNormal;
    @BindView(R.id.company_people)
    TextView companyPeople;
    @BindView(R.id.company_credit)
    TextView companyCredit;
    @BindView(R.id.company_complain)
    TextView companyComplain;
    @BindView(R.id.company_tab)
    TabLayout companyTab;
    @BindView(R.id.company_vp)
    ViewPager companyVp;
    List<String> mTitleList = new ArrayList<>();
    List<Fragment> mFragmentList = new ArrayList<>();
    @BindView(R.id.company_head)
    ImageView companyHead;
    @BindView(R.id.company_name)
    TextView companyName;
    private HomeVpAdapter mAdapter;
    private int uId = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_company;
    }

    @Override
    public void initView() {
        Intent mIntent = getIntent();
        uId = mIntent.getIntExtra(Config.TASK_ID, uId);
        mAdapter = new HomeVpAdapter(getSupportFragmentManager(), mFragmentList, mTitleList);
        companyVp.setAdapter(mAdapter);
        companyTab.setupWithViewPager(companyVp);
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.ISSUS_MESSAGE, LoadConfig.NORMAL, uId);
        if (mTitleList.size() == 0 && mFragmentList.size() == 0) {
            mTitleList.add("整体评价");
            mFragmentList.add(CompanyAppraiseFragment.newInstance());
            mTitleList.add("任务历史");
            mFragmentList.add(CompanyHistoryFragment.newInstance());
            mTitleList.add("公司简介");
            mFragmentList.add(CompanyIntroFragment.newInstance());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public CommonPresenter getPresenter() {
        return new CommonPresenter();
    }

    @Override
    public HomeModel getModel() {
        return new HomeModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Log.e("发布人", "onError: " + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.ISSUS_MESSAGE:
                final IssusMessageBean mIssusMessageBeans = (IssusMessageBean) t[0];
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        IssusMessageBean.DataBean mData = mIssusMessageBeans.getData();
                        if (mData.getInfo().getCompany_name() == null || mData.getInfo().getCompany_name().equals("")) {

                        } else {
                            companyName.setText(mData.getInfo().getCompany_name());
                        }
                        if (mData.getInfo().getAvatar() == null || mData.getInfo().getAvatar().equals("")) {

                        } else {
                            Glide.with(CompanyActivity.this).load(mData.getInfo().getAvatar()).into(companyHead);
                        }
                        companyPeople.setText(mData.getSum_job() + "");
                        companyCredit.setText(mData.getCredit_point() + "");
                        companyComplain.setText(mData.getCount() + "");
                    }
                });
                break;
        }
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
