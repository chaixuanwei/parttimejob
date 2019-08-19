package com.example.myapplication.me.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

public class WorkingAdapter extends RecyclerView.Adapter<WorkingAdapter.ViewHolder> {
    Context mContext;

    public WorkingAdapter(Context pContext) {
        mContext = pContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_work, pViewGroup, false);
        ViewHolder mViewHolder = new ViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, int pI) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mWorkingImg;
        private TextView mWorkingName;
        private TextView mWorkingProject;
        private TextView mWorkingProgress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mWorkingImg = itemView.findViewById(R.id.working_img);
            mWorkingName = itemView.findViewById(R.id.working_name);
            mWorkingProject = itemView.findViewById(R.id.working_project);
            mWorkingProgress = itemView.findViewById(R.id.working_progress);
        }
    }
}
