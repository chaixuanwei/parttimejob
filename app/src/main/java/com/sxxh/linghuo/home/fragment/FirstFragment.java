package com.sxxh.linghuo.home.fragment;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.config.ApiConfig;
import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.config.LoadConfig;
import com.sxxh.linghuo.frame.BaseMvpFragment;
import com.sxxh.linghuo.frame.CommonPresenter;
import com.sxxh.linghuo.home.activity.SearchActivity;
import com.sxxh.linghuo.home.adapter.HomeAdapter;
import com.sxxh.linghuo.home.bean.BannerBean;
import com.sxxh.linghuo.home.bean.MenuBean;
import com.sxxh.linghuo.local_utils.SharedPrefrenceUtils;
import com.sxxh.linghuo.model.HomeModel;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zaaach.citypicker.CityPickerActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends BaseMvpFragment<CommonPresenter, HomeModel> {
    private static final int REQUEST_CODE_PICK_CITY = 0;
    static FirstFragment fragment;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.place)
    TextView place;
    @BindView(R.id.first_search)
    LinearLayout first_search;
    @BindView(R.id.home_rv)
    RecyclerView homeRv;
    Unbinder unbinder;
    @BindView(R.id.search)
    TextView mSearch;
    @BindView(R.id.first_banner)
    Banner firstBanner;
    private HomeAdapter mAdapter;


    public static FirstFragment newInstance() {
        if (fragment == null) fragment = new FirstFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_first2;
    }

    @Override
    public void initView() {
//        mCollapsingToolbarLayout.setContentScrimResource(R.drawable.toolbar);
        mCollapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.blue_theme));
        LinearLayoutManager mManager = new LinearLayoutManager(getContext());
        mAdapter = new HomeAdapter(getActivity());
        homeRv.setAdapter(mAdapter);
        homeRv.setLayoutManager(mManager);
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getActivity(), SearchActivity.class);
                startActivity(mIntent);
            }
        });
        String mPlace = SharedPrefrenceUtils.getString(getActivity(), Config.PLACE);
        if (mPlace.equals("")) {

        } else {
            place.setText(mPlace);
        }
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.HOME_BANNER, LoadConfig.NORMAL, "1");
        mPresenter.getData(ApiConfig.HOME_MENU, LoadConfig.NORMAL);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                SharedPrefrenceUtils.saveString(getActivity(), Config.PLACE, city);
                if (city.equals("")) {

                } else {
                    place.setText(city);
                }
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
    public void onError(int whichApi, Throwable e) {
        Log.e("首页", "onError: " + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.HOME_BANNER:
                BannerBean mBannerBeans = (BannerBean) t[0];
                List<BannerBean.DataBean> mData = mBannerBeans.getData();
                List<String> images = new ArrayList<>();
                List<String> urls = new ArrayList<>();
                List<String> titles = new ArrayList<>();
                for (int i = 0; i < mData.size(); i++) {
                    images.add(mData.get(i).getImage());
                    urls.add(mData.get(i).getUrl());
                    titles.add(mData.get(i).getTitle());
                }
                firstBanner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(getActivity()).load((String) path).into(imageView);
                    }
                });
                firstBanner.setImages(images);
                firstBanner.setBannerTitles(titles);
                firstBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int pI, float pV, int pI1) {

                    }

                    @Override
                    public void onPageSelected(int pI) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int pI) {

                    }
                });
                firstBanner.setDelayTime(3000);
                firstBanner.start();
                break;
            case ApiConfig.HOME_MENU:
                MenuBean mMenuBeans = (MenuBean) t[0];

                break;
        }
    }

    @OnClick(R.id.place)
    public void onClick() {
        startActivityForResult(new Intent(getActivity(), CityPickerActivity.class),
                REQUEST_CODE_PICK_CITY);
    }
}
