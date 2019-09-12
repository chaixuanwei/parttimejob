package com.sxxh.linghuo.me.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.sxxh.linghuo.login.bean.AuthCodeBean;
import com.sxxh.linghuo.me.bean.IndustryBean;
import com.sxxh.linghuo.me.bean.ScaleBean;
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

public class FirmActivity extends BaseMvpActivity<CommonPresenter, MeModel> implements InvokeListener, TakePhoto.TakeResultListener, SlideFromBottomPopup.BottomPopClick {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.firm_scale)
    Spinner firmScale;
    @BindView(R.id.firm_industry)
    Spinner firmIndustry;
    @BindView(R.id.firm_name)
    EditText firmName;
    @BindView(R.id.firm_people_name)
    EditText firmPeopleName;
    @BindView(R.id.firm_people_photo)
    EditText firmPeoplePhoto;
    @BindView(R.id.firm_business_license)
    ImageView firmBusinessLicense;
    @BindView(R.id.firm_hand_business_license)
    ImageView firmHandBusinessLicense;
    @BindView(R.id.firm_card)
    ImageView firmCard;
    @BindView(R.id.bt_issus_login)
    TextView btIssusLogin;
    private InvokeParam invokeParam;
    private TakePhotoImpl mTakePhoto;
    private SlideFromBottomPopup mPop;
    private ArrayAdapter<String> mScaleAda;
    private ArrayAdapter<String> mIndustryAda;
    ArrayList<String> mIndustry = new ArrayList<>();
    ArrayList<String> mScale = new ArrayList<>();
    String industryString = "";
    String scaleString = "";
    String firm_business_license = "";
    String firm_hand_business_license = "";
    String firm_card = "";
    int photo = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_firm;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.GET_SCALE, LoadConfig.NORMAL);
        mPresenter.getData(ApiConfig.GET_INDUSTRY, LoadConfig.NORMAL);
        mIndustryAda = new ArrayAdapter<String>(FirmActivity.this, android.R.layout.simple_spinner_item, mIndustry);
        mIndustryAda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firmIndustry.setAdapter(mIndustryAda);
        firmIndustry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                industryString = mIndustry.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mScaleAda = new ArrayAdapter<String>(FirmActivity.this, android.R.layout.simple_spinner_item, mScale);
        mScaleAda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firmScale.setAdapter(mScaleAda);
        firmScale.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                scaleString = mScale.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
        ToastUtils.showShort("ssss");
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GET_INDUSTRY:
                IndustryBean mIndustryBeans = (IndustryBean) t[0];
                List<IndustryBean.DataBean> mIndustryData = mIndustryBeans.getData();
                for (int i = 0; i < mIndustryData.size(); i++) {
                    mIndustry.add(mIndustryData.get(i).getName());
                }
                mIndustryAda.notifyDataSetChanged();
                break;
            case ApiConfig.GET_SCALE:
                ScaleBean mScaleBean = (ScaleBean) t[0];
                List<String> mScaleData = mScaleBean.getData();
                mScale.addAll(mScaleData);
                mScaleAda.notifyDataSetChanged();
                break;
            case ApiConfig.SET_FIRM:
                AuthCodeBean mAuthCodeBean = (AuthCodeBean) t[0];
                ToastUtils.showShort(mAuthCodeBean.getMsg());
                startActivity(new Intent(FirmActivity.this, WaitAuditActivity.class));
                finish();
                break;
        }
    }

    @OnClick({R.id.back, R.id.firm_business_license, R.id.firm_hand_business_license, R.id.firm_card, R.id.bt_issus_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.firm_business_license:
                photo = 1;
                mPop = new SlideFromBottomPopup(this);
                mPop.setLineText("相册", "拍照", "取消");
                mPop.setBottomClickListener(this);
                mPop.showPopupWindow();
                break;
            case R.id.firm_hand_business_license:
                photo = 2;
                mPop = new SlideFromBottomPopup(this);
                mPop.setLineText("相册", "拍照", "取消");
                mPop.setBottomClickListener(this);
                mPop.showPopupWindow();
                break;
            case R.id.firm_card:
                photo = 3;
                mPop = new SlideFromBottomPopup(this);
                mPop.setLineText("相册", "拍照", "取消");
                mPop.setBottomClickListener(this);
                mPop.showPopupWindow();
                break;
            case R.id.bt_issus_login:
                String mFirmName = firmName.getText().toString();
                String mFirmPeopleName = firmPeopleName.getText().toString();
                String mFirmPeoplePhoto = firmPeoplePhoto.getText().toString();
                if (!mFirmName.equals("") && !mFirmPeopleName.equals("") && !mFirmPeoplePhoto.equals("") && !firm_business_license.equals("") && !firm_hand_business_license.equals("") && !firm_card.equals("")) {
                    mPresenter.getData(ApiConfig.SET_FIRM, LoadConfig.NORMAL, mFirmName, scaleString, industryString, mFirmPeopleName, mFirmPeoplePhoto, firm_business_license, firm_hand_business_license, firm_card);
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
                        if (photo == 1) {
                            firm_business_license = mUrl;
                            Glide.with(FirmActivity.this).load(mUrl).into(firmBusinessLicense);
                        } else if (photo == 2) {
                            firm_hand_business_license = mUrl;
                            Glide.with(FirmActivity.this).load(mUrl).into(firmHandBusinessLicense);
                        } else if (photo == 3) {
                            firm_card = mUrl;
                            Glide.with(FirmActivity.this).load(mUrl).into(firmCard);
                        }
                    }
                });
    }

    private Uri getUri() {
        File file = new File(Environment.getExternalStorageDirectory(), "/linghuo/" + System.currentTimeMillis() + ".png");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri imageUri = Uri.fromFile(file);
        return imageUri;
    }
}
