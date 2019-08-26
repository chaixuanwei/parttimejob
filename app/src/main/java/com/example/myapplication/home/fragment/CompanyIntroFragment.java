package com.example.myapplication.home.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpFragment;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.model.HomeModel;

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
