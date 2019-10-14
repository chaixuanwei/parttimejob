package com.sxxh.linghuo.me.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.me.bean.BasicInformationBean;

import java.util.ArrayList;

public class ImageViewAdapter extends RecyclerView.Adapter<ImageViewAdapter.ViewHolder> {
    Context mContext;
    private ArrayList<BasicInformationBean.ProjectImagesBean> mList;

    public ImageViewAdapter(Context pContext, ArrayList<BasicInformationBean.ProjectImagesBean> pList) {
        mContext = pContext;
        mList = pList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_imageview, null);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, int pI) {
        Glide.with(mContext).load(mList.get(pI).getPreview_url()).into(pViewHolder.mItemImg);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mItemImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemImg = itemView.findViewById(R.id.item_img);
        }
    }
}
