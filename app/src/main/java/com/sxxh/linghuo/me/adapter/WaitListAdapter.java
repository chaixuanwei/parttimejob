package com.sxxh.linghuo.me.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.me.bean.SalaryBean;

import java.util.ArrayList;

public class WaitListAdapter extends RecyclerView.Adapter<WaitListAdapter.ViewHolder> {
    Context mContext;
    ArrayList<SalaryBean.DataBean> mList;

    public WaitListAdapter(Context pContext, ArrayList<SalaryBean.DataBean> pList) {
        mContext = pContext;
        mList = pList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_wait, pViewGroup, false);
        ViewHolder mViewHolder = new ViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, int pI) {
        ViewHolder mViewHolder = pViewHolder;
        mViewHolder.mWaitFirmName.setText(mList.get(pI).getTask_name());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mWaitFirmName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mWaitFirmName = itemView.findViewById(R.id.wait_firm_name);
        }
    }
}
