package com.example.myapplication.me.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.local_utils.DateUtil;
import com.example.myapplication.me.bean.ProjectSufferBean;

import java.util.ArrayList;

public class ProjectExperienceAdapter extends RecyclerView.Adapter<ProjectExperienceAdapter.ViewHolder> {
    Context mContext;
    ArrayList<ProjectSufferBean.DataBean> mList;

    public ProjectExperienceAdapter(Context pContext, ArrayList<ProjectSufferBean.DataBean> pList) {
        mContext = pContext;
        mList = pList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_project, pViewGroup, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, int pI) {
        ViewHolder mViewHolder = pViewHolder;
        ProjectSufferBean.DataBean mDataBean = mList.get(pI);
        mViewHolder.mItemProjectName.setText(mDataBean.getName());
        int mComplete = mDataBean.getComplete();
        if (mComplete == 0) {
            mViewHolder.mItemProjectProgress.setText("项目进度：未完成");
        } else {
            mViewHolder.mItemProjectProgress.setText("项目进度：已完成");
        }
        String mDateToString = DateUtil.getDateToString(mDataBean.getAdd_time(), "yyyy/MM/dd");
        mViewHolder.mItemProjectTime.setText(mDateToString);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mItemProjectName;
        private TextView mItemProjectProgress;
        private TextView mItemProjectTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemProjectName = itemView.findViewById(R.id.item_project_name);
            mItemProjectProgress = itemView.findViewById(R.id.item_project_progress);
            mItemProjectTime = itemView.findViewById(R.id.item_project_time);
        }
    }
}
