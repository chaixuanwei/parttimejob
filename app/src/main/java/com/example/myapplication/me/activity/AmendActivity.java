package com.example.myapplication.me.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.config.Config;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.local_utils.PhotoUtils;
import com.example.myapplication.local_utils.SharedPrefrenceUtils;
import com.example.myapplication.me.adapter.PhotoAdapter;
import com.example.myapplication.model.MeModel;
import com.example.myapplication.view.RoundImage;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class AmendActivity extends BaseMvpActivity<CommonPresenter, MeModel> implements InvokeListener, TakePhoto.TakeResultListener, PhotoAdapter.OnPhotoClick {

    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.headportrait)
    RoundImage headportrait;
    @BindView(R.id.photo_rv)
    RecyclerView photoRv;
    private ArrayList<String> mList = new ArrayList<>();
    private ArrayList<File> mFiles = new ArrayList<>();
    private PhotoAdapter mAdapter;
    private InvokeParam invokeParam;
    private TakePhotoImpl mTakePhoto;
    private int num = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_amend;
    }

    @Override
    public void initView() {
        initPermmition();
        Bitmap mBitmap = SharedPrefrenceUtils.getBitmap(this, Config.BITMAP, null);
        if (null != mBitmap) {
            headportrait.setImageBitmap(mBitmap);
        }
        LinearLayoutManager mManager = new LinearLayoutManager(this);
        mManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mAdapter = new PhotoAdapter(this, mList);
        photoRv.setLayoutManager(mManager);
        photoRv.setAdapter(mAdapter);
        mAdapter.setListener(this);
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

    }

    @OnClick({R.id.back, R.id.headportrait})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.headportrait:
                Intent oneintent = new Intent(Intent.ACTION_PICK, null);
                oneintent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(oneintent, Config.PHOTO);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Config.PHOTO) {
            if (data != null) {
                Uri mUri = data.getData();
                Bitmap mBitmap = null;
                if (mUri != null) {
                    int degrees = PhotoUtils.getOrientation(this, mUri);
                    try {
                        mBitmap = PhotoUtils.getBitmapFormUri(this, mUri);
                    } catch (IOException pE) {
                        pE.printStackTrace();
                    }
                    mBitmap = PhotoUtils.rotateImage(mBitmap, degrees);
                    SharedPrefrenceUtils.putBitmap(this, Config.BITMAP, mBitmap);
                    headportrait.setImageBitmap(mBitmap);
                }
            }
        } else {
            mTakePhoto.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    @Override
    public void takeSuccess(TResult result) {
        ArrayList<TImage> mImages = result.getImages();
        for (int i = 0; i < mImages.size(); i++) {
            String mOriginalPath = mImages.get(i).getOriginalPath();
            mList.add(mOriginalPath);
            mFiles.add(new File(mOriginalPath));
        }
        mAdapter.notifyDataSetChanged();
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
        num = pI;
//        if (pI == 0) {
        mList.clear();
        mFiles.clear();
        mTakePhoto = new TakePhotoImpl(this, this);
        mTakePhoto.onEnableCompress(new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(1080).create(), true);
        mTakePhoto.onPickMultiple(5);
//        }
    }
}
