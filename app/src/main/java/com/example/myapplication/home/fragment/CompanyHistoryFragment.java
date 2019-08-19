package com.example.myapplication.home.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpFragment;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.home.adapter.CommpanyHistoryAdapter;
import com.example.myapplication.model.HomeModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyHistoryFragment extends BaseMvpFragment<CommonPresenter, HomeModel> {
    static CompanyHistoryFragment fragment;
    @BindView(R.id.item_company_rv)
    RecyclerView itemCompanyRv;
    Unbinder unbinder;

    public static CompanyHistoryFragment newInstance() {
        if (fragment == null) fragment = new CompanyHistoryFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_company_history;
    }

    @Override
    public void initView() {
//        CommpanyHistoryAdapter mAdapter = new CommpanyHistoryAdapter(getActivity());
//        itemCompanyRv.setLayoutManager(new LinearLayoutManager(getActivity()));
//        itemCompanyRv.setAdapter(mAdapter);
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
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }
}
