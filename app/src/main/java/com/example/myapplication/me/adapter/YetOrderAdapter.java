package com.example.myapplication.me.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

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

    }

    @Override
    public int getItemCount() {
        return 1;
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
