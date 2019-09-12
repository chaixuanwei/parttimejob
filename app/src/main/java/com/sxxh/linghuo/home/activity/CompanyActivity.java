package com.sxxh.linghuo.home.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.home.adapter.HomeVpAdapter;
import com.sxxh.linghuo.home.fragment.CompanyAppraiseFragment;
import com.sxxh.linghuo.home.fragment.CompanyHistoryFragment;
import com.sxxh.linghuo.home.fragment.CompanyIntroFragment;
import com.sxxh.linghuo.model.HomeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
    private HomeVpAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_company;
    }

    @Override
    public void initView() {
        mAdapter = new HomeVpAdapter(getSupportFragmentManager(), mFragmentList, mTitleList);
        companyVp.setAdapter(mAdapter);
        companyTab.setupWithViewPager(companyVp);
    }

    @Override
    public void initData() {
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
    public void onError(int whichApi,Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
