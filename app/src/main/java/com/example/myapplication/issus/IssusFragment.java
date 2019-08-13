package com.example.myapplication.issus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpFragment;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.home.HomeModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class IssusFragment extends BaseMvpFragment<CommonPresenter, IssusModel> {
    static IssusFragment fragment;

    public static IssusFragment newInstance() {
        if (fragment == null) fragment = new IssusFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_issus;
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
    public IssusModel getModel() {
        return new IssusModel();
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }
}
