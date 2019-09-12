package com.sxxh.linghuo.home.fragment;


import android.support.v4.app.Fragment;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.frame.BaseMvpFragment;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.model.HomeModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyIntroFragment extends BaseMvpFragment<CommonPresenter, HomeModel> {
    static CompanyIntroFragment fragment;

    public static CompanyIntroFragment newInstance() {
        if (fragment == null) fragment = new CompanyIntroFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_company_intro;
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
    public HomeModel getModel() {
        return new HomeModel();
    }

    @Override
    public void onError(int whichApi,Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }
}
