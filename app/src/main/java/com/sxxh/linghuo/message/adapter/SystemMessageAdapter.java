package com.sxxh.linghuo.message.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.message.bean.SystemBean;

import java.util.ArrayList;

public class SystemMessageAdapter extends RecyclerView.Adapter<SystemMessageAdapter.ViewHolder> {
    Context mContext;
    ArrayList<SystemBean.DataBean> mSystemList;

    public SystemMessageAdapter(Context pContext, ArrayList<SystemBean.DataBean> pSystemList) {
        mContext = pContext;
        mSystemList = pSystemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_system_message, pViewGroup, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, int pI) {
        SystemBean.DataBean mDataBean = mSystemList.get(pI);
        ViewHolder mViewHolder = pViewHolder;
        mViewHolder.mItemSystemTitle.setText(mDataBean.getTitle());
        mViewHolder.mItemSystemContent.setText(mDataBean.getContent());
        mViewHolder.mItemSystemTime.setText(mDataBean.getCreate_time());
    }

    @Override
    public int getItemCount() {
        return mSystemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mItemSystemTitle;
        private TextView mItemSystemContent;
        private TextView mItemSystemTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemSystemTitle = itemView.findViewById(R.id.item_system_title);
            mItemSystemContent = itemView.findViewById(R.id.item_system_content);
            mItemSystemTime = itemView.findViewById(R.id.item_system_time);
        }
    }
}
