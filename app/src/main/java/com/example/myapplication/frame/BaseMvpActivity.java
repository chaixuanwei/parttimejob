package com.example.myapplication.frame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 任小龙 on 2019/4/1.
 */
public abstract class BaseMvpActivity<P extends BasePresenter, M> extends BaseActivity implements ICommonView {

    private Unbinder mBind;
    public P mPresenter;
    public M mModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mBind = ButterKnife.bind(this);
        mPresenter = getPresenter();
        mModel = getModel();
        if (mPresenter != null) mPresenter.attach(this, (ICommonModel) mModel);
        initView();
        initData();
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();

    public abstract P getPresenter();

    public abstract M getModel();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }

    public void netErrorToast(Throwable e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
