package com.sxxh.linghuo.message.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.activity.WebviewActivity;
import com.sxxh.linghuo.message.bean.AfficheBean;

import java.util.ArrayList;

public class AfficheAdapter extends RecyclerView.Adapter<AfficheAdapter.ViewHolder> {
    Context mContext;
    ArrayList<AfficheBean.DataBean> mAddicheList;

    public AfficheAdapter(Context pContext, ArrayList<AfficheBean.DataBean> pAddicheList) {
        mContext = pContext;
        mAddicheList = pAddicheList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_affiche, pViewGroup, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, int pI) {
        final AfficheBean.DataBean mDataBean = mAddicheList.get(pI);
        ViewHolder mViewHolder = pViewHolder;
        if (mDataBean != null) {
            mViewHolder.mItemAfficheTitle.setText(mDataBean.getTitle());
            mViewHolder.mItemAfficheContent.setText(mDataBean.getPost_excerpt());
            Glide.with(mContext).load(mDataBean.getThumbnail()).into(mViewHolder.mItemAfficheImg);
        }
        mViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(mContext, WebviewActivity.class);
                mIntent.putExtra("url",mDataBean.getUrl());
                mContext.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAddicheList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mItemAfficheImg;
        private TextView mItemAfficheTitle;
        private TextView mItemAfficheContent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemAfficheImg = itemView.findViewById(R.id.item_affiche_img);
            mItemAfficheTitle = itemView.findViewById(R.id.item_affiche_title);
            mItemAfficheContent = itemView.findViewById(R.id.item_affiche_content);
        }
    }
}
