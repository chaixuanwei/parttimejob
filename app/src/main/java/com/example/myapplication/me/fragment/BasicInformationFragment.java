package com.example.myapplication.me.fragment;


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
import com.example.myapplication.me.adapter.ImageViewAdapter;
import com.example.myapplication.model.MeModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class BasicInformationFragment extends BaseMvpFragment<CommonPresenter, MeModel> {
    static BasicInformationFragment fragment;
    @BindView(R.id.basic_information_rv)
    RecyclerView basicInformationRv;
    Unbinder unbinder;

    public static BasicInformationFragment newInstance() {
        if (fragment == null) fragment = new BasicInformationFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_basic_information;
    }

    @Override
    public void initView() {
        ImageViewAdapter mAdapter = new ImageViewAdapter(getActivity());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        basicInformationRv.setLayoutManager(mLayoutManager);
        basicInformationRv.setAdapter(mAdapter);
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

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
