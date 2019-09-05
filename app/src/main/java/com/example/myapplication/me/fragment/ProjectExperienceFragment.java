package com.example.myapplication.me.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.config.ApiConfig;
import com.example.myapplication.config.LoadConfig;
import com.example.myapplication.frame.BaseMvpFragment;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.me.adapter.ProjectExperienceAdapter;
import com.example.myapplication.me.bean.ProjectSufferBean;
import com.example.myapplication.model.MeModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectExperienceFragment extends BaseMvpFragment<CommonPresenter, MeModel> {

    static ProjectExperienceFragment fragment;
    @BindView(R.id.project_experience_rv)
    RecyclerView projectExperienceRv;
    @BindView(R.id.project_experience_srl)
    SmartRefreshLayout projectExperienceSrl;
    private int mUserId;
    int mPage = 1;
    ArrayList<ProjectSufferBean.DataBean> mList = new ArrayList<>();
    private ProjectExperienceAdapter mAdapter;

    public static ProjectExperienceFragment newInstance(int pUserId) {
        if (fragment == null) fragment = new ProjectExperienceFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("userId", pUserId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUserId = getArguments().getInt("userId");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_project_experience;
    }

    @Override
    public void initView() {
        mList.clear();
        initRecycleView(projectExperienceRv, projectExperienceSrl);
        mAdapter = new ProjectExperienceAdapter(getActivity(),mList);
        projectExperienceRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        projectExperienceRv.setAdapter(mAdapter);
        projectExperienceRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.PROJECT_SUFFER, LoadConfig.NORMAL, mUserId,mPage);
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
        Log.e("项目经验", "onError: " + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.PROJECT_SUFFER:
                ProjectSufferBean mProjectBeans = (ProjectSufferBean) t[0];
                int upordown = (int) t[1];
                if (upordown == LoadConfig.REFRESH) {
                    mList.clear();
                    projectExperienceSrl.finishRefresh();
                } else if (upordown == LoadConfig.LOADMORE) {
                    projectExperienceSrl.finishLoadMore();
                }
                mList.addAll(mProjectBeans.getData());
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void refresh() {
        mPage = 1;
        mPresenter.getData(ApiConfig.PROJECT_SUFFER, LoadConfig.REFRESH, mUserId,mPage);
    }

    @Override
    public void loadMore() {
        mPage += mPage;
        mPresenter.getData(ApiConfig.PROJECT_SUFFER, LoadConfig.LOADMORE, mUserId,mPage);
    }
}
