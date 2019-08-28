package com.example.myapplication.me.activity;

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

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.config.ApiConfig;
import com.example.myapplication.config.Config;
import com.example.myapplication.config.LoadConfig;
import com.example.myapplication.frame.ApplicationJob;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.local_utils.SharedPrefrenceUtils;
import com.example.myapplication.login.bean.AuthCodeBean;
import com.example.myapplication.me.adapter.PhotoAdapter;
import com.example.myapplication.me.bean.PerfectBean;
import com.example.myapplication.me.bean.UploadTopBean;
import com.example.myapplication.model.MeModel;
import com.example.myapplication.view.RoundImage;
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

import static com.example.myapplication.local_utils.NetHeaders.getAppVersionCode;

public class AmendActivity extends BaseMvpActivity<CommonPresenter, MeModel> implements InvokeListener, TakePhoto.TakeResultListener, PhotoAdapter.OnPhotoClick, SlideFromBottomPopup.BottomPopClick {

    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    private static final String TAG = "AmendActivity";
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.headportrait)
    RoundImage headportrait;
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
    private ArrayList<File> mFiles = new ArrayList<>();
    private PhotoAdapter mAdapter;
    private InvokeParam invokeParam;
    private TakePhotoImpl mTakePhoto;
    private SlideFromBottomPopup mPop;
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
            case ApiConfig.SET_PREJECT:
                AuthCodeBean mAuthCodeBeans = (AuthCodeBean) t[0];
                finish();
                break;
            case ApiConfig.GET_PREJECT:
                final PerfectBean mPerfectBeans = (PerfectBean) t[0];
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
                                    mList.add(mProject_images.get(i).getUrl());
                                }
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
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
                String mage = age.getText().toString();
                int mAge = Integer.parseInt(mage);
                boolean mCheckedMan = rbMan.isChecked();
                boolean mCheckedGirl = rbGirl.isChecked();
                if (mCheckedMan) {
                    mSix = 1;
                } else if (mCheckedGirl) {
                    mSix = 2;
                } else {
                    mSix = 0;
                }
                mPresenter.getData(ApiConfig.SET_PREJECT, LoadConfig.NORMAL, mName, mNickname, mAddress, mSix, mAge, mList);
                break;
        }
    }

    /*
    start
    上传照片
     */
    /**
     *  获取TakePhoto实例
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

    @Override
    public void takeSuccess(TResult result) {
        mList.clear();
        mFiles.clear();
        ArrayList<TImage> mImages = result.getImages();
        if (isTop) {
            isTop = false;
            String mCompressPath = mImages.get(0).getCompressPath();
            SharedPrefrenceUtils.saveString(this, Config.TOPPHOTO,mCompressPath);
            Glide.with(AmendActivity.this).load(mCompressPath).into(headportrait);
        } else {
            for (int i = 0; i < mImages.size(); i++) {
                String mOriginalPath = mImages.get(i).getCompressPath() != null ? result.getImages().get(i).getCompressPath()
                        : result.getImages().get(i).getOriginalPath();
                mList.add(mOriginalPath);
                mFiles.add(new File(mOriginalPath));
                netUpload(new File(mOriginalPath));
            }
            mAdapter.notifyDataSetChanged();
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
        mList.clear();
        mFiles.clear();
        mTakePhoto = new TakePhotoImpl(this, this);
        mTakePhoto.onEnableCompress(new CompressConfig.Builder().setMaxSize(50 * 1024)
                .setMaxPixel(1080).create(), true);
        mTakePhoto.onPickMultiple(5);
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
                    public void accept(UploadTopBean pUploadTopBean) throws Exception {
                        mList.add(pUploadTopBean.getData().getUrl());
                    }
                });
    }

    @Override
    public void clickTop() {
        mTakePhoto = new TakePhotoImpl(this, this);
        mTakePhoto.onEnableCompress(new CompressConfig.Builder().setMaxSize(50 * 1024)
                .setMaxPixel(1080).create(), true);
//        mTakePhoto.onPickFromGalleryWithCrop(getUri(), getOption());//从相册中获取图片并裁剪
        mTakePhoto.onPickMultiple(9);    //从相册获取图片多选
        Log.e("-----picture", mTakePhoto.toString() + "");
        mPop.dismiss();
    }

    @Override
    public void clickCenter() {
        mTakePhoto = new TakePhotoImpl(this, this);
        mTakePhoto.onEnableCompress(new CompressConfig.Builder().setMaxSize(50 * 1024)
                .setMaxPixel(1080).create(), true);
//        mTakePhoto.onPickFromCaptureWithCrop(getUri(), getOption());    //从相机获取图片并裁剪
        mTakePhoto.onPickFromCapture(getUri());    //从相机获取图片(不裁剪)
        mPop.dismiss();
    }

    @Override
    public void clickBottom() {
        mPop.dismiss();
    }

    private Uri getUri() {
        File file = new File(Environment.getExternalStorageDirectory(), "/woaisiji/" + System.currentTimeMillis() + ".png");
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
