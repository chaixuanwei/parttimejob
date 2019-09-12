package com.sxxh.linghuo.me.activity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.AuthTask;
import com.bumptech.glide.Glide;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.local_utils.SharedPrefrenceUtils;
import com.sxxh.linghuo.login.bean.ZFBLoginBean;
import com.sxxh.linghuo.login.bean.ZFBTokenBean;
import com.sxxh.linghuo.me.bean.IdBindBean;
import com.sxxh.linghuo.model.MeModel;
import com.sxxh.linghuo.zhifubao.AuthResult;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class IdBindActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.now_img_photo)
    ImageView nowImgPhoto;
    @BindView(R.id.now_txt_name)
    TextView nowTxtName;
    @BindView(R.id.now_txt_id)
    TextView nowTxtId;
    @BindView(R.id.now_login)
    RelativeLayout nowLogin;
    @BindView(R.id.now_ll)
    LinearLayout nowLl;
    @BindView(R.id.grey_line)
    TextView greyLine;
    @BindView(R.id.wx_ll)
    LinearLayout wxLl;
    @BindView(R.id.wb_ll)
    LinearLayout wbLl;
    @BindView(R.id.zfb_ll)
    LinearLayout zfbLl;
    @BindView(R.id.wx_bind)
    TextView wxBind;
    @BindView(R.id.wb_bind)
    TextView wbBind;
    @BindView(R.id.zfb_bind)
    TextView zfbBind;
    private static final int SDK_AUTH_FLAG = 2;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    String mAuthCode = authResult.getAuthCode();
                    mPresenter.getData(ApiConfig.ZFB_LOGIN, LoadConfig.NORMAL, mAuthCode, "binding");
                    break;
                }
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_id_bind;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.ID_BIND, LoadConfig.NORMAL);
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
        Log.e("账号绑定", "onError: 账号绑定" );
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.ID_BIND:
                IdBindBean mIdBindBeans = (IdBindBean) t[0];
                IdBindBean.DataBean mData = mIdBindBeans.getData();
                if (mData.getWx().getBinding() == 1) {
                    wxLl.setVisibility(View.GONE);
                } else {
                    Glide.with(this).load(R.mipmap.bind_weixin).into(nowImgPhoto);
                    nowTxtName.setText("微信");
                    nowTxtId.setText(mData.getWx().getNick_name());
                }
                if (mData.getAlipay().getBinding() == 1) {
                    zfbLl.setVisibility(View.GONE);
                } else {
                    Glide.with(this).load(R.mipmap.bind_zhifubao).into(nowImgPhoto);
                    nowTxtName.setText("支付宝");
                    nowTxtId.setText(mData.getAlipay().getNick_name());
                }
                if (mData.getSina_blog().getBinding() == 1) {
                    wbLl.setVisibility(View.GONE);
                } else {
                    Glide.with(this).load(R.mipmap.bind_weibo).into(nowImgPhoto);
                    nowTxtName.setText("微博");
                    nowTxtId.setText(mData.getSina_blog().getNick_name());
                }
                if (mData.getSina_blog().getBinding() == 0 && mData.getAlipay().getBinding() == 0 && mData.getWx().getBinding() == 0) {
                    nowLogin.setVisibility(View.GONE);
                }
                break;
            case ApiConfig.GET_ZFB_LOGIN:
                ZFBLoginBean mZFBLoginBeans = (ZFBLoginBean) t[0];
                final String authInfo = mZFBLoginBeans.getData().getUrl();
                Runnable authRunnable = new Runnable() {

                    @Override
                    public void run() {
                        // 构造AuthTask 对象
                        AuthTask authTask = new AuthTask(IdBindActivity.this);
                        // 调用授权接口，获取授权结果
                        Map<String, String> result = authTask.authV2(authInfo, true);

                        Message msg = new Message();
                        msg.what = SDK_AUTH_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };

                // 必须异步调用
                Thread authThread = new Thread(authRunnable);
                authThread.start();
                break;
            case ApiConfig.ZFB_LOGIN:
                ZFBTokenBean mZFBTokenBeans = (ZFBTokenBean) t[0];
                final ZFBTokenBean.DataBean mZFBData = mZFBTokenBeans.getData();
                SharedPrefrenceUtils.saveString(IdBindActivity.this, Config.TOKEN, mZFBData.getToken());
                break;
        }
    }

    @OnClick({R.id.back, R.id.wx_bind, R.id.wb_bind, R.id.zfb_bind})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.wx_bind:
                break;
            case R.id.wb_bind:
                break;
            case R.id.zfb_bind:
                mPresenter.getData(ApiConfig.GET_ZFB_LOGIN, LoadConfig.NORMAL);
                break;
        }
    }
}
