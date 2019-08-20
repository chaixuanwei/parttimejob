package com.example.myapplication.me.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpFragment;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.me.adapter.ProjectExperienceAdapter;
import com.example.myapplication.model.MeModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectExperienceFragment extends BaseMvpFragment<CommonPresenter, MeModel> {

    static ProjectExperienceFragment fragment;
    @BindView(R.id.project_experience_rv)
    RecyclerView projectExperienceRv;
    Unbinder unbinder;

    public static ProjectExperienceFragment newInstance() {
        if (fragment == null) fragment = new ProjectExperienceFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_project_experience;
    }

    @Override
    public void initView() {
        ProjectExperienceAdapter mAdapter = new ProjectExperienceAdapter(getActivity());
        projectExperienceRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        projectExperienceRv.setAdapter(mAdapter);
        projectExperienceRv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
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
    public void onError(Throwable e) {

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
