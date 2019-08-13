package com.example.myapplication;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.myapplication.customs.MyBottomView;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.home.FirstFragment;
import com.example.myapplication.home.HomeModel;
import com.example.myapplication.issus.IssusFragment;
import com.example.myapplication.local_utils.statusbar.StatusBarCompat;
import com.example.myapplication.me.MeFragment;
import com.example.myapplication.message.MessageFragment;

public class MainActivity extends BaseMvpActivity<CommonPresenter, HomeModel> implements MyBottomView.OnBottomClick {

    private MyBottomView mBottomView;
    private final int FRISTPAGE = 1;
    private final int ISSUS = 2;
    private final int MESSAGE = 3;
    private final int ME = 4;
    private FragmentManager mManager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mBottomView = findViewById(R.id.bottom_view);
        StatusBarCompat.setStatusBarColor(this,mAppColor);
        mBottomView.setBottomBg(Color.WHITE);
        mBottomView.setBottomTextSize(this, 10f);
        mBottomView.setOnBottomClickListener(this);
        mManager = getSupportFragmentManager();
        showFragment(FRISTPAGE);
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
        netErrorToast(e);
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @Override
    public void onFirstClick() {
        showFragment(FRISTPAGE);
    }

    @Override
    public void onSecondClick() {
        showFragment(ISSUS);
    }

    @Override
    public void onThirdClick() {
        showFragment(MESSAGE);
    }

    @Override
    public void onFourthClick() {
        showFragment(ME);
    }

    @Override
    public void onFifthClick() {

    }

    private void showFragment(int index) {
        FragmentTransaction fragmentTransaction = mManager.beginTransaction();
        switch (index) {
            case FRISTPAGE:
                fragmentTransaction.replace(R.id.frame_layout, FirstFragment.newInstance());
                break;
            case ISSUS:
                fragmentTransaction.replace(R.id.frame_layout, IssusFragment.newInstance());
                break;
            case MESSAGE:
                fragmentTransaction.replace(R.id.frame_layout, MessageFragment.newInstance());
                break;
            case ME:
                fragmentTransaction.replace(R.id.frame_layout, MeFragment.newInstance());
                break;
        }
        fragmentTransaction.commit();
    }
}
