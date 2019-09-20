package com.sxxh.linghuo.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.ToastUtils;
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
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity<CommonPresenter, HomeModel> implements MyBottomView.OnBottomClick {

    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    private MyBottomView mBottomView;
    private final int FRISTPAGE = 1;
    private final int ISSUS = 2;
    private final int MESSAGE = 3;
    private final int ME = 4;
    private FragmentManager mManager;
    private long touchTime = 0;

    //应用退出提醒
    @Override
    public void onBackPressed() {
        // 模板自动生成的，大概是说如果左侧抽屉栏被打开，按返回键的时候关闭抽屉栏而不是退出程序
        long currentTime = System.currentTimeMillis();
        if (currentTime - touchTime > 2000) {
            Snackbar.make(frameLayout, "再按一次退出程序", Snackbar.LENGTH_SHORT).show();
            touchTime = currentTime;
        } else {
            super.onBackPressed();
        }
    }

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
                        if (requestCode == 200) {
                            // TODO ...
                        }
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                        // 权限申请失败回调
                        if (requestCode == 200) {
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
    public void onError(int whichApi, Throwable e) {
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


    /*//应用退出提醒
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            dialog();
        }
        return false;
    }

    private void dialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("提示");
        dialog.setMessage("是否退出当前程序？");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.exit(0);
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ToastUtils.showShort("取消");
            }
        });
        if (dialog != null) {
            dialog.show();
        }
    }*/

}
