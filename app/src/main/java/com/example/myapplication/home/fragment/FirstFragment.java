package com.example.myapplication.home.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.frame.BaseMvpFragment;
import com.example.myapplication.frame.CommonPresenter;
import com.example.myapplication.home.adapter.HomeAdapter;
import com.example.myapplication.model.HomeModel;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zaaach.citypicker.CityPickerActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends BaseMvpFragment<CommonPresenter, HomeModel> {
    private static final int REQUEST_CODE_PICK_CITY = 0;
    static FirstFragment fragment;
    @BindView(R.id.place)
    TextView place;
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.home_rv)
    RecyclerView homeRv;
    Unbinder unbinder;
    @BindView(R.id.first_banner)
    Banner firstBanner;
    Unbinder unbinder1;
    private HomeAdapter mAdapter;


    public static FirstFragment newInstance() {
        if (fragment == null) fragment = new FirstFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_first;
    }

    @Override
    public void initView() {
//        List<Integer> images = new ArrayList<>();
//        images.add(R.mipmap.banner_one);
//        images.add(R.mipmap.banner_two);
//        images.add(R.mipmap.banner_three);
//        firstBanner.setImageLoader(new ImageLoader() {
//            @Override
//            public void displayImage(Context context, Object path, ImageView imageView) {
//                Glide.with(getActivity()).load(path).into(imageView);
//            }
//        });
//        firstBanner.setImages(images);
//        firstBanner.start();
        LinearLayoutManager mManager = new LinearLayoutManager(getContext());
        mAdapter = new HomeAdapter(getActivity());
        homeRv.setAdapter(mAdapter);
        homeRv.setLayoutManager(mManager);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                place.setText(city);
            }
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
    public void onError(int whichApi,Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick(R.id.place)
    public void onClick() {
        startActivityForResult(new Intent(getActivity(), CityPickerActivity.class),
                REQUEST_CODE_PICK_CITY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}
