package com.example.myapplication.frame;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMvpFragment<P extends BasePresenter, M> extends BaseFragment implements ICommonView {

    private Unbinder mBind;
    public P mPresenter;
    public M mModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View inflate = inflater.inflate(getLayoutId(), null);
        mBind = ButterKnife.bind(this, inflate);
        initView();
        mPresenter = getPresenter();
        mModel = getModel();
        if (mPresenter != null && mModel != null)mPresenter.attach(this, (ICommonModel) mModel);
        initData();
        return inflate;
    }

    public void netErrorToast(Throwable e){
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();

    public abstract P getPresenter();

    public abstract M getModel();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
        if (null != mPresenter) {
            mPresenter.detach();
        }
    }
}
