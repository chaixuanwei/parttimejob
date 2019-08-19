package com.example.myapplication.me.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

public class NoOrderAdapter extends RecyclerView.Adapter<NoOrderAdapter.ViewHolder> {
    Context mContext;

    public NoOrderAdapter(Context pContext) {
        mContext = pContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_no_order, pViewGroup,false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, int pI) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mItemOrderJobName;
        private TextView mItemOrderJobPay;
        private TextView mItemOrderJobSuffer;
        private TextView mItemOrderJobContent;
        private TextView mItemOrderJobLook;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemOrderJobName = itemView.findViewById(R.id.item_order_job_name);
            mItemOrderJobPay = itemView.findViewById(R.id.item_order_job_pay);
            mItemOrderJobSuffer = itemView.findViewById(R.id.item_order_job_suffer);
            mItemOrderJobContent = itemView.findViewById(R.id.item_order_job_content);
            mItemOrderJobLook = itemView.findViewById(R.id.item_order_job_look);
        }
    }
}
