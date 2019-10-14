package com.sxxh.linghuo.me.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpFragment;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.me.adapter.ImageViewAdapter;
import com.sxxh.linghuo.me.bean.BasicInformationBean;
import com.sxxh.linghuo.model.MeModel;
import com.sxxh.linghuo.view.RoundImage;

import java.util.ArrayList;

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
    @BindView(R.id.avatar)
    RoundImage mAvatar;
    @BindView(R.id.user_nickname)
    TextView mUserNickname;
    @BindView(R.id.real_name)
    TextView mRealName;
    @BindView(R.id.sex)
    TextView mSex;
    @BindView(R.id.id_number)
    TextView mIdNumber;
    @BindView(R.id.user_email)
    TextView mUserEmail;
    @BindView(R.id.mobile)
    TextView mMobile;
    private int mUserId;
    ArrayList<BasicInformationBean.ProjectImagesBean> mList = new ArrayList<>();
    private ImageViewAdapter mAdapter;

    public static BasicInformationFragment newInstance(int pUserId) {
        if (fragment == null) fragment = new BasicInformationFragment();
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
        return R.layout.fragment_basic_information;
    }

    @Override
    public void initView() {
        mAdapter = new ImageViewAdapter(getActivity(), mList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        basicInformationRv.setLayoutManager(mLayoutManager);
        basicInformationRv.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.BASIC_INFORMATION, LoadConfig.NORMAL, mUserId);
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
        Log.e("基础信息", "onError: 基础信息");
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.BASIC_INFORMATION:
                BasicInformationBean mBasicInformationBeans = (BasicInformationBean) t[0];
                mRealName.setText(mBasicInformationBeans.getReal_name());
                String mNumber = mBasicInformationBeans.getId_number();
                String mIdnumber = mNumber.substring(0, 3) + "***" + mNumber.substring(7, 11);
                mIdNumber.setText(mIdnumber);
                int sex = mBasicInformationBeans.getSex();
                if (sex == 0) {
                    mSex.setText("保密");
                } else if (sex == 1) {
                    mSex.setText("男");
                } else if (sex == 2) {
                    mSex.setText("女");
                }
                mMobile.setText(mBasicInformationBeans.getMobile());
                mUserEmail.setText(mBasicInformationBeans.getUser_email());
                Glide.with(this).load(mBasicInformationBeans.getAvatar()).into(mAvatar);
                mUserNickname.setText(mBasicInformationBeans.getUser_nickname());
                mList.addAll(mBasicInformationBeans.getProject_images());
                mAdapter.notifyDataSetChanged();
                break;
        }
    }
}
