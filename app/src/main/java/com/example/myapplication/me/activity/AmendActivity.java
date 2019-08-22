package com.example.myapplication.me.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.config.Config;
import com.example.myapplication.frame.BaseMvpActivity;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.local_utils.PhotoUtils;
import com.example.myapplication.local_utils.SharedPrefrenceUtils;
import com.example.myapplication.model.MeModel;
import com.example.myapplication.view.RoundImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class AmendActivity extends BaseMvpActivity<CommonPresenter, MeModel> {

    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.headportrait)
    RoundImage headportrait;
    @BindView(R.id.project_photo)
    TextView projectPhoto;

    @Override
    public int getLayoutId() {
        return R.layout.activity_amend;
    }

    @Override
    public void initView() {
        Bitmap mBitmap = SharedPrefrenceUtils.getBitmap(this, Config.BITMAP, null);
        if (null != mBitmap) {
            headportrait.setImageBitmap(mBitmap);
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
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick({R.id.back, R.id.headportrait, R.id.project_photo})
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
            case R.id.project_photo:
                pickImage();
                break;
        }
    }

    private void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.mis_permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        } else {
            int maxNum = 9;
            MultiImageSelector selector = MultiImageSelector.create(AmendActivity.this);
            selector.count(maxNum);
            selector.multi();
            selector.start(AmendActivity.this, Config.MOREPHOTO);
        }
    }

    private void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.mis_permission_dialog_title)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.mis_permission_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(AmendActivity.this, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.mis_permission_dialog_cancel, null)
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_STORAGE_READ_ACCESS_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImage();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
        } else if (requestCode == Config.MOREPHOTO) {
//            if (data != null) {
//                ArrayList<String> mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
//                projectPhoto.setVisibility(View.GONE);
//                for (int i = 0; i < mSelectPath.size(); i++) {
//                    if (i == 0) {
//                        Uri mUri = Uri.parse(mSelectPath.get(0));
//                        imageOne.setImageURI(mUri);
//                    } else if (i == 1) {
//                        Uri mUri = Uri.parse(mSelectPath.get(1));
//                        imageTwo.setImageURI(mUri);
//                    } else if (i == 2) {
//                        Uri mUri = Uri.parse(mSelectPath.get(2));
//                        imageThree.setImageURI(mUri);
//                    } else if (i == 3) {
//                        Uri mUri = Uri.parse(mSelectPath.get(3));
//                        imageFour.setImageURI(mUri);
//                    } else if (i == 4) {
//                        Uri mUri = Uri.parse(mSelectPath.get(4));
//                        imageFive.setImageURI(mUri);
//                    }
//                }
//            }
        }
    }
}
