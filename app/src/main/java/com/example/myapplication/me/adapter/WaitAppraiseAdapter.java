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
import com.example.myapplication.me.activity.AtonceActivity;

public class WaitAppraiseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int WAITAPPRAISE = 0;
    private int PASTAPPRAISE = 1;
    Context mContext;

    public WaitAppraiseAdapter(Context pContext) {
        mContext = pContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        RecyclerView.ViewHolder mViewHolder = null;
        if (pI == WAITAPPRAISE) {
            View mView = LayoutInflater.from(mContext).inflate(R.layout.item_wait_appraise, pViewGroup, false);
            mViewHolder = new WaitHolder(mView);
        } else {
            View mView = LayoutInflater.from(mContext).inflate(R.layout.item_past_appraise, pViewGroup, false);
            mViewHolder = new PastHolder(mView);
        }
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder pViewHolder, int pI) {
        if (getItemViewType(pI) == PASTAPPRAISE) {
            PastHolder mPastHolder = (PastHolder) pViewHolder;
            mPastHolder.mAppraisePastProjectClick.setTextColor(mContext.getResources().getColor(R.color.grey_second));
        } else {
            WaitHolder mWaitHolder = (WaitHolder) pViewHolder;
            mWaitHolder.mAppraiseWaitProjectClick.setTextColor(mContext.getResources().getColor(R.color.app_theme_color));
            mWaitHolder.mAppraiseWaitProjectClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent = new Intent(mContext, AtonceActivity.class);
                    mContext.startActivity(mIntent);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == WAITAPPRAISE) {
            return WAITAPPRAISE;
        } else {
            return PASTAPPRAISE;
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class PastHolder extends RecyclerView.ViewHolder {
        private TextView mAppraisePastProjectName;
        private TextView mAppraisePastProjectNumber;
        private TextView mAppraisePastProjectClick;
        private TextView mAppraisePastJobContent;
        private TextView mAppraisePastTreatment;
        private TextView mAppraisePastCompany;

        public PastHolder(@NonNull View itemView) {
            super(itemView);
            mAppraisePastProjectName = itemView.findViewById(R.id.appraise_past_project_name);
            mAppraisePastProjectNumber = itemView.findViewById(R.id.appraise_past_project_number);
            mAppraisePastProjectClick = itemView.findViewById(R.id.appraise_past_project_click);
            mAppraisePastJobContent = itemView.findViewById(R.id.appraise_past_job_content);
            mAppraisePastTreatment = itemView.findViewById(R.id.appraise_past_treatment);
            mAppraisePastCompany = itemView.findViewById(R.id.appraise_past_company);
        }
    }

    private class WaitHolder extends RecyclerView.ViewHolder {
        private TextView mAppraiseWaitProjectName;
        private TextView mAppraiseWaitProjectNumber;
        private TextView mAppraiseWaitProjectClick;
        private TextView mAppraiseWaitJobContent;
        private TextView mAppraiseWaitTreatment;
        private TextView mAppraiseWaitCompany;
        public WaitHolder(View pView) {
            super(pView);
            mAppraiseWaitProjectName = pView.findViewById(R.id.appraise_wait_project_name);
            mAppraiseWaitProjectNumber = pView.findViewById(R.id.appraise_wait_project_number);
            mAppraiseWaitProjectClick = pView.findViewById(R.id.appraise_wait_project_click);
            mAppraiseWaitJobContent = pView.findViewById(R.id.appraise_wait_job_content);
            mAppraiseWaitTreatment = pView.findViewById(R.id.appraise_wait_treatment);
            mAppraiseWaitCompany = pView.findViewById(R.id.appraise_wait_company);
        }
    }
}
