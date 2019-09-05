package com.example.myapplication.me.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.config.ApiConfig;
import com.example.myapplication.config.Config;
import com.example.myapplication.config.LoadConfig;
import com.example.myapplication.frame.ApplicationJob;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.local_utils.SharedPrefrenceUtils;
import com.example.myapplication.me.adapter.PhotoAdapter;
import com.example.myapplication.me.adapter.WorkingAdapter;
import com.example.myapplication.me.bean.UploadTopBean;
import com.example.myapplication.me.bean.WorkingBean;
import com.example.myapplication.model.MeModel;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import razerdp.design.SlideFromBottomPopup;

import static com.example.myapplication.local_utils.NetHeaders.getAppVersionCode;

public class WorkingActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.working_rv)
    RecyclerView workingRv;
    @BindView(R.id.working_srl)
    SmartRefreshLayout workingSrl;
    private WorkingAdapter mAdapter;
    int mPage = 1;
    ArrayList<WorkingBean.DataBean> mList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_working;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.GET_WORKING_LIST, LoadConfig.NORMAL,mPage);
        initRecycleView(workingRv, workingSrl);
        mAdapter = new WorkingAdapter(this,mList);
        workingRv.setLayoutManager(new LinearLayoutManager(this));
        workingRv.setAdapter(mAdapter);
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
        Log.e("工作中", "onError: " + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GET_WORKING_LIST:
                WorkingBean mWorkingBeans = (WorkingBean) t[0];
                int upordown = (int) t[1];
                if (upordown == LoadConfig.REFRESH) {
                    mList.clear();
                    workingSrl.finishRefresh();
                } else if (upordown == LoadConfig.LOADMORE) {
                    workingSrl.finishLoadMore();
                }
                mList.addAll(mWorkingBeans.getData());
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    @Override
    public void refresh() {
        mPage = 1;
        mPresenter.getData(ApiConfig.GET_WORKING_LIST, LoadConfig.REFRESH, mPage);
    }

    @Override
    public void loadMore() {
        mPage += mPage;
        mPresenter.getData(ApiConfig.GET_WORKING_LIST, LoadConfig.LOADMORE, mPage);
    }
}
