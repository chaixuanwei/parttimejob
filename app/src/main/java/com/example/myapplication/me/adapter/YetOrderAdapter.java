package com.example.myapplication.me.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.me.activity.ApplyPeopleActivity;
import com.example.myapplication.me.activity.InspectActivity;

public class YetOrderAdapter extends RecyclerView.Adapter<YetOrderAdapter.ViewHolder> {

    Context mContext;

    public YetOrderAdapter(Context pContext) {
        mContext = pContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_yet_order, pViewGroup,false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, int pI) {
        ViewHolder mViewHolder = pViewHolder;
        mViewHolder.mItemYetOrderJobLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mApplyPeopleIntent = new Intent(mContext, ApplyPeopleActivity.class);
                mContext.startActivity(mApplyPeopleIntent);
            }
        });
        mViewHolder.mItemYetOrderJobProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mInspectIntent = new Intent(mContext, InspectActivity.class);
                mContext.startActivity(mInspectIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mItemYetOrderJobName;
        private TextView mItemYetOrderJobPay;
        private TextView mItemYetOrderJobSuffer;
        private TextView mItemYetOrderJobContent;
        private TextView mItemYetOrderJobLook;
        private TextView mItemYetOrderJobProgress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemYetOrderJobName = itemView.findViewById(R.id.item_yet_order_job_name);
            mItemYetOrderJobPay = itemView.findViewById(R.id.item_yet_order_job_pay);
            mItemYetOrderJobSuffer = itemView.findViewById(R.id.item_yet_order_job_suffer);
            mItemYetOrderJobContent = itemView.findViewById(R.id.item_yet_order_job_content);
            mItemYetOrderJobLook = itemView.findViewById(R.id.item_yet_order_job_look);
            mItemYetOrderJobProgress = itemView.findViewById(R.id.item_yet_order_job_progress);
        }
    }
}
