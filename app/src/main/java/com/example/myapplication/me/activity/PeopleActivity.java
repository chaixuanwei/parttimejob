package com.example.myapplication.me.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
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
import com.example.myapplication.me.bean.UploadTopBean;
import com.example.myapplication.model.MeModel;
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

import static com.example.myapplication.local_utils.NetHeaders.getAppVersionCode;

public class PeopleActivity extends BaseMvpActivity<CommonPresenter, MeModel> implements InvokeListener, TakePhoto.TakeResultListener, SlideFromBottomPopup.BottomPopClick {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.people_name)
    EditText peopleName;
    @BindView(R.id.people_card)
    EditText peopleCard;
    @BindView(R.id.people_photo)
    EditText peoplePhoto;
    @BindView(R.id.people_email)
    EditText peopleEmail;
    @BindView(R.id.people_img_classify)
    ImageView peopleImgClassify;
    @BindView(R.id.positive)
    LinearLayout positive;
    @BindView(R.id.people_img_hand)
    ImageView peopleImgHand;
    @BindView(R.id.nedative)
    LinearLayout nedative;
    @BindView(R.id.bt_issus_login)
    TextView btIssusLogin;
    int classify = 0;
    String people_classify_url = "";
    String people_hand_url = "";
    private InvokeParam invokeParam;
    private TakePhotoImpl mTakePhoto;
    private SlideFromBottomPopup mPop;

    @Override
    public int getLayoutId() {
        return R.layout.activity_people;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

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

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.SET_PEOPLE:
                AuthCodeBean mAuthCodeBean = (AuthCodeBean) t[0];
                ToastUtils.showShort(mAuthCodeBean.getMsg());
                startActivity(new Intent(PeopleActivity.this,WaitAuditActivity.class));
                finish();
                break;
        }
    }

    @OnClick({R.id.back, R.id.positive, R.id.nedative, R.id.bt_issus_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.positive:
                classify = 1;
                mPop = new SlideFromBottomPopup(this);
                mPop.setLineText("相册", "拍照", "取消");
                mPop.setBottomClickListener(this);
                mPop.showPopupWindow();
                break;
            case R.id.nedative:
                classify = 2;
                mPop = new SlideFromBottomPopup(this);
                mPop.setLineText("相册", "拍照", "取消");
                mPop.setBottomClickListener(this);
                mPop.showPopupWindow();
                break;
            case R.id.bt_issus_login:
                String mName = peopleName.getText().toString();
                String mCard = peopleCard.getText().toString();
                if (!mName.equals("") && !mCard.equals("") && !people_classify_url.equals("") && !people_hand_url.equals("")) {
                    mPresenter.getData(ApiConfig.SET_PEOPLE, LoadConfig.NORMAL, mCard, mName, people_classify_url, people_hand_url);
                } else {
                    ToastUtils.showShort("请填写完整信息");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mTakePhoto.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
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
        mTakePhoto.onPickMultiple(1);    //从相册获取图片多选
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
                        if (classify == 1) {
                            Glide.with(PeopleActivity.this).load(mUrl).into(peopleImgClassify);
                            people_classify_url = mUrl;
                        } else if (classify == 2) {
                            Glide.with(PeopleActivity.this).load(mUrl).into(peopleImgHand);
                            people_hand_url = mUrl;
                        }
                    }
                });
    }

    private Uri getUri() {
        File file = new File(Environment.getExternalStorageDirectory(), "/woaisiji/" + System.currentTimeMillis() + ".png");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri imageUri = Uri.fromFile(file);
        return imageUri;
    }
}
