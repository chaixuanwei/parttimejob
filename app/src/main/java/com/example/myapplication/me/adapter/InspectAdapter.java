package com.example.myapplication.me.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.me.activity.ProjectReviewActivity;
import com.example.myapplication.me.bean.ProjectProgressBean;

import java.util.ArrayList;

public class InspectAdapter extends RecyclerView.Adapter<InspectAdapter.ViewHolder> {
    Context mContext;
    ArrayList<ProjectProgressBean.DataBean> mList;

    public InspectAdapter(Context pContext, ArrayList<ProjectProgressBean.DataBean> pList) {
        mContext = pContext;
        mList = pList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_inspect, pViewGroup, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, int pI) {
        ViewHolder mViewHolder = pViewHolder;
        ProjectProgressBean.DataBean mDataBean = mList.get(pI);
        mViewHolder.mItemInspectName.setText(mDataBean.getReal_name());
        Glide.with(mContext).load(mDataBean.getAvatar()).into(mViewHolder.mItemInspectImg);
        int mComplete = mDataBean.getComplete();
        if (mComplete == 0) {
            mViewHolder.mItemInspectProgress.setText("项目进度：未完成");
            mViewHolder.mItemInspectLookall.setVisibility(View.GONE);
        } else {
            mViewHolder.mItemInspectProgress.setText("项目进度：已完成");
            mViewHolder.mItemInspectLookall.setVisibility(View.VISIBLE);
        }
        if (mViewHolder.mItemInspectLookall.getVisibility() == View.VISIBLE) {
            mViewHolder.mItemInspectLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mProjectReviewIntent = new Intent(mContext, ProjectReviewActivity.class);
                    mContext.startActivity(mProjectReviewIntent);
                }
            });
        } else {

        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mItemInspectLl;
        private ImageView mItemInspectImg;
        private TextView mItemInspectName;
        private TextView mItemInspectLookall;
        private TextView mItemInspectProgress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemInspectLl = itemView.findViewById(R.id.item_inspect_ll);
            mItemInspectLookall = itemView.findViewById(R.id.item_inspect_look_all);
            mItemInspectImg = itemView.findViewById(R.id.item_inspect_img);
            mItemInspectName = itemView.findViewById(R.id.item_inspect_name);
            mItemInspectProgress = itemView.findViewById(R.id.item_inspect_progress);
        }
    }
}
