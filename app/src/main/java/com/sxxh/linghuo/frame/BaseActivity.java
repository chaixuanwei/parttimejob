package com.sxxh.linghuo.frame;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.customs.LoadingDialogWithContent;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import static com.scwang.smartrefresh.layout.util.DensityUtil.px2dp;

public class BaseActivity extends AppCompatActivity {

    public ApplicationJob mApplication;
    public int mAppColor;
    private LoadingDialogWithContent mDialog;
    public LinearLayoutManager mManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (ApplicationJob) getApplication();
        Log.e("我是华丽丽的类名-----", this.getClass().getSimpleName());
        mAppColor = ContextCompat.getColor(this, R.color.app_theme_color);
//        StatusBarCompat.setTranslucent(getWindow(),true);
        mDialog = new LoadingDialogWithContent(this,getString(R.string.loading));
    }

    public void showLoadingDialog(){
        if (!mDialog.isShowing())mDialog.show();
    }

    public void hideLoadingDialog(){
        if (mDialog.isShowing())mDialog.dismiss();
    }

    public void showToast(String content) {
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String content) {
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void showLog(String content){
        Log.e("睚眦",content);
    }

    public void initRecycleView(RecyclerView recyclerView, RefreshLayout refreshLayout) {
        mManager = new LinearLayoutManager(mApplication);
        recyclerView.setLayoutManager(mManager);
        if (refreshLayout != null) {
            refreshLayout.setHeaderHeight(px2dp(120));
            refreshLayout.setFooterHeight(px2dp(100));
            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    refresh();
                }
            });
            refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    loadMore();
                }
            });
        }
    }

    public void refresh() {
    }

    public void loadMore() {
    }
}
