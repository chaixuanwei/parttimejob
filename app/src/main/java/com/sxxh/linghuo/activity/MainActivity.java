package com.sxxh.linghuo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.customs.MyBottomView;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.home.fragment.FirstFragment;
import com.sxxh.linghuo.local_utils.SharedPrefrenceUtils;
import com.sxxh.linghuo.login.LoginActivity;
import com.sxxh.linghuo.model.HomeModel;
import com.sxxh.linghuo.issus.fragment.IssusFragment;
import com.sxxh.linghuo.me.fragment.MeFragment;
import com.sxxh.linghuo.message.fragment.MessageFragment;

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
        mBottomView.setBottomBg(Color.WHITE);
        mBottomView.setBottomTextSize(this, 10f);
        mBottomView.setOnBottomClickListener(this);
        mManager = getSupportFragmentManager();
        showFragment(FRISTPAGE);
    }

    @Override
    public void initData() {
        String mToken = SharedPrefrenceUtils.getString(this, Config.TOKEN);
        if (mToken.equals("")) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
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
    public void onError(int whichApi,Throwable e) {
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
