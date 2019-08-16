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

public class WaitListAdapter extends RecyclerView.Adapter<WaitListAdapter.ViewHolder> {
    Context mContext;

    public WaitListAdapter(Context pContext) {
        mContext = pContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_wait_list, pViewGroup, false);
        ViewHolder mViewHolder = new ViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, int pI) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mWaitListImg;
        private TextView mWaitListName;
        private TextView mWaitListProject;
        private TextView mWaitListProgress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mWaitListImg = itemView.findViewById(R.id.wait_list_img);
            mWaitListName = itemView.findViewById(R.id.wait_list_name);
            mWaitListProject = itemView.findViewById(R.id.wait_list_project);
            mWaitListProgress = itemView.findViewById(R.id.wait_list_progress);
        }
    }
}
