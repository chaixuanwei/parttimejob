package com.sxxh.linghuo.me.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.ApplicationJob;
import com.sxxh.linghuo.frame.BaseMvpActivity;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.local_utils.SharedPrefrenceUtils;
import com.sxxh.linghuo.login.bean.AuthCodeBean;
import com.sxxh.linghuo.me.adapter.SubmitAdapter;
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
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import razerdp.design.SlideFromBottomPopup;

import static com.sxxh.linghuo.local_utils.NetHeaders.getAppVersionCode;

public class SubmitWorkActivity extends BaseMvpActivity<CommonPresenter, MeModel> implements InvokeListener, TakePhoto.TakeResultListener, SlideFromBottomPopup.BottomPopClick, SubmitAdapter.ToJumpPhoto, SubmitAdapter.DeletePhoto {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.submit_work_rv)
    RecyclerView submitWorkRv;
    @BindView(R.id.submit)
    TextView submit;
    private InvokeParam invokeParam;
    private TakePhotoImpl mTakePhoto;
    private SlideFromBottomPopup mPop;
    int photo = 0;
    private SubmitAdapter mAdapter;
    private ArrayList<String> mList = new ArrayList<String>();
    private ArrayList<String> mUpload = new ArrayList<String>();
    private boolean mUpdataOne = false;
    int updata = 0;
    private String mTask_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_submit_work;
    }

    @Override
    public void initView() {
        Intent mIntent = getIntent();
        String mName = mIntent.getStringExtra("name");
        mTask_id = mIntent.getStringExtra("task_id");
    }

    @Override
    public void initData() {
        mAdapter = new SubmitAdapter(this, mList);
        GridLayoutManager mManager = new GridLayoutManager(this, 4);
        submitWorkRv.setLayoutManager(mManager);
        submitWorkRv.setAdapter(mAdapter);
        mAdapter.setToJumpPhoto(this);
        mAdapter.setDeletePhoto(this);
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
        Log.e("立即提交", "onError: " + "立即提交");
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.SET_SUBMIT:
                AuthCodeBean mAuthCodeBeans = (AuthCodeBean) t[0];
                String mMsg = mAuthCodeBeans.getMsg();
                if (mMsg.equals("提交成功")) {
                    ToastUtils.showShort(mMsg);
                    finish();
                } else {
                    ToastUtils.showShort(mMsg);
                }
                break;
        }
    }

    @OnClick({R.id.back, R.id.submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.submit:
                String task_images = "";
                if (mUpload.size() != 0) {
                    for (int i = 0; i < mUpload.size(); i++) {
                        if (i != mUpload.size() - 1) {
                            task_images += mUpload.get(i) + ";";
                        } else {
                            task_images += mUpload.get(i);
                        }
                    }
                    mPresenter.getData(ApiConfig.SET_SUBMIT, LoadConfig.NORMAL, Integer.parseInt(mTask_id), task_images);
                } else {
                    ToastUtils.showShort("请上传照片！");
                }
                break;
        }
    }

    @Override
    public void getPhoto(int position) {
        updata = position;
        if (position == mList.size()) {
            mUpdataOne = false;
            mPop = new SlideFromBottomPopup(this);
            mPop.setLineText("相册", "拍照", "取消");
            mPop.setBottomClickListener(this);
            mPop.showPopupWindow();
        } else {
            mUpdataOne = true;
            mPop = new SlideFromBottomPopup(this);
            mPop.setLineText("相册", "拍照", "取消");
            mPop.setBottomClickListener(this);
            mPop.showPopupWindow();
        }
    }

    @Override
    public void goPhoto(int position) {
        mList.remove(position);
        mUpload.remove(position);
        mAdapter.notifyDataSetChanged();
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
        int permission = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
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
                        String mUrl = pUploadTopBean.getData().getUrl();
                        String mFilepath = pUploadTopBean.getData().getFilepath();
                        if (mUpdataOne) {
                            mList.set(updata, mUrl);
                            mUpload.set(updata, mFilepath);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mAdapter.notifyItemChanged(updata);
                                }
                            });
                        } else {
                            mList.add(mUrl);
                            mUpload.add(mFilepath);
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
    public void clickTop() {
        mTakePhoto = new TakePhotoImpl(this, this);
        mTakePhoto.onEnableCompress(new CompressConfig.Builder().setMaxSize(50 * 1024)
                .setMaxPixel(1080).create(), true);
        if (mUpdataOne) {
            mTakePhoto.onPickMultiple(1);    //从相册获取图片多选
        } else {
            mTakePhoto.onPickMultiple(100);    //从相册获取图片多选
        }
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
