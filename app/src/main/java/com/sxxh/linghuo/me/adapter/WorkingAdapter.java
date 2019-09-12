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
import com.sxxh.linghuo.me.activity.SubmitWorkActivity;
import com.sxxh.linghuo.me.bean.WorkingBean;

import java.util.ArrayList;

public class WorkingAdapter extends RecyclerView.Adapter<WorkingAdapter.ViewHolder> {
    Context mContext;
    ArrayList<WorkingBean.DataBean> mList;

    public WorkingAdapter(Context pContext, ArrayList<WorkingBean.DataBean> pList) {
        mContext = pContext;
        mList = pList;
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
        ViewHolder mViewHolder = pViewHolder;
        final WorkingBean.DataBean mDataBean = mList.get(pI);
        mViewHolder.mWorkingProject.setText(mDataBean.getName());
        mViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mSubmitWorkIntent = new Intent(mContext, SubmitWorkActivity.class);
                mSubmitWorkIntent.putExtra("task_id", mDataBean.getT_id() + "");
                mSubmitWorkIntent.putExtra("name", mDataBean.getName());
                mContext.startActivity(mSubmitWorkIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mWorkingProject;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mWorkingProject = itemView.findViewById(R.id.working_project);
        }
    }
}
