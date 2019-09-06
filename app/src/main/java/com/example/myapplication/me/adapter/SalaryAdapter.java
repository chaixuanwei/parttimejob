package com.example.myapplication.me.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.me.bean.SalaryBean;

import java.util.ArrayList;

public class SalaryAdapter extends RecyclerView.Adapter<SalaryAdapter.ViewHolder> {
    Context mContext;
    ArrayList<SalaryBean.DataBean> mList;

    public SalaryAdapter(Context pContext, ArrayList<SalaryBean.DataBean> pList) {
        mContext = pContext;
        mList = pList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_salary, pViewGroup, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, int pI) {
        ViewHolder mViewHolder = pViewHolder;
        mViewHolder.mSalaryFirmName.setText(mList.get(pI).getTask_name());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mSalaryFirmName;
        private LinearLayout mSalaryLl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mSalaryFirmName = itemView.findViewById(R.id.salary_firm_name);
            mSalaryLl = itemView.findViewById(R.id.salary_ll);
        }
    }
}
