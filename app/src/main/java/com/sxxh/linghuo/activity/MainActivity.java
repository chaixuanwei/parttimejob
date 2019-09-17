package com.sxxh.linghuo.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;

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
        //安卓权限
        AndPermission.with(this).permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .requestCode(200)
                .callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        // 权限申请成功回调

                        // 这里的requestCode就是申请时设置的requestCode。
                        // 和onActivityResult()的requestCode一样，用来区分多个不同的请求。
                        if(requestCode == 200) {
                            // TODO ...
                        }
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                        // 权限申请失败回调
                        if(requestCode == 200) {
                        }
                    }
                }).start();
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
