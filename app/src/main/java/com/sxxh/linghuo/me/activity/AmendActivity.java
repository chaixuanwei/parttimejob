package com.sxxh.linghuo.me.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.ApplicationJob;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.local_utils.SharedPrefrenceUtils;
import com.sxxh.linghuo.login.LoginActivity;
import com.sxxh.linghuo.login.bean.AuthCodeBean;
import com.sxxh.linghuo.me.adapter.PhotoAdapter;
import com.sxxh.linghuo.me.bean.PerfectBean;
import com.sxxh.linghuo.me.bean.UploadTopBean;
import com.sxxh.linghuo.model.MeModel;
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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import razerdp.design.SlideFromBottomPopup;

import static com.sxxh.linghuo.local_utils.NetHeaders.getAppVersionCode;

public class AmendActivity extends BaseMvpActivity<CommonPresenter, MeModel> implements InvokeListener, TakePhoto.TakeResultListener, PhotoAdapter.OnPhotoClick, SlideFromBottomPopup.BottomPopClick {

    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    private static final String TAG = "AmendActivity";
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.headportrait)
    ImageView headportrait;
    @BindView(R.id.photo_rv)
    RecyclerView photoRv;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.rb_man)
    RadioButton rbMan;
    @BindView(R.id.rb_girl)
    RadioButton rbGirl;
    @BindView(R.id.nickname)
    EditText nickname;
    @BindView(R.id.age)
    EditText age;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.confirm)
    TextView confirm;
    private ArrayList<String> mList = new ArrayList<String>();
    private ArrayList<String> mUpload = new ArrayList<String>();
    private PhotoAdapter mAdapter;
    private InvokeParam invokeParam;
    private TakePhotoImpl mTakePhoto;
    private SlideFromBottomPopup mPop;
    int photo = 0;
    Boolean isTop = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_amend;
    }

    @Override
    public void initView() {
        initPermmition();
        String mPhoto = SharedPrefrenceUtils.getString(this, Config.TOPPHOTO);
        if (!mPhoto.equals("")) {
            Glide.with(this).load(mPhoto).into(headportrait);
        }
        LinearLayoutManager mManager = new LinearLayoutManager(this);
        mManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mAdapter = new PhotoAdapter(this, mList);
        photoRv.setLayoutManager(mManager);
        photoRv.setAdapter(mAdapter);
        mAdapter.setListener(this);
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.GET_PREJECT, LoadConfig.NORMAL);
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
        Log.e(TAG, "onError: " + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, final Object[] t) {
        switch (whichApi) {
            case ApiConfig.ALTER_TOP:
                AuthCodeBean AlterTop = (AuthCodeBean) t[0];
                ToastUtils.showShort("头像修改成功！");
                break;
            case ApiConfig.SET_PREJECT:
                AuthCodeBean mAuthCodeBeans = (AuthCodeBean) t[0];
                if (mAuthCodeBeans.getCode() == 10001) {
                    ToastUtils.showShort(mAuthCodeBeans.getMsg());
                    startActivity(new Intent(AmendActivity.this, LoginActivity.class));
                    finish();
                } else {
                    ToastUtils.showShort(mAuthCodeBeans.getMsg());
                }
                finish();
                break;
            case ApiConfig.GET_PREJECT:
                final PerfectBean mPerfectBeans = (PerfectBean) t[0];
                if (mPerfectBeans.getCode() == 10001) {
                    startActivity(new Intent(AmendActivity.this, LoginActivity.class));
                    finish();
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            PerfectBean.DataBean mData = mPerfectBeans.getData();
                            if (mData != null) {
                                name.setText(mData.getReal_name());
                                nickname.setText(mData.getUser_nickname());
                                int mSex = mData.getSex();
                                if (mSex == 1) {
                                    rbMan.setChecked(true);
                                } else if (mSex == 2) {
                                    rbGirl.setChecked(true);
                                }
                                age.setText(mData.getAge() + "");
                                address.setText(mData.getAddress());
                                List<PerfectBean.DataBean.ProjectImagesBean> mProject_images = mData.getProject_images();
                                if (mProject_images != null) {
                                    for (int i = 0; i < mProject_images.size(); i++) {
                                        mList.add(mProject_images.get(i).getPreview_url());
                                    }
                                    mAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
                }
                break;
        }
    }

    @OnClick({R.id.back, R.id.headportrait, R.id.confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.headportrait:
                isTop = true;
                mPop = new SlideFromBottomPopup(this);
                mPop.setLineText("相册", "拍照", "取消");
                mPop.setBottomClickListener(this);
                mPop.showPopupWindow();
                break;
            case R.id.confirm:
                String mName = name.getText().toString();
                String mNickname = nickname.getText().toString();
                String mAddress = address.getText().toString();
                int mSix = 0;
                boolean mCheckedMan = rbMan.isChecked();
                boolean mCheckedGirl = rbGirl.isChecked();
                if (mCheckedMan) {
                    mSix = 1;
                } else if (mCheckedGirl) {
                    mSix = 2;
                } else {
                    mSix = 0;
                }
                String mage = age.getText().toString();
                if (!mName.equals("") && !mNickname.equals("") && !mAddress.equals("") && !mage.equals("")) {
                    int mAge = Integer.parseInt(mage);
                    mPresenter.getData(ApiConfig.SET_PREJECT, LoadConfig.NORMAL, mName, mNickname, mAddress, mSix, mAge, mUpload);
                } else {
                    ToastUtils.showShort("请填写完整信息");
                }
                break;
        }
    }

    /*
    start
    上传照片
     */

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mTakePhoto.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    private void initPermmition() {
        int permission = ActivityCompat.checkSelfPermission(AmendActivity.this,
                Manifest.permission.CAMERA);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AmendActivity.this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }
    }

    //上传照片
    public void netUpload(File pFile) {
        String url = "http://job.zhangtongdongli.com/api/user/upload/one";
        Map<String, String> mMap = new HashMap<>();
        mMap.put("XX-Api-Version", getAppVersionCode(ApplicationJob.getAppContext()));
        mMap.put("XX-Device-Type", "android");
        String mToken = SharedPrefrenceUtils.getString(ApplicationJob.getAppContext(), Config.TOKEN);
        mMap.put("XX-Token", mToken);
        Rx2AndroidNetworking.upload(url)
                .setContentType("multipart/form-data")
                .addMultipartFile("file", pFile)
                .addHeaders(mMap)
                .build()
                .getObjectObservable(UploadTopBean.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UploadTopBean>() {
                    @Override
                    public void accept(final UploadTopBean pUploadTopBean) throws Exception {
                        if (isTop) {
                            isTop = false;
                            final String mUrl = pUploadTopBean.getData().getUrl();
                            SharedPrefrenceUtils.saveString(AmendActivity.this, Config.TOPPHOTO, mUrl);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Glide.with(AmendActivity.this).load(mUrl).into(headportrait);
                                }
                            });
                            mPresenter.getData(ApiConfig.ALTER_TOP, LoadConfig.NORMAL, mUrl);
                        } else {
                            if (photo != 0) {
                                mList.set(photo - 1, pUploadTopBean.getData().getUrl());
                            } else {
                                mList.add(pUploadTopBean.getData().getUrl());
                            }
                            mUpload.add(pUploadTopBean.getData().getFilepath());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                    }
                });
    }

    @Override
    public void takeSuccess(TResult result) {
        if (photo == 0) {
            mList.clear();
        }
        ArrayList<TImage> mImages = result.getImages();
        for (int i = 0; i < mImages.size(); i++) {
            String mOriginalPath = mImages.get(i).getCompressPath() != null ? result.getImages().get(i).getCompressPath()
                    : result.getImages().get(i).getOriginalPath();
            netUpload(new File(mOriginalPath));
        }
    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    @Override
    public void onPhoto(int pI) {
        photo = pI;
        mTakePhoto = new TakePhotoImpl(this, this);
        mTakePhoto.onEnableCompress(new CompressConfig.Builder().setMaxSize(50 * 1024)
                .setMaxPixel(1080).create(), true);
        if (pI == 0) {
            mTakePhoto.onPickMultiple(5);
        } else {
            mTakePhoto.onPickMultiple(1);
        }
    }

    @Override
    public void clickTop() {
        mTakePhoto = new TakePhotoImpl(this, this);
        mTakePhoto.onEnableCompress(new CompressConfig.Builder().setMaxSize(50 * 1024)
                .setMaxPixel(1080).create(), true);
        mTakePhoto.onPickMultiple(5);    //从相册获取图片多选
        Log.e("-----picture", mTakePhoto.toString() + "");
        mPop.dismiss();
    }

    @Override
    public void clickCenter() {
        mTakePhoto = new TakePhotoImpl(this, this);
        mTakePhoto.onEnableCompress(new CompressConfig.Builder().setMaxSize(50 * 1024)
                .setMaxPixel(1080).create(), true);
        mTakePhoto.onPickFromCapture(getUri());    //从相机获取图片(不裁剪)
        mPop.dismiss();
    }

    @Override
    public void clickBottom() {
        mPop.dismiss();
    }

    private Uri getUri() {
        File file = new File(Environment.getExternalStorageDirectory(), "/linghuo/" + System.currentTimeMillis() + ".png");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri imageUri = Uri.fromFile(file);
        return imageUri;
    }

    /*
    end
    上传照片
     */
}
