package com.sxxh.linghuo.me.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.me.activity.IssusActivity;
import com.sxxh.linghuo.me.bean.MyIssusBean;

import java.util.ArrayList;

public class NoOrderAdapter extends RecyclerView.Adapter<NoOrderAdapter.ViewHolder> {
    Context mContext;
    ArrayList<MyIssusBean.DataBean> mList;

    public NoOrderAdapter(Context pContext, ArrayList<MyIssusBean.DataBean> pList) {
        mContext = pContext;
        mList = pList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_no_order, pViewGroup, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, int pI) {
        ViewHolder mViewHolder = pViewHolder;
        final MyIssusBean.DataBean mDataBean = mList.get(pI);
        mViewHolder.mItemOrderJobName.setText(mDataBean.getName());
        mViewHolder.mItemOrderJobContent.setText(mDataBean.getDes());
        mViewHolder.mItemOrderJobPay.setText(mDataBean.getPay());
        mViewHolder.mItemOrderJobLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIssusIntent = new Intent(mContext, IssusActivity.class);
                mContext.startActivity(mIssusIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mItemOrderJobName;
        private TextView mItemOrderJobPay;
        private TextView mItemOrderJobContent;
        private TextView mItemOrderJobLook;
        private TextView mItemOrderWaitPayment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemOrderJobName = itemView.findViewById(R.id.item_order_job_name);
            mItemOrderJobPay = itemView.findViewById(R.id.item_order_job_pay);
            mItemOrderJobContent = itemView.findViewById(R.id.item_order_job_content);
            mItemOrderJobLook = itemView.findViewById(R.id.item_order_job_look);
            mItemOrderWaitPayment = itemView.findViewById(R.id.item_order_wait_payment);
        }
    }
}
