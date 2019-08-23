package com.example.myapplication.home.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpFragment;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.home.adapter.CommpanyAppraiseAdapter;
import com.example.myapplication.local_utils.ConvertUtil;
import com.example.myapplication.model.HomeModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyAppraiseFragment extends BaseMvpFragment<CommonPresenter, HomeModel> {
    static CompanyAppraiseFragment fragment;
    @BindView(R.id.progress_appraise)
    ProgressBar progressAppraise;
    @BindView(R.id.num_appraise)
    TextView numAppraise;
    @BindView(R.id.progress_service)
    ProgressBar progressService;
    @BindView(R.id.num_service)
    TextView numService;
    @BindView(R.id.progress_efficiency)
    ProgressBar progressEfficiency;
    @BindView(R.id.num_efficiency)
    TextView numEfficiency;
    @BindView(R.id.company_appraise_rv)
    RecyclerView companyAppraiseRv;

    public static CompanyAppraiseFragment newInstance() {
        if (fragment == null) fragment = new CompanyAppraiseFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_company_appraise;
    }

    @Override
    public void initView() {
        numAppraise.setText(progressAppraise.getProgress()*0.5+"");
        numService.setText(progressService.getProgress()*0.5+"");
        numEfficiency.setText(progressEfficiency.getProgress()*0.5+"");
        companyAppraiseRv.setFocusable(false);
        CommpanyAppraiseAdapter mAdapter = new CommpanyAppraiseAdapter(getActivity());
        companyAppraiseRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        companyAppraiseRv.setAdapter(mAdapter);
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
